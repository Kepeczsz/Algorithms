package com.example.demo.components;

import com.example.demo.exceptions.EngineNotStartetException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class Engine {
    private Long id;
    private Oil oil;
    public float fuelConsumption;
    @Autowired
    public Engine(Oil oil, @Value("${engine.fuel.consumption}") float fuelConsumption)
    {
        log.info("Wstrzykuję oil przez właściowść");
        this.fuelConsumption = fuelConsumption;
        this.oil = oil;
    }
    public void start() {
        Random random = new Random();
        float breakValue = random.nextFloat();
//        if(breakValue < 0.25)
//            throw new EngineNotStartetException();
//
//        try {
            log.info("Engine works");
            log.info("Engine burns:" + fuelConsumption);
//        }
//        catch(EngineNotStartetException e){
//        }
    }
}
