package com.example.demo.components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
@Setter
public class Oil {
    private Long id;
    public Oil() {
        log.info("Tworzenie beana oil za pomocą konstruktora domyślnego");
        log.info(typeOfOil);
    }
    String typeOfOil = "Type of Oil : synthetic oil";
}
