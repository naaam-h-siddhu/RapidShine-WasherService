package com.rapidshine.carwash.washerservice.controller;

import com.rapidshine.carwash.washerservice.model.Washer;
import com.rapidshine.carwash.washerservice.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/washer")
public class WasherController {

    @Autowired
    private WasherService washerService;

    @GetMapping("/available")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Washer getAvailableWasher() {
        return washerService.getAvailableWasher();
    }

    @PostMapping("/markDone")
    @PreAuthorize("hasRole('WASHER')")
    public String markWasherTaskAsDone(@RequestParam Long washerId) {
//        return washerService.markTaskDone(washerId);
        return null;
    }

}
