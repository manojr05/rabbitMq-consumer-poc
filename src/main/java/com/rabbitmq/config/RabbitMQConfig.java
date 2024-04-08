package com.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar() {
        RabbitListenerEndpointRegistrar registrar = new RabbitListenerEndpointRegistrar();
        registrar.setEndpointRegistry(rabbitListenerEndpointRegistry());
        registrar.setBeanFactory(beanFactory());
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
        return registrar;
    }

    @Bean
    public RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry() {
        return new RabbitListenerEndpointRegistry();
    }

    @Bean
    public ConfigurableBeanFactory beanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
