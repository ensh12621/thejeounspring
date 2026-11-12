package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private HttpSession session;

	public Map<String, Object> login(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		try {
			User user = userMapper.selectUser(requestMap);
			if(user != null) {

				if(passwordEncoder.matches((String)requestMap.get("userPwd"), user.getPwd())) {
					session.setAttribute("sessionId", user.getUserId());
					session.setAttribute("sessionName", user.getUserName());
					session.setAttribute("sessionRole", user.getRole());
//					session.invalidate(); // 세션 모든 정보 삭제
					
					if(user.getRole() != null && user.getRole().equals("A")) {
						resultMap.put("url", "/product/list.do");
					}else {
						resultMap.put("url", "/board/list.do");
					}
					
					
					resultMap.put("result", "success");
					resultMap.put("message", user.getUserName() + "님 환영합니다");
				}else {
					resultMap.put("result", "failed");
					resultMap.put("message", Message.LOGIN_FAIL);
				}	
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
		
		
		
		System.out.println("해시전 패스워드 : " + (String)requestMap.get("userPwd")) ;
		requestMap.put("hashPwd", passwordEncoder.encode((String)requestMap.get("userPwd")));
		requestMap.put("userRole", "C");
		System.out.println("해시전 패스워드 : " + (String)requestMap.get("userPwd")) ;
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
