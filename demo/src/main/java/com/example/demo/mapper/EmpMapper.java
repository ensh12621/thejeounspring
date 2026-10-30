package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Emp;

@Mapper
public interface EmpMapper {

	List<Emp> selectEmpList(Map<String, Object> requestMap);
	int deleteEmp(HashMap<String, Object> requestMap);
	int insertEmployee(HashMap<String, Object> requestMap);
	Emp selectEmp(HashMap<String, Object> requestMap);
	List<Emp> selectSalGradeList(HashMap<String, Object> requestMap);

}
