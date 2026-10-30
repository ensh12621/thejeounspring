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
	
	@RequestMapping("/emp/add.do")
	public String addEmp() {
		return "/emp/add";
	}
	
	@RequestMapping("/emp/view.do")
	public String addEmp(Model model, @RequestParam HashMap<String, Object> map) {
		model.addAttribute("empNo", map.get("empNo"));
		return "/emp/view";
	}
	
	
	@RequestMapping(value = "/emp/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String listEmp(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = empService.getEmpList(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/emp/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeEmp(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = empService.removeEmp(map);
		return new Gson().toJson(resultMap); 
	}
	

	@RequestMapping(value = "/emp/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addEmployee(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = empService.addEmployee(requestMap);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/emp/check-empNo-duplication.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkEmpNoDuplication(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = empService.isDuplicatedEmpNo(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/emp/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getEmployeeAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = empService.getEmployee(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/emp/get-salgrade-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSalgradeList(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = empService.getSalGradeList(requestMap);
		System.out.println("getSalgradeList...");
		System.out.print(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
	
}
