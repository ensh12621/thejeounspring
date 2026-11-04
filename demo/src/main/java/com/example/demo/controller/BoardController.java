package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BoardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list.do")
	public String boardList() {
		return "/board/list";
	}

	@RequestMapping(value = "/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardListAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.getAllBoardAticles(requestMap);
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeBoardAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.removeBoardArticle(requestMap);
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/remove-all.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeAll(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String json = map.get("selectItemList").toString();
		ObjectMapper mapper = new ObjectMapper();
		List<Object> list = mapper.readValue(json, new TypeReference<List<Object>>() {});
		map.put("list", list);
		System.out.println(map);

//		resultMap = boardService.removeBoard(map);

		return new Gson().toJson(resultMap);
	}

}
