package com.fiap.kafka.consumer.adapter.exception;

import lombok.Getter;

@Getter
public class BadRequestLongitude extends Exception{

    double longitude;

    public BadRequestLongitude(double longitude) {
        this.longitude = longitude;
    }
}
