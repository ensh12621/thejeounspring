package com.example.demo.controller;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ProductService;
import com.example.demo.model.Product;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;	

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@RequestMapping("/product/list.do")
	public String test(Model model) throws Exception{
		return "/product/list";
	}
	
	@RequestMapping("/product/add.do")
	public String addProduct(Model model) throws Exception{
		return "/product/add";
	}
	
	@RequestMapping("/product/view.do")
	public String productView(Model model,  @RequestParam HashMap<String, Object> requestMap) {
		model.addAttribute("productId", requestMap.get("productId"));
		return "/product/view";
	}
	
	@RequestMapping(value = "/product/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addBoardAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = productService.addProduct(requestMap);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/product/get-category-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCategoryListAjax(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); 
		List<Product> categoryList= productService.getProductCategoryList(map);
		resultMap.put("categoryList", categoryList);
		System.out.println(resultMap);
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping("/product/fileUpload.dox")
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
				Map<String, Object> resultMap = productService.addProductFile(map); 
				
				model.addAttribute("filename", multi.getOriginalFilename());
				model.addAttribute("uploadPath", file.getAbsolutePath());

				return new Gson().toJson(resultMap);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:list.do";
	}
	
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
	
	@RequestMapping(value = "/product/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String listProductAjax(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap = productService.getProductList(map);
		
		return new Gson().toJson(resultMap); 
	}
	

	@RequestMapping(value = "/product/view.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String viewProductAjax(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = productService.getProduct(map); 
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping(value = "/product/remove-product.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeProductAjax(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = productService.removeProduct(map); 
		return new Gson().toJson(resultMap); 
	}
	
	
//	
//	@RequestMapping(value = "/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public String boardEditAjax(Model model, @RequestParam HashMap<String, Object> requestMap) throws Exception {
//		Map<String, Object> resultMap = boardService.editBoardArticle(requestMap);
//		return new Gson().toJson(resultMap);
//	}



}
