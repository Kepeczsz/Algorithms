package com.example.demo.config;

import com.example.demo.components.Car;
import com.example.demo.components.Engine;
import com.example.demo.components.Transmission;
import com.example.demo.repository.CarOwnerRepository;
import com.example.demo.repository.PureCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan(basePackages = "com.example.demo.domain")
public class AppConfig {

    @Autowired
    private Environment env;

    @Autowired
    Engine engine;

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Autowired
    private PureCarRepository pureCarRepository;

    @Bean
    public Transmission transmission(){return new Transmission();}

    @Bean
    public Car car(Engine engine, Transmission transmission){
        return new Car(engine, transmission, env.getProperty("car.name"));
    }
}
