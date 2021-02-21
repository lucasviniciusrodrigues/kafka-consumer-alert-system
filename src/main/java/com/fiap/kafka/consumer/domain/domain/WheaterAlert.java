package com.fiap.kafka.consumer.domain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.kafka.consumer.adapter.exception.BadRequestLatitude;
import com.fiap.kafka.consumer.adapter.exception.BadRequestLongitude;
import com.fiap.kafka.consumer.adapter.exception.BadRequestMoisturePercentage;
import com.fiap.kafka.consumer.adapter.exception.BadRequestTemperature;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class WheaterAlert {

    private String droneId;
    private double latitude;
    private double longitude;
    private int temperatureCelsius;
    private int moisturePercentage;

    @JsonProperty("isTraceble")
    private boolean isTraceble;

    @SneakyThrows
    public WheaterAlert checkValues() {
        checkLatitude();
        checkLongitude();
        checkMoisturePercentage();
        checkTemperatureCelsius();

        return this;
    }

    private void checkLatitude() throws BadRequestLatitude {
        if(latitude == 0)
            throw new BadRequestLatitude(latitude);
    }

    private void checkLongitude() throws BadRequestLongitude {
        if(longitude == 0)
            throw new BadRequestLongitude(longitude);
    }

    private void checkMoisturePercentage() throws BadRequestMoisturePercentage {
        if(moisturePercentage < 0 || moisturePercentage > 100)
            throw new BadRequestMoisturePercentage(moisturePercentage);
    }

    private void checkTemperatureCelsius() throws BadRequestTemperature {
        if(temperatureCelsius < -25 || temperatureCelsius > 40)
            throw new BadRequestTemperature(temperatureCelsius);
    }

}
