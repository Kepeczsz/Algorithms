package com.example.demo.domain;

import com.example.demo.services.PureCarService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PureCar")
@NoArgsConstructor
public class PureCar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String mark;

    private String type;

    private String registrationNumber;

    public PureCar(String name, String mark, String type, String registrationNumber) {
        this.name = name;
        this.mark = mark;
        this.type = type;
        this.registrationNumber = registrationNumber;
    }

    public PureCar(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }
}
