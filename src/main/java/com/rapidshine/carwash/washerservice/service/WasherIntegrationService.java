    package com.rapidshine.carwash.washerservice.service;


    import com.rapidshine.carwash.washerservice.feign.BookingClient;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
    import org.springframework.stereotype.Service;

    @Service
    public class WasherIntegrationService {

        @Autowired
        private BookingClient bookingClient;
        @Autowired
        private  CircuitBreakerFactory<?,?> circuitBreakerFactory;

        public boolean isWasherWorking(String email){

            return circuitBreakerFactory.create("bookingServiceCB")
                    .run(()-> bookingClient.isWorking(email), throwable -> {
                        return handleBookingFailure();
                    });
        }

        private Boolean handleBookingFailure() {
            return null;
        }

    }
