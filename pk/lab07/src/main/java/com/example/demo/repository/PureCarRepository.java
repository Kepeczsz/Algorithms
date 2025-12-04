package com.example.demo.repository;

import com.example.demo.domain.CarOwner;
import com.example.demo.domain.PureCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PureCarRepository extends JpaRepository<PureCar, Long> {
}
