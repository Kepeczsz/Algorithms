package com.example.demo;

import com.example.demo.components.Car;
import com.example.demo.dao.CarDaoImpl;
import com.example.demo.domain.CarOwner;
import com.example.demo.domain.PureCar;
import com.example.demo.repository.CarOwnerRepository;
import com.example.demo.repository.PureCarRepository;
import com.example.demo.services.CarOwnerService;
import com.example.demo.services.PureCarService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@ComponentScan(basePackages = "appSettings.config")
@ComponentScan("com.example.demo.repository")
@Log4j2
public class AppRunner implements CommandLineRunner {
    @Autowired
    Car car;

    @Autowired
    private CarOwnerService carOwnerService;

    @Autowired
    private PureCarService pureCarService;

    @Override
    public void run(String... args) throws Exception {
        log.info("EXECUTING : command line runner");
        car.run();
        log.info("FINISH : command line runner");

//        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/lab05", "root", "inventoryzut!@");
//        CarDaoImpl carDao = new CarDaoImpl(dataSource);
//
//        try {
//            carDao.jdbcTemplate.update("Delete from cars");
//            carDao.insert(123L,"Ferrari");
//            carDao.insert(124L,"BMW");
//            carDao.insert(125L,"Opel");
//            carDao.delete(123L);
//            carDao.update(124L, "Mazda");
//            carDao.findAll();
//            carDao.findCarByName("Mazda");
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        }


        CarOwner carOwner = new CarOwner("Stefan","Szczepaniak");

       // this.carOwnerService.insertInto(carOwner);
       // CarOwner carowner1 = this.carOwnerService.getByName("Michal");

        PureCar pureCar = new PureCar("Mx-30","Mazda");
        this.pureCarService.insertInto(pureCar);
    }
}
