package com.rapidshine.carwash.washerservice.controller;

import com.rapidshine.carwash.washerservice.dto.BookingResponseDto;
import com.rapidshine.carwash.washerservice.dto.WasherAvailabilityResponse;
import com.rapidshine.carwash.washerservice.dto.WasherUpdateRequest;
import com.rapidshine.carwash.washerservice.dto.WasherUpdateResponse;
import com.rapidshine.carwash.washerservice.model.Booking;
import com.rapidshine.carwash.washerservice.model.Washer;
import com.rapidshine.carwash.washerservice.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/washer")
public class WasherController {

    @Autowired
    private WasherService washerService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
    @GetMapping("/available")
    public List<Washer> getAvailableWasher() {

        return washerService.getAvailableWasher();
    }

    @PostMapping("/done")
    @PreAuthorize("hasRole('WASHER')")
    public String markWasherTaskAsDone(Authentication authentication) throws Exception {
        return washerService.markTaskDone(authentication.getName());

    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('WASHER')")
    public WasherUpdateResponse editWasher(Authentication authentication,@RequestBody WasherUpdateRequest washerUpdateRequest) throws Exception {
        return washerService.editWasher(authentication.getName(),washerUpdateRequest);
    }

    @GetMapping("/status")
    @PreAuthorize("hasRole('WASHER')")
    public WasherAvailabilityResponse getStatus(Authentication authentication) throws Exception {
        return  washerService.getStatus(authentication.getName());
    }

    @PutMapping("/toggle-status")
    public void toggleWasher(Authentication authentication)throws Exception{
        washerService.toggleStatus(authentication.getName());
    }
    @GetMapping("/get-all-bookings")
    public ResponseEntity<Map<String,List<BookingResponseDto>>> getAllBookings(Authentication auth){
        return washerService.getAllBookings(auth.getName());
    }
}
