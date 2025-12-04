package com.example.demo.components;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class Metrics {

    @Before("execution(void com.example.demo.components.Car.run())")
    public void routeTime(){
        System.out.println("\nPorada dowiązana do metody run\n");
    }

    @Pointcut("execution(void com.example.demo.components.Engine.start())")
    private void enginePointCut() {}

    @After("enginePointCut()")
    private void afterEngineStart(){
        log.info("Engine started succesfully");
    }

    @AfterThrowing("enginePointCut()")
    private void afterFailedEngineStart(){
        log.info("Engine doesn't work properly");
    }
}

