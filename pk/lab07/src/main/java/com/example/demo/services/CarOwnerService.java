package com.example.demo.services;

import com.example.demo.domain.CarOwner;
import com.example.demo.repository.CarOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarOwnerService {
    @Autowired
   CarOwnerRepository carOwnerRepository;

    public CarOwnerService(CarOwnerRepository carOwnerRepository){
        this.carOwnerRepository = carOwnerRepository;
    }
    public CarOwnerService(){
        this.carOwnerRepository = carOwnerRepository;
    }

    public CarOwner getById(Long id) {
        return this.carOwnerRepository.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("Role not found with id " + id);
        });
    }
    public CarOwner getByName(String name) {
        List<CarOwner> carOwners = this.carOwnerRepository.findAll();
        for (CarOwner owner : carOwners) {
            if (owner.getFirstName().equals(name)) {
                return owner;
            }
        }
        throw new NoSuchElementException("Car owner not found with name " + name);
    }

    public CarOwner insertInto(CarOwner carOwner){
        return this.carOwnerRepository.save(carOwner);
    }
    public void delete(Long id){
        this.carOwnerRepository.deleteById(id);
    }
}
