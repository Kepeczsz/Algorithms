package com.example.demo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Result {
    private String status;
    private String errCode;
    private String errMsg;

    public Result(String status, String errCode, String errMsg) {
        this.status = status;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
