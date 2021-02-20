package com.fiap.kafka.consumer.adapter.exception;

import lombok.Getter;

@Getter
public class BadRequestLatitude extends Exception{

    double latitude;

    public BadRequestLatitude(double latitude) {
        this.latitude = latitude;
    }
}
