package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.ProfessorMapper;
import com.example.demo.model.Professor;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorMapper professorMapper;

	public Map<String, Object> getProfessorList(Map<String, Object> requestMap) {
		List<Professor> list = professorMapper.selectProfessorList(requestMap);
		System.out.println(requestMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		List<Professor> deptList =professorMapper.selectDepartmentList(requestMap);
		resultMap.put("deptList", deptList);
		resultMap.put("result", "success");
		return resultMap;
	}

	public Map<String, Object> removeProfessor(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		int nAffected = professorMapper.deleteProfessor(requestMap);
		if (nAffected == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "failed");
		}

		return resultMap;
	}
	
	public Map<String, Object> addProfessor(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		int nAffected = professorMapper.insertProfessor(requestMap);
		if (nAffected == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "failed");
		}

		return resultMap;
	}

	public Map<String, Object> getProfessor(HashMap<String, Object> requestMap) {
		Professor info = professorMapper.selectProfessor(requestMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("info", info);
		return resultMap;
	}
	
	public Map<String, Object> getDepartmentList(HashMap<String, Object> requestMap) {
		List<Professor> deptList = professorMapper.selectDepartmentList(requestMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("deptList", deptList);
		resultMap.put("result", "success");
		return resultMap;
	}

	public Map<String, Object> editProfessor(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			int nAffected = professorMapper.updateProfessor(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");
			}else {
				resultMap.put("result", "failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			resultMap.put("result", "failed");
		}
		
		return resultMap;
	}
	

}
