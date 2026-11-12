package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Board;
import com.example.demo.model.File;
import com.example.demo.model.Reply;

@Mapper
public interface BoardMapper {

	List<Board> selectBoardList(HashMap<String, Object> requestMap);

	int deleteBoard(HashMap<String, Object> requestMap);
	
	// 여러 개 삭제
	int deleteBoardAll(HashMap<String, Object> requestMap);
	
	int selectBoardCount(HashMap<String, Object> requestMap);
	
	int insertBoard(HashMap<String, Object> requestMap);
	
	Board selectBoard(HashMap<String, Object> requestMap);
	
	int updateBoard(HashMap<String, Object> requestMap);
	
	int insertBoardFile(HashMap<String, Object> requestMap);
	
	List<File> selectBoardFile(HashMap<String, Object> requestMap);

	int viewCnt(HashMap<String, Object> requestMap);

	List<Reply> getReplyList(HashMap<String, Object> requestMap);

	int insertBoardReply(HashMap<String, Object> requestMap);

	int editBoardReply(HashMap<String, Object> requestMap);

	Reply selectBoardReply(HashMap<String, Object> requestMap);

	int deleteBoardReply(HashMap<String, Object> requestMap);
}
