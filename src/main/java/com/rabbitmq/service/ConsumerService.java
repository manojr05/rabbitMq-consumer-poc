package com.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "MAC001.messageQueue")
    public void stringConsumer(String message){
        log.info("Received message: {}", message);
        rabbitTemplate.convertAndSend("MAC001", "acknowledgeRoute", message);
    }

}
