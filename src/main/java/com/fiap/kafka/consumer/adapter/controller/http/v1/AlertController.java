package com.fiap.kafka.consumer.adapter.controller.http.v1;

import com.fiap.kafka.consumer.adapter.exception.BadRequestMoisturePercentage;
import com.fiap.kafka.consumer.domain.domain.WheaterAlert;
import com.fiap.kafka.consumer.domain.usecase.CheckWheatherUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alert/v1")
public class AlertController {

    CheckWheatherUseCase checkWheatherUseCase;

    @Autowired
    public AlertController(CheckWheatherUseCase checkWheatherUseCase){
        this.checkWheatherUseCase = checkWheatherUseCase;
    }

    @PostMapping(value="/wheater")
    public ResponseEntity<Object> getClient(
            @RequestBody WheaterAlert wheaterAlert) throws BadRequestMoisturePercentage {
        return new ResponseEntity<Object>(checkWheatherUseCase.execute(wheaterAlert.checkValues()), HttpStatus.OK);
    }

}



