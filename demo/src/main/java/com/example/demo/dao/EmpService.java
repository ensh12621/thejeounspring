package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.EmpMapper;
import com.example.demo.model.Emp;

@Service
public class EmpService {
	
	@Autowired
	private EmpMapper empMapper;

	public Map<String, Object> getEmpList() {
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Emp> list = empMapper.selectEmpList();
		resultMap.put("result", "success");
		resultMap.put("list", list);
		
		return resultMap;
	}

	public Map<String, Object> removeEmp(HashMap<String, Object> requestMap) {
		int nAffected = empMapper.deleteEmp(requestMap);
		Map<String, Object> resultMap = new HashMap<>();
		if(nAffected == 1) {
			resultMap.put("result", "success");	
		}else {
			resultMap.put("result", "failed");
		}
		
		return resultMap;
	}

	
}
