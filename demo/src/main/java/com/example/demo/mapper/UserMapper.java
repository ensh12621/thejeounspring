package com.example.demo.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {

	User selectUser(HashMap<String, Object> requestMap);

	int insertUser(HashMap<String, Object> requestMap);

}
