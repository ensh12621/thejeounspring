package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserService;
import com.google.gson.Gson;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.do")
	public String loginPage() {
		return "/user/login";
	}
	
	@RequestMapping("/join.do")
	public String joinPage() {
		return "/user/join";
	}
	
	@RequestMapping(value = "/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeStudent(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = userService.login(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/join.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String joinUserAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = userService.addUser(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
}
