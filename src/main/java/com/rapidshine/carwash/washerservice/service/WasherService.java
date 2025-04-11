package com.rapidshine.carwash.washerservice.service;

import com.rapidshine.carwash.washerservice.exceptions.UserNotFoundException;
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

    public Washer getAvailableWasher(){
        List<Washer> availableWasher = getWashers();
        if(availableWasher.isEmpty()){
            return null;

        }
        Random random = new Random();
        return availableWasher.get(random.nextInt(availableWasher.size()));

    }

    private List<Washer> getWashers() {
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
}
