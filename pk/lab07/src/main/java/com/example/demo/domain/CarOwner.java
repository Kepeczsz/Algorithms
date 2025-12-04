package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CarOwner")
@NoArgsConstructor
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany()
    private List<PureCar> pureCar;

    public CarOwner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
