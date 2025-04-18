package com.rapidshine.carwash.washerservice.dto;

import com.rapidshine.carwash.washerservice.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCompletionEvent {
    String washerEmail;
    BookingStatus bookingStatus;
}
