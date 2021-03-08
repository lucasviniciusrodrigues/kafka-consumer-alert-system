package com.fiap.kafka.consumer.adapter.exception.handler;

import com.fiap.kafka.consumer.adapter.exception.BadRequestLatitude;
import com.fiap.kafka.consumer.adapter.exception.BadRequestLongitude;
import com.fiap.kafka.consumer.adapter.exception.BadRequestMoisturePercentage;
import com.fiap.kafka.consumer.adapter.exception.BadRequestTemperature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class TBDExceptionHandler {

    @ExceptionHandler(value = BadRequestLongitude.class)
    public ResponseEntity<Object> badRequestLongitude(BadRequestLongitude e){
        return new ResponseEntity<Object>("Invalid longitude: " + e.getLongitude(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestLatitude.class)
    public ResponseEntity<Object> badRequestLatitude(BadRequestLatitude e){
        return new ResponseEntity<Object>("Invalid latitude: " + e.getLatitude(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestMoisturePercentage.class)
    public ResponseEntity<Object> badRequestMoisturePercentage(BadRequestMoisturePercentage e){
        return new ResponseEntity<Object>("Moisture percentage should be between 0 and 100. Invalid moisture percentage: " + e.getMoisturePercentage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestTemperature.class)
    public ResponseEntity<Object> badRequestTemperature(BadRequestTemperature e){
        return new ResponseEntity<Object>("Temperature should be between -25 and 40. Invalid temperature: " + e.getTemperature(), HttpStatus.BAD_REQUEST);
    }

}