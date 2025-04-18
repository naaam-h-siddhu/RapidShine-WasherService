package com.rapidshine.carwash.washerservice.service;

import com.rapidshine.carwash.washerservice.dto.JobCompletionEvent;
import com.rapidshine.carwash.washerservice.model.BookingStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingStatusPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void updateWasherStatus(String email, BookingStatus bookingStatus) {
        JobCompletionEvent event = new JobCompletionEvent();
        event.setWasherEmail(email);
        event.setBookingStatus(bookingStatus);

        rabbitTemplate.convertAndSend(
                "job.completion.exchange",   // exchange
                "job.completion.update",            // routing key
                event                              // message
        );
    }
}
