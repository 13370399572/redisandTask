package com.mq.service;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverService {

	//监听队列的名称，也就是客户端声明的queue.string
    @RabbitListener(queues = "queue.string")
    public void receiveStringQueue(String message) {
        System.out.println("Received string<" + message + ">");
        System.out.println("Received string<" + new Date(System.currentTimeMillis()) + ">");
       
    }
}
