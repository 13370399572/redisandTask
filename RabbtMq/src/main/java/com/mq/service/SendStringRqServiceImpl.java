package com.mq.service;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendStringRqServiceImpl  implements SendStringRqService{

	@Autowired
    private RabbitMessagingTemplate rabbitTemplate;
 
    @Override
    public void sendString(String message) {
          rabbitTemplate.convertAndSend("exchange", "queue.string", message);
    }


}
