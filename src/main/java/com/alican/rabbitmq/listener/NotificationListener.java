package com.alican.rabbitmq.listener;

import com.alican.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "my-queue")
    public void handleMessage(Notification notification) {
        System.out.println("Message received..");
        System.out.println(notification.toString());
    }
}
