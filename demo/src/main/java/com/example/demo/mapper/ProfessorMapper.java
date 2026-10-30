package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Professor;

@Mapper
public interface ProfessorMapper {
	// 교수 목록
//	List<Professor> selectProfessorList();

	List<Professor> selectProfessorList(Map<String, Object> requestMap);

	int deleteProfessor(HashMap<String, Object> requestMap);
	
	int insertProfessor(Map<String, Object> requestMap);

	Professor selectProfessor(HashMap<String, Object> requestMap);
	
	List<Professor> selectDepartmentList(Map<String, Object> requestMap);

	int updateProfessor(HashMap<String, Object> requestMap);
	
}
