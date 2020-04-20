package com.mq.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mq.service.SendStringRqService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitDemoApplicationTests {
	@Autowired
	private SendStringRqService sendStringRqService;

	@Test
	public void SendStringService() {
		sendStringRqService.sendString(new Date(System.currentTimeMillis())+" ------> hello world");
	}


}
