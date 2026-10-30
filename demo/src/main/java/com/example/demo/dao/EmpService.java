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

	public Map<String, Object> getEmpList(Map<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(requestMap);
		List<Emp> list = empMapper.selectEmpList(requestMap);
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

	public Map<String, Object> addEmployee(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			int nAffected = empMapper.insertEmployee(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
			}else {
				resultMap.put("result", "failed");
			}

			return resultMap;
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "failed");
			return resultMap;
		}
	}

	public Map<String, Object> isDuplicatedEmpNo(HashMap<String, Object> requestMap) {
		Emp emp = empMapper.selectEmp(requestMap);
		Map<String, Object> resultMap = new HashMap<>();
		if(emp != null) {
			resultMap.put("isDuplicated", true);
		}else {
			resultMap.put("isDuplicated", false);
		}
		
		return resultMap;
	}

	public Map<String, Object> getEmployee(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Emp emp = empMapper.selectEmp(requestMap);
			resultMap.put("info", emp);
			System.out.println("employee 정보 조회 완료");
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "failed");
		}
		
		return resultMap;
	}

	public Map<String, Object> getSalGradeList(HashMap<String, Object> requestMap) {
		List<Emp> salgradeList = empMapper.selectSalGradeList(requestMap);
		Map<String, Object> map = new HashMap<>();
		map.put("salgradeList", salgradeList);
		map.put("result", "success");
		return map;
	}

	
}
