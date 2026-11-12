package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.File;
import com.example.demo.model.Product;

@Mapper
public interface ProductMapper {
	// 제품 목록
	List<Product> selectProductList(HashMap<String, Object> map);
	
	// 카테고리 목록
	List<Product> selectCategoryList(HashMap<String, Object> map);
	Product selectProduct(HashMap<String, Object> map);

	int updateProductForRemoval(HashMap<String, Object> requestMap);

	int insertProductFile(HashMap<String, Object> requestMap);

	int insertProduct(HashMap<String, Object> requestMap);

	List<File> selectProductFile(HashMap<String, Object> requestMap);

	List<Product> selectProductWithSumPrice(HashMap<String, Object> requestMap);
}