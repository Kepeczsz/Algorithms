package com.example.demo.services;

import com.example.demo.domain.CarOwner;
import com.example.demo.domain.PureCar;
import com.example.demo.repository.PureCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PureCarService {
    @Autowired
    PureCarRepository pureCarRepository;

    public PureCarService(PureCarRepository pureCarRepository){
        this.pureCarRepository = pureCarRepository;
    }

    public PureCar getById(Long id) {
        return this.pureCarRepository.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("Role not found with id " + id);
        });
    }
    public PureCar insertInto(PureCar pureCar){
        return this.pureCarRepository.save(pureCar);
    }
    public void delete(Long id){
        this.pureCarRepository.deleteById(id);
    }
}
