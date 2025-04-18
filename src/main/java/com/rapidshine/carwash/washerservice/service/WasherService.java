package com.rapidshine.carwash.washerservice.service;

import com.rapidshine.carwash.washerservice.exceptions.UserNotFoundException;
import com.rapidshine.carwash.washerservice.model.BookingStatus;
import com.rapidshine.carwash.washerservice.model.Customer;
import com.rapidshine.carwash.washerservice.model.User;
import com.rapidshine.carwash.washerservice.model.Washer;
import com.rapidshine.carwash.washerservice.repository.UserRepository;
import com.rapidshine.carwash.washerservice.repository.WasherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WasherService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WasherRepository washerRepository;

    @Autowired
    private BookingStatusPublisher bookingStatusPublisher;
    public List<Washer> getAvailableWasher() {
        return washerRepository.findAll()
                .stream()
                .filter(Washer::isAvailable)
                .collect(Collectors.toList());

    }




    // helper function to get the washer from jwt token
    private Washer getWasher(String email) throws  Exception{
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with "+email+" not " +
                "found"));
        Long id = user.getId();
        Washer washer = washerRepository.findByUserId(id).orElseThrow(() -> new UserNotFoundException("Washer " +
                "with " + email + " not found"));
        return washer;



    }

    public String markTaskDone(String email) throws Exception {
        Washer washer = getWasher(email);
        washer.setAvailable(true);
        bookingStatusPublisher.updateWasherStatus(email, BookingStatus.COMPLETED);
        washerRepository.save(washer);
        return "Washer marked as done";
    }
}
