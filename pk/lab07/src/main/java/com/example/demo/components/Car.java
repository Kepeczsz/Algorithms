package com.example.demo.components;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class Car extends Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    private Engine engine;
    private Transmission transmission;

    float roadFuelConsumption;

    @Value("${car.route.length}")
    Long roughtLength;

    private String carName;

    public Car(Engine engine, Transmission transmission, String name){
        log.info("Car has been created");
        this.engine = engine;
        this.transmission = transmission;
        this.carName=name;
    }
    @PostConstruct
    public void init() {
        roadFuelConsumption = (roughtLength / 100 ) * engine.fuelConsumption;
    }


    public void run() {
        log.info("RUNNING CAR");
        engine.start();
        try {
            System.out.println("Waiting...");
            sleep(1000*roughtLength);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Road length: " + roughtLength);
        log.info("Car burnt this much fuel: " + roadFuelConsumption);
        log.info("Car name: " + carName);

        log.info("Transmission info");
        transmission.transmisionInfo();
    }
}
