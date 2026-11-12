package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.AreaMapper;
import com.example.demo.model.Area;

@Service
public class AreaService {

	@Autowired
	private AreaMapper areaMapper;

	public Map<String, Object> getSiList(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();

		try {

			List<Area> siList = areaMapper.selectSiList(requestMap);
			resultMap.put("result", "success");
			resultMap.put("siList", siList);

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "failed");
			return resultMap;
		}
	}

	public Map<String, Object> getGuList(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();

		try {

			List<Area> guList = areaMapper.selectGuList(requestMap);
			resultMap.put("result", "success");
			resultMap.put("guList", guList);
			
			System.out.println(requestMap);
			
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "failed");
			return resultMap;
		}
	}

	public Map<String, Object> getDongList(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();

		try {

			List<Area> dongList = areaMapper.selectDongList(requestMap);
			resultMap.put("result", "success");
			resultMap.put("dongList", dongList);
			
			System.out.println(requestMap);
			
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "failed");
			return resultMap;
		}
	}
}
