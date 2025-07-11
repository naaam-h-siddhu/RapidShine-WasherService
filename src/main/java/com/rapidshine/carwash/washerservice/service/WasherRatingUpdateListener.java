package com.rapidshine.carwash.washerservice.service;

import com.netflix.discovery.converters.Auto;
import com.rapidshine.carwash.washerservice.dto.WasherRatingUpdateDto;
import com.rapidshine.carwash.washerservice.exceptions.UserNotFoundException;
import com.rapidshine.carwash.washerservice.model.Washer;
import com.rapidshine.carwash.washerservice.repository.WasherRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasherRatingUpdateListener {
    @Autowired
    private WasherRepository washerRepository;

    @RabbitListener(queues = "review.washer.rating.update.queue")
    public void updateRatingHandler(WasherRatingUpdateDto washerRatingUpdateDto){

        Washer washer = washerRepository.findByEmail(washerRatingUpdateDto.getWasherEmail()).orElseThrow(()-> new UserNotFoundException("Washer not found"));
        washer.setRating(
                (( washer.getRating()* (washer.getTotal_services()-1))+washerRatingUpdateDto.getRating())/ washerRatingUpdateDto.getRating()
        );
        System.out.println("Washer Rating is updated !!!!");
        washerRepository.save(washer);
    }
}
