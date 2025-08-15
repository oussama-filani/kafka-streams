package com.filani.clickproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClickController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC_NAME = "clicks";

    @PostMapping("/click")
    public void recordClick() {
        String userId = "user-" + UUID.randomUUID().toString().substring(0, 4);
        String message = "click";

        kafkaTemplate.send(TOPIC_NAME, userId, message);
        System.out.println("Message envoyé au topic 'clicks' pour l'utilisateur: " + userId);
    }
}