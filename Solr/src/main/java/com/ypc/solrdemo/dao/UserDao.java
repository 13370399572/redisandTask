package com.ypc.solrdemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ypc.solrdemo.entity.User;

@Mapper
public interface UserDao {
	User queryById (@Param("id")Integer id);
	
	List<User> queryAll ();
	
	int insertUser(User user);
	
	int deleteById(@Param("id") Integer id);
}
