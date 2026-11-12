package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.File;
import com.example.demo.model.Product;

@Service
public class ProductService {
	@Autowired
	ProductMapper productMapper;
	
	public HashMap<String, Object> getProductList(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap();
		try {	
			
			List<Product> list = productMapper.selectProductList(map);
			List<Product> categoryList = productMapper.selectCategoryList(map);
			
			resultMap.put("list", list);
			resultMap.put("categoryList", categoryList);
			resultMap.put("result", "success");
		} catch (Exception e) {
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		return resultMap;
	}
	
	public HashMap<String, Object> getProduct(HashMap<String, Object> requestMap) {
		HashMap<String, Object> resultMap = new HashMap<>();
		try {	
			Product info = productMapper.selectProduct(requestMap);
			System.out.println();
			resultMap.put("info", info);
			
			List<File> fileList = productMapper.selectProductFile(requestMap);
			resultMap.put("fileList", fileList);
			
			resultMap.put("result", "success");
		} catch (Exception e) {
			resultMap.put("result", "fail");
			System.out.println(e.getMessage());
		}
		return resultMap;
	}

	public HashMap<String, Object> removeProduct(HashMap<String, Object> requestMap) {
		int n = productMapper.updateProductForRemoval(requestMap);
		HashMap<String, Object> resultMap = new HashMap<>();
		if(n == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "failed");
		}
		
		return resultMap;
	}

	public List<Product> getProductCategoryList(HashMap<String, Object> requestMap) {
		return productMapper.selectCategoryList(requestMap);
	}

	public Map<String, Object> addProductFile(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			int nAffected = productMapper.insertProductFile(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.INSERT_SUCCESS);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.INSERT_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.INSERT_FAILED);
			e.printStackTrace();
			return resultMap;
		}
		
	}

	
	public Map<String, Object> addProduct(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		try{
			int nAffected = productMapper.insertProduct(requestMap);
			System.out.println(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.INSERT_SUCCESS);
				System.out.println("productId: " + requestMap.get("productId"));
				resultMap.put("productNo", requestMap.get("productId"));
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.INSERT_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.INSERT_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}

	public Map<String, Object> getProductWithSumPrice(HashMap<String, Object> requestMap) {
		List<Product> productList = productMapper.selectProductWithSumPrice(requestMap);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", "success");
		resultMap.put("productList", productList);
		
		return resultMap;
	}
	
	
}