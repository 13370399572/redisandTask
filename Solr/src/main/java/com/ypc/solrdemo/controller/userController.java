package com.ypc.solrdemo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ypc.solrdemo.entity.User;
import com.ypc.solrdemo.service.UserService;

@Controller
public class userController {
	
	private UserService userService;
	
	@ResponseBody
    @PostMapping("/insert")
    public Map<String, Object> insertUser(@RequestBody User user) {
        Map<String,Object> result = userService.insertAndUpdate(user);
        return result;
    }

}
