package com.rabbitmq.controller;

import com.rabbitmq.service.mq.DynamicRabbitListenerConfigurer;
import com.rabbitmq.service.mq.MyMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
public class RabbitListenerController {

    private final DynamicRabbitListenerConfigurer dynamicConfigurer;

    @PostMapping("/add-listener")
    public ResponseEntity<Map<String, String>> addListener(@RequestBody String queueName) {

        dynamicConfigurer.addRabbitListener(queueName, new MyMessageHandler(), "handleMessage");

        Map<String, String> response = new HashMap<>();
        response.put("message", "RabbitMQ listener added successfully");
        return ResponseEntity.ok(response);
    }
}

