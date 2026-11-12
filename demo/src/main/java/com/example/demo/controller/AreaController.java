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

import com.example.demo.dao.AreaService;
import com.google.gson.Gson;

@Controller
public class AreaController {

	@Autowired
	private AreaService areaService;	
	
	@GetMapping("/weather.do")
	public String weatherPage() {
		return "/area/weather";
	}

	@RequestMapping(value = "/area/si.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String siAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = areaService.getSiList(requestMap);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/area/gu.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String guAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = areaService.getGuList(requestMap);
		return new Gson().toJson(resultMap);
	}
	

	@RequestMapping(value = "/area/dong.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String dongAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = areaService.getDongList(requestMap);
		return new Gson().toJson(resultMap);
	}


}
