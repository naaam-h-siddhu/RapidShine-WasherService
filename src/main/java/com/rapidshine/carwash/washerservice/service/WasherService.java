package com.rapidshine.carwash.washerservice.service;

import com.rapidshine.carwash.washerservice.dto.BookingResponseDto;
import com.rapidshine.carwash.washerservice.dto.WasherAvailabilityResponse;
import com.rapidshine.carwash.washerservice.dto.WasherUpdateRequest;
import com.rapidshine.carwash.washerservice.dto.WasherUpdateResponse;
import com.rapidshine.carwash.washerservice.exceptions.UserNotFoundException;
import com.rapidshine.carwash.washerservice.exceptions.WasherBusyException;
import com.rapidshine.carwash.washerservice.model.*;
import com.rapidshine.carwash.washerservice.repository.BookingRepository;
import com.rapidshine.carwash.washerservice.repository.UserRepository;
import com.rapidshine.carwash.washerservice.repository.WasherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WasherService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WasherRepository washerRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingStatusPublisher bookingStatusPublisher;

    @Autowired
    private WasherIntegrationService washerIntegrationService;
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
        washer.setTotal_services(washer.getTotal_services()+1);
        washerRepository.save(washer);
        return "Washer marked as done";
    }

    public WasherUpdateResponse editWasher(String email, WasherUpdateRequest washerUpdateRequest) throws Exception {
        Washer washer = getWasher(email);
        washer.setName(washerUpdateRequest.getName());
        washer.setPhoneNumber(washerUpdateRequest.getPhoneNumber());
        washer.setAddress(washerUpdateRequest.getAddress());
        washerRepository.save(washer);
        return new WasherUpdateResponse(washer.getName(),washer.getPhoneNumber(), washer.getAddress());
    }

    public WasherAvailabilityResponse getStatus(String email) throws Exception {
        Washer washer = getWasher(email);
        return new WasherAvailabilityResponse(washer.isAvailable());


    }

    public void toggleStatus(String name) throws Exception {
        if(washerIntegrationService.isWasherWorking(name)){
            throw new WasherBusyException("Washer Already Working Not able to toggle");
        }
        else {
            Washer washer = getWasher(name);
            washer.setAvailable(!washer.isAvailable());
            washerRepository.save(washer);
        }


    }
    public ResponseEntity<Map<String, List<BookingResponseDto>>> getAllBookings(String email) {
        List<Booking> completedBookings = bookingRepository.findByWasherIdAndStatus(email, BookingStatus.COMPLETED);
        List<Booking> pendingBookings = bookingRepository.findByWasherIdAndStatus(email, BookingStatus.CONFIRMED);

        List<BookingResponseDto> completedDtos = completedBookings.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        List<BookingResponseDto> pendingDtos = pendingBookings.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        Map<String, List<BookingResponseDto>> ans = new HashMap<>();
        ans.put("Completed", completedDtos);
        ans.put("Pending", pendingDtos);

        return ResponseEntity.ok(ans);
    }

    public BookingResponseDto mapToDto(Booking booking) {
        return new BookingResponseDto(
                booking.getCustomer().getName(),
                booking.getCar().getBrand(),
                booking.getCar().getModel(),
                booking.getCar().getLicenceNumberPlate(),
                booking.getBookingTime(),
                booking.getBookingStatus()
        );
    }
}
