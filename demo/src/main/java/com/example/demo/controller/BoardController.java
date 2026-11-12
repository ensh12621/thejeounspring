package com.example.demo.controller;

import java.io.File;
import java.util.Calendar;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list.do")
	public String boardList() {
		return "/board/list";
	}

	@GetMapping("/add.do")
	public String boardAdd() {
		return "/board/add";
	}

	@RequestMapping("/view.do")
	public String boardView(Model model, @RequestParam HashMap<String, Object> requestMap) {
		System.out.println(requestMap);
		model.addAttribute("boardNo", requestMap.get("boardNo"));
		return "/board/view";
	}

	@RequestMapping("/edit.do")
	public String boardEdit(Model model, @RequestParam HashMap<String, Object> requestMap) {
		model.addAttribute("boardNo", requestMap.get("boardNo"));
		return "/board/edit";
	}
	


	@RequestMapping(value = "/add-reply.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addReplyAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.addBoardReply(requestMap);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/edit-reply.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editReplyAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.editBoardReply(requestMap);
		return new Gson().toJson(resultMap);
	}


	@RequestMapping(value = "/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardEditAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.editBoardArticle(requestMap);
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardListAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.getAllBoardAticles(requestMap);
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/view.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.getBoardAticle(requestMap);
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
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String json = map.get("selectItemList").toString();
		ObjectMapper mapper = new ObjectMapper();
		List<Object> list = mapper.readValue(json, new TypeReference<List<Object>>() {
		});
		map.put("list", list);
		System.out.println(map);

		resultMap = boardService.removeBoardArticleAll(map);

		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/remove-reply.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeReplyAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.removeBoardReply(requestMap);
		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addBoardAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = boardService.addBoardArticle(requestMap);
		return new Gson().toJson(resultMap);
	}

	// controller
	@RequestMapping("/fileUpload.dox")
	@ResponseBody
	public String result(@RequestParam("file1") MultipartFile multi, @RequestParam("idx") int idx,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		String url = null;
		String path = "c:\\img";
		try {

			// String uploadpath = request.getServletContext().getRealPath(path);
			String uploadpath = path;
			String originFilename = multi.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			long size = multi.getSize();
			String saveFileName = genSaveFileName(extName);

			System.out.println("uploadpath : " + uploadpath);
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			String path2 = System.getProperty("user.dir");
			System.out.println("Working Directory = " + path2 + "\\src\\webapp\\img");
			if (!multi.isEmpty()) {
				File file = new File(path2 + "\\src\\main\\webapp\\img", saveFileName);
				multi.transferTo(file);

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", saveFileName);
				map.put("path", "../img/" + saveFileName);
				map.put("idx", idx);
				map.put("orgName", originFilename);
				map.put("extName", extName);
				map.put("size", size);
				Map<String, Object> resultMap = boardService.addBoardFile(map); 
				
				model.addAttribute("filename", multi.getOriginalFilename());
				model.addAttribute("uploadPath", file.getAbsolutePath());

				return new Gson().toJson(resultMap);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:list.do";
	}

	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

}
