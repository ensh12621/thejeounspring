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

import com.example.demo.dao.DefaultService;
import com.google.gson.Gson;

@Controller
public class DefaultController {
	
	
	@Autowired
	private DefaultService defaultService;
	

	@RequestMapping("/default.do")
	public String test(Model model) throws Exception{
		return "/test";
	}
	
	@RequestMapping(value = "/default.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		System.out.println("ㅎㅎㅎ");
		Map<String, Object> resultMap = defaultService.getUserList();
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/stu-remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeStudent(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = defaultService.removeUser(map);
		System.out.println(map.toString());
		return new Gson().toJson(resultMap); 
	}
	
}
