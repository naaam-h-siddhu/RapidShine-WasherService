package com.rapidshine.carwash.washerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherRatingUpdateDto {
    private String washerEmail;
    private double rating;
    
}
