package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dao.ConsumerDao;

@RequestMapping
@RestController
public class ConsumerController {

	@Autowired
	private ConsumerDao consumerDao;
	@RequestMapping("/getName")
	public String getName() {
	return	consumerDao.getNmae();
	}
}
