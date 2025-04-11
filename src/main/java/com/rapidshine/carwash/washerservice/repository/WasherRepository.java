package com.rapidshine.carwash.washerservice.repository;

import com.rapidshine.carwash.washerservice.model.Washer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WasherRepository extends JpaRepository<Washer,Long> {
    @Query("SELECT c FROM Washer c WHERE c.user.id = :userId")
    Optional<Washer> findByUserId(@Param("userId") Long userId);

}
