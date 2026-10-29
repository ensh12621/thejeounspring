package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DefaultMapper;
import com.example.demo.model.User;

@Service
public class DefaultService {

	
	@Autowired
	private DefaultMapper defaultMapper;
	
	public Map<String, Object> getUserList() {
		Map<String, Object> map = new HashMap<>();
		List<User> list = defaultMapper.selectUserList();
		map.put("list", list);
		map.put("result", "success");
		return map;
	}
	
	public Map<String, Object> removeUser(Map<String, Object> requestMap) {
		Map<String, Object> map = new HashMap<>();
		defaultMapper.deleteUser(requestMap);
		
		if(defaultMapper.deleteUser() == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "failed");
		}
		
		return map;
		
	}
	
}
