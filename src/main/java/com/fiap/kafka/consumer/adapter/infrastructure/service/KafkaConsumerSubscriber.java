package com.fiap.kafka.consumer.adapter.infrastructure.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.kafka.consumer.domain.domain.WheaterAlert;
import com.fiap.kafka.consumer.domain.usecase.CheckWheatherUseCase;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class KafkaConsumerSubscriber {

    private Consumer<String, String> consumer;

    private CheckWheatherUseCase checkWheatherUseCase;

    @Autowired
    KafkaConsumerSubscriber(CheckWheatherUseCase checkWheatherUseCase){
        this.checkWheatherUseCase = checkWheatherUseCase;
        createConsumer();
        poolTopic();
    }

    private void poolTopic() {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                consumer.commitAsync();

                try {
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(record.value());
                        ObjectMapper mapper = new ObjectMapper();
                        WheaterAlert wheaterAlert = mapper.readValue(record.value(), WheaterAlert.class);
                        checkWheatherUseCase.execute(wheaterAlert.checkValues());
                    }
                } catch (Exception e){
                    e.printStackTrace(); // TODO add log
                }
            }
    }

    public void createConsumer() {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "aquele-id");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Collections.singleton("meu-primeiro-topic"));

    }
}
