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
			
			// 오브젝트 -> 문자열 -> 숫자
			String limit = (String)requestMap.get("limit");
			String offset = (String) requestMap.get("offset");
			
			requestMap.put("limit", Integer.parseInt(limit));
			requestMap.put("offset", Integer.parseInt(offset));
			
			
			List<Board> boardList = boardMapper.selectBoardList(requestMap);
			resultMap.put("boardList", boardList);

			int count = boardMapper.selectBoardCount(requestMap);
			resultMap.put("count", count);
			
			resultMap.put("result", "success");
			
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
