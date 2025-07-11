package com.rapidshine.carwash.washerservice.dto;

import com.rapidshine.carwash.washerservice.model.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
   private String customerName;
   private String carBrand;
   private String carModel;
   private String carNumber;
   private LocalDateTime bookingTime;
   private BookingStatus bookingStatus;

}