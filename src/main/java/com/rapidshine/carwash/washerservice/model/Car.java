package com.rapidshine.carwash.washerservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;

    public Car(String brand, String model, String licenceNumberPlate,String carType) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.licenceNumberPlate = licenceNumberPlate;
    }
    private String carType;
    private String model;
    private String licenceNumberPlate;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonBackReference
    private Customer customer;
}
