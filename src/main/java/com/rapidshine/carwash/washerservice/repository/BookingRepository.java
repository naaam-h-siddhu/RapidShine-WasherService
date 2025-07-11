package com.rapidshine.carwash.washerservice.repository;

import com.rapidshine.carwash.washerservice.model.Booking;
import com.rapidshine.carwash.washerservice.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Booking getBookingsByBookingId(Long bookingId);
    @Query("SELECT b FROM Booking b WHERE b.customer.customerID = :customerId")
    List<Booking> findByCustomerId(@Param("customerId") Long customerId);
    @Query("SELECT b FROM Booking b WHERE b.washerEmail= :washerEmail AND b.bookingStatus = :status")
    List<Booking> findByWasherIdAndStatus(@Param("washerEmail") String washerEmail, @Param("status") BookingStatus status);
}
