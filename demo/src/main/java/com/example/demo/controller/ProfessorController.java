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

import com.example.demo.dao.ProfessorService;
import com.google.gson.Gson;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	
	@RequestMapping("/professor/list.do")
	public String test(Model model) throws Exception{
		return "/professor/list";
	}

	@RequestMapping("/professor/add.do")
	public String addProfessor(Model model) throws Exception{
		return "/professor/add";
	}
	
	@RequestMapping("/professor/view.do")
	public String viewProfessor(Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map.toString());
		model.addAttribute("profNo", map.get("profNo"));
		return "/professor/view";
	}
	
	@RequestMapping("/professor/edit.do")
	public String editProfessor(Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map.toString());
		model.addAttribute("profNo", map.get("profNo"));
		return "/professor/edit";
	}
	
	
	@RequestMapping(value = "/professor/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeStudent(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = professorService.getProfessorList(map);
		System.out.println("list.dox 컨트롤러 진입완료");
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/professor/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeProfessor(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = professorService.removeProfessor(map);
		return new Gson().toJson(resultMap); 
	}

	@RequestMapping(value = "/professor/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addProfessor(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = professorService.addProfessor(map);
		return new Gson().toJson(resultMap); 
	}

	@RequestMapping(value = "/professor/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String professorInfo(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = professorService.getProfessor(map);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/professor/dept-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String departmentList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		Map<String, Object> resultMap = professorService.getDepartmentList(map);
		return new Gson().toJson(resultMap); 
	}
	
	
	@RequestMapping(value = "/professor/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editProfessorAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = professorService.editProfessor(requestMap);
		return new Gson().toJson(resultMap); 
	}
}
