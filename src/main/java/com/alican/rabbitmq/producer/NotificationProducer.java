package com.alican.rabbitmq.producer;

import com.alican.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Service
public class NotificationProducer {

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Value("${sr.rabbit.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("rabbit mq üzerinden yollanan mesaj.");
        notification.setSeen(Boolean.FALSE);
        sendToQueue(notification);
    }

    public void sendToQueue(Notification notification) {
        System.out.println("Notification Send ID: " + notification.getId());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, notification);
    }
}
