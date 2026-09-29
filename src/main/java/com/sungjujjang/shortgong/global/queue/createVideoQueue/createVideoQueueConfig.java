package com.sungjujjang.shortgong.global.queue.createVideoQueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class createVideoQueueConfig {

    @Bean
    public Queue createVideoQueue(
            @Value("${spring.rabbitmq.create_video.queue}") String queue,
            @Value("${spring.rabbitmq.create_video.dlx}") String dlx,
            @Value("${spring.rabbitmq.create_video.dlq}") String dlq
    ) {
        Map<String, Object> arguments = new HashMap<>();

        arguments.put("x-dead-letter-exchange", dlx);
        arguments.put("x-dead-letter-routing-key", dlq);

        return new Queue(
                queue,
                true,
                false,
                false,
                arguments
        );
    }

    @Bean
    public DirectExchange createVideoDlx(
            @Value("${spring.rabbitmq.create_video.dlx}") String dlx
    ) {
        return new DirectExchange(dlx);
    }

    @Bean
    public Queue createVideoDlq(
            @Value("${spring.rabbitmq.create_video.dlq}") String dlq
    ) {
        return new Queue(dlq);
    }

    @Bean
    public Binding createVideoDlqBinding(
            Queue createVideoDlq,
            DirectExchange createVideoDlx,
            @Value("${spring.rabbitmq.create_video.dlq}") String routingKey
    ) {
        return BindingBuilder
                .bind(createVideoDlq)
                .to(createVideoDlx)
                .with(routingKey);
    }

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter converter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}