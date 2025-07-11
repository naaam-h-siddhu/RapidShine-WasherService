package com.rapidshine.carwash.washerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherUpdateRequest {
    private String name;
    private String phoneNumber;
    private String address;

}
