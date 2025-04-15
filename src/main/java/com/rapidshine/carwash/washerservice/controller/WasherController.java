package com.rapidshine.carwash.washerservice.controller;

import com.rapidshine.carwash.washerservice.model.Washer;
import com.rapidshine.carwash.washerservice.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/washer")
public class WasherController {

    @Autowired
    private WasherService washerService;

    @GetMapping("/health")
    @PreAuthorize("hasRole('CUSTOMER')")

    public String health() {
        return "OK";
    }
    @GetMapping("/available")
    @PreAuthorize("hasRole('SERVICE')")
    public List<Washer> getAvailableWasher() {

        return washerService.getAvailableWasher();
    }

    @PostMapping("/markDone")
    @PreAuthorize("hasRole('WASHER')")
    public String markWasherTaskAsDone(@RequestParam Long washerId) {
//        return washerService.markTaskDone(washerId);
        return null;
    }

}
