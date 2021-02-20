package com.fiap.kafka.consumer.adapter.exception;

import lombok.Getter;

@Getter
public class BadRequestMoisturePercentage extends Exception{

    int moisturePercentage;

    public BadRequestMoisturePercentage(int moisturePercentage) {
        this.moisturePercentage = moisturePercentage;
    }
}
