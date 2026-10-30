package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public Map<String, Object> login(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = null; 
		
		try {
			User user = userMapper.selectUser(requestMap);
			resultMap = new HashMap<>();
			if(user != null) {
				resultMap.put("result", "success");
				resultMap.put("message", user.getUserName() + "님 환영합니다");
			}else {
				resultMap.put("result", "failed");
				resultMap.put("message", Message.LOGIN_FAIL);
			}
			return resultMap;
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "500");
			resultMap.put("message", Message.DB_ERR);
			return resultMap;
		}
		
	}

	public Map<String, Object> addUser(HashMap<String, Object> requestMap) {
		int nAffected = userMapper.insertUser(requestMap);
		Map<String, Object> resultMap = new HashMap<>();
		if(nAffected == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failed");
		}
		
		return resultMap;
	}
	
}
