package com.fiap.kafka.consumer.adapter.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.kafka.consumer.adapter.gateway.repository.EmailRepository;
import com.fiap.kafka.consumer.domain.domain.WheaterAlert;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.fiap.kafka.consumer.adapter.constants.AlertConstants.CONTENT_TYPE_TEXT_HTML;

@Component
public class EmailService {

    EmailRepository emailRepository;

    @Autowired
    EmailService(EmailRepository emailRepository){
        this.emailRepository = emailRepository;
    }

    @SneakyThrows
    public void sendAlertByEmail(WheaterAlert wheaterAlert){

        ObjectMapper mapper = new ObjectMapper();
        String message = wheaterAlert.toString();// mapper.writeValueAsString(wheaterAlert);

        emailRepository.sendEmail("lucas.vinicius.rodrigues@hotmail.com", CONTENT_TYPE_TEXT_HTML, message);
    }
}
