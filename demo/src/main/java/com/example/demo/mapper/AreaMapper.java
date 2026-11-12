package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Area;

@Mapper
public interface AreaMapper {
	List<Area> selectSiList(Map<String, Object> requestMap);

	List<Area> selectGuList(HashMap<String, Object> requestMap);

	List<Area> selectDongList(HashMap<String, Object> requestMap);
}
