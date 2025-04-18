package com.rapidshine.carwash.washerservice.service;

import com.rapidshine.carwash.washerservice.dto.WasherStatusUpdateEvent;
import com.rapidshine.carwash.washerservice.model.Washer;
import com.rapidshine.carwash.washerservice.repository.WasherRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasherStatusConsumer {
    @Autowired
    private WasherRepository washerRepository;

    @RabbitListener(queues = "washer.status.update.queue")
    public void handleWasherStatusUpdate(WasherStatusUpdateEvent event) {
        System.out.println("Received washer status update: " + event.getEmail());
        Washer washer = washerRepository.findByEmail(event.getEmail()).orElse(null) ;
        if (washer != null) {
            washer.setAvailable(event.isAvailable());
            washerRepository.save(washer);
        }
        System.out.println("Washer update received in WasherService: " + event.getEmail());

    }
}
