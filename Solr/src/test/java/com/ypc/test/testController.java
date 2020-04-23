package com.ypc.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.ypc.solrdemo.entity.User;
import com.ypc.solrdemo.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class testController {

	@Autowired
	private UserService userService;

	@Test
	public void insertAndUpdate() {
		User user = new User();
		user.setAge(11);
		user.setAddress("中国");
		user.setDescription("如花食欲");
		user.setMobile("13370399572");
		user.setPeriod("xxx");
		user.setSex("人药");
		user.setUserName("王凯父亲大单大单");
		userService.insertAndUpdate(user);
	}
    
	@Test
	public void queryById() {
		User user= userService.queryById(2);
		Gson gson = new Gson();
	    String xxString= 	gson.toJson(user);
	    System.out.println(xxString);
		
	}
    
	@Test
	public void queryAll() {
		List<User> user= userService.queryAll();
		Gson gson = new Gson();
	    String xxString= 	gson.toJson(user);
	    System.out.println(xxString);
	}
	
	@Test
	public void deleteUserById() {
		userService.deleteUserById(2);
		System.out.println("删除成功");
	}
	
}
