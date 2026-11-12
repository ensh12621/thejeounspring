package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

	
	

	@RequestMapping("/map/map1.do")
	public String map1Page() {
		return "/map/map1";
	}
	
	@RequestMapping("/map/map2.do")
	public String map2Page() {
		return "/map/map2";
	}
	
	@RequestMapping("/map/map3.do")
	public String map3Page() {
		return "/map/map3";
	}

	@RequestMapping("/map/map99.do")
	public String map99Page() {
		return "/map/map99";
	}
	
}
