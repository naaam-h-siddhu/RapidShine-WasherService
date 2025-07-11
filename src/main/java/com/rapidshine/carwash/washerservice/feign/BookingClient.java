package com.rapidshine.carwash.washerservice.feign;

import com.rapidshine.carwash.washerservice.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "booking-service",configuration = FeignClientConfig.class)
public interface BookingClient {

    @GetMapping("booking/isWorking")
    boolean isWorking(@RequestParam String email);
}
