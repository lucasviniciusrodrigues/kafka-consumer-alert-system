package com.fiap.kafka.consumer.domain.usecase;

import com.fiap.kafka.consumer.adapter.gateway.service.EmailService;
import com.fiap.kafka.consumer.domain.domain.WheaterAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckWheatherUseCase {

    EmailService emailService;

    @Autowired
    CheckWheatherUseCase(EmailService emailService){
        this.emailService = emailService;
    }

    public void execute(WheaterAlert wheaterAlert) {
        if(wheaterAlert.getTemperatureCelsius() >= 35
                || wheaterAlert.getTemperatureCelsius() <= 0
                || wheaterAlert.getMoisturePercentage() <= 15)
            emailService.sendAlertByEmail(wheaterAlert);
    }
}
