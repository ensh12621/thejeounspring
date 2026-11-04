package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	public Map<String, Object> getAllBoardAticles(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			List<Board> boardList = boardMapper.selectBoardList(requestMap);
			resultMap.put("result", "success");
			resultMap.put("boardList", boardList);
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			e.printStackTrace();
			return resultMap;
		}

		
		
	}

	public Map<String, Object> removeBoardArticle(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		try{
			int nAffected = boardMapper.deleteBoard(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.DELETE_SUCCESS);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.DELETE_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.DELETE_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}
	
	public Map<String, Object> removeBoardArticleAll(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		try{
			int nAffected = boardMapper.deleteBoardAll(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.DELETE_SUCCESS);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.DELETE_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.DELETE_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}
	
	
	
}
