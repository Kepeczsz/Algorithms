package com.example.demo.components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Getter
@Setter
public class Transmission {
    private Long id;
    String co2Transmission = "BIG";

    @Value("#{T(java.lang.Math).PI * 2 * ${transmission.wheel.radius}}")
    float transmissionBeltLength;
    public Transmission() {

    }
    public void transmisionInfo(){
        log.info("Tworzę beana transmission");
        log.info("transmissionbelt: " + transmissionBeltLength);
        log.info(co2Transmission);
    }
}
