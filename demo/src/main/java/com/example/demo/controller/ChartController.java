package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ProductService;
import com.google.gson.Gson;

@Controller
public class ChartController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/chart/chart1.do")
	public String char1() {
		return "/chart/chart1";
	}
	
	@RequestMapping("/chart/chart2.do")
	public String char2() {
		return "chart/chart2";
	}
	
	@RequestMapping("/chart/chart3.do")
	public String char3() {
		return "chart/chart3";
	}
	
	@RequestMapping(value = "/chart/get-chart-data3.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = productService.getProductWithSumPrice(requestMap);
		return new Gson().toJson(resultMap); 
	}
	
}
