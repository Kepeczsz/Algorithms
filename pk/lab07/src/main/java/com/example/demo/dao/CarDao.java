package com.example.demo.dao;

import com.example.demo.components.Car;

import java.util.List;
import java.util.Map;

public interface CarDao {
    List<Map<String, Object>> findAll();
    List<Car> findCarByName(String name);
    void insert(Long id, String name);
    void update(Long id, String name);
    void delete(Long id);
}
