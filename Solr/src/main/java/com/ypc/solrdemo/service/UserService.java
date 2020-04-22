package com.ypc.solrdemo.service;

import java.util.List;
import java.util.Map;

import com.ypc.solrdemo.entity.User;

public interface UserService {

	User queryById(int id);
	
	List<User> queryAll ();

	Map<String, Object> insertAndUpdate(User user);

	Map<String, Object> deleteUserById(int id);

}
