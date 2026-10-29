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

import com.example.demo.dao.EmpService;
import com.google.gson.Gson;

@Controller
public class EmpController {

	@Autowired
	private EmpService empService;
	
	@RequestMapping("/emp/list.do")
	public String empList() {
		return "/emp/list";
	}

	@RequestMapping(value = "/emp/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String listEmp(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = empService.getEmpList();
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/emp/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeEmp(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = empService.removeEmp(map);
		return new Gson().toJson(resultMap); 
	}
	
}
