package com.fiap.kafka.consumer.adapter.exception;

import lombok.Getter;

@Getter
public class BadRequestTemperature extends Exception{

    int temperature;

    public BadRequestTemperature(int temperature) {
        this.temperature = temperature;
    }
}
