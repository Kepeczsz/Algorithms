package com.example.demo.dao;

import com.example.demo.components.Car;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class CarDaoImpl implements CarDao{
    public JdbcTemplate jdbcTemplate;

    public CarDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM cars");
    }

    @Override
    public List<Car> findCarByName(String name) {
        String sql = "SELECT * FROM cars WHERE name = ?";

        List<Car> cars = jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) -> {
            Car car = new Car();
            car.setId(rs.getLong("id"));
            car.setName(rs.getString("name"));
            return car;
        });
        return cars;
    }

    @Override
    public void insert(Long id, String name) {
        jdbcTemplate.update("INSERT INTO cars (id, name) VALUES (?, ?)",id,name);
    }

    @Override
    public void update(Long id, String name) {
        jdbcTemplate.update("UPDATE cars SET name = ? WHERE id = ?",name, id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM cars WHERE id = ?", id);
    }
}
