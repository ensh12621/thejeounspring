package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Board;

@Mapper
public interface BoardMapper {

	List<Board> selectBoardList(HashMap<String, Object> requestMap);

	int deleteBoard(HashMap<String, Object> requestMap);
	
	// 여러 개 삭제
	int deleteBoardAll(HashMap<String, Object> requestMap);
	
	
	int selectBoardCount(HashMap<String, Object> requestMap);
	
}
