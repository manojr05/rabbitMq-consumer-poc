package com.rabbitmq.service;

import com.rabbitmq.model.Employee;
import com.rabbitmq.util.FileExportUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final FileExportUtil fileExportService;

    @RabbitListener(queues = "${rabbitmq.string.queue}")
    public void stringConsumer(String message){
        log.info("Received message: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.json.queue}")
    public void jsonConsumer(Employee message){
        log.info("Received message: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.file.queue}")
    public void fileConsumer(String encryptedFile){
        byte[] fileContent = Base64.getDecoder().decode(encryptedFile);
        fileExportService.exportFile(fileContent);
        log.info("Received message: {}", encryptedFile);
    }

}
