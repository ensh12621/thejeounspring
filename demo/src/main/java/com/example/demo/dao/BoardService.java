package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;
import com.example.demo.model.File;
import com.example.demo.model.Reply;

import jakarta.servlet.http.HttpSession;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private HttpSession session;
	
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
	
	public Map<String, Object> getBoardAticle(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			
			int nAffected = boardMapper.viewCnt(requestMap);
			if(nAffected != 1) {
				throw new RuntimeException("조회수 증가 실패");
			}
			
			
			List<Reply> replyList = boardMapper.getReplyList(requestMap);
			resultMap.put("replyList", replyList);
			
			// 오브젝트 -> 문자열 -> 숫자
			Board info = boardMapper.selectBoard(requestMap);
			resultMap.put("info", info);
			
			
			
			List<File> fileList = boardMapper.selectBoardFile(requestMap);
			resultMap.put("fileList", fileList);
			
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
	

	public Map<String, Object> addBoardArticle(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		try{
			if(session.getAttribute("sessionId") != null) {
				requestMap.put("sessionId", session.getAttribute(("sessionId")));
				int nAffected = boardMapper.insertBoard(requestMap);
				System.out.println(requestMap);
				if(nAffected == 1) {
					resultMap.put("result", "success");	
					resultMap.put("message", Message.INSERT_SUCCESS);
					resultMap.put("boardNo", requestMap.get("boardNo"));
				}else{
					resultMap.put("result", "failed");
					resultMap.put("message", Message.INSERT_FAILED);
				}
			}else {
				resultMap.put("result", "failed");
				resultMap.put("message", "세션이 만료되었습니다");
			}
			
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.INSERT_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}

	public Map<String, Object> editBoardArticle(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			int nAffected = boardMapper.updateBoard(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.INSERT_SUCCESS);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.UPDATE_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.UPDATE_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}
	
	
	public Map<String, Object> addBoardFile(HashMap<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		try{
			int nAffected = boardMapper.insertBoardFile(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.INSERT_SUCCESS);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.INSERT_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.INSERT_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}

	public Map<String, Object> addBoardReply(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			
			requestMap.put("sessionId", session.getAttribute(("sessionId")));
			int nAffected = boardMapper.insertBoardReply(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.REPLY_SUCCESS);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.REPLY_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.REPLY_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}

	public Map<String, Object> editBoardReply(HashMap<String, Object> requestMap) {
		Map<String, Object> resultMap = new HashMap<>();
		try{
			
			requestMap.put("sessionId", session.getAttribute(("sessionId")));
			int nAffected = boardMapper.editBoardReply(requestMap);
			if(nAffected == 1) {
				resultMap.put("result", "success");	
				resultMap.put("message", Message.REPLY_UPDATED);
			}else{
				resultMap.put("result", "failed");
				resultMap.put("message", Message.REPLY_UPDATE_FAILED);
			}
			return resultMap;
		}catch(Exception e){
			resultMap.put("result", "failed");
			resultMap.put("message", Message.REPLY_UPDATE_FAILED);
			e.printStackTrace();
			return resultMap;
		}
	}

	public Map<String, Object> removeBoardReply(HashMap<String, Object> requestMap) {
		Map<String, Object> map = new HashMap<>();	
		
		Reply reply = boardMapper.selectBoardReply(requestMap);
		String sessionId = (String)session.getAttribute("sessionId");
		System.out.println("compare ["+sessionId+", "+reply.getUserid()+"]");
		if(!reply.getUserid().equals(sessionId)) {
			map.put("result", "failed");
			map.put("message", Message.DELETE_REPLY_FAILED);
		}else {
			int nAffected = boardMapper.deleteBoardReply(requestMap);
			
			if(nAffected == 1) {
				map.put("result", "success");
				map.put("message", Message.DELETE_REPLY_SUCCESS);
			}else {
				map.put("result", "failed");	
				map.put("message", Message.DELETE_REPLY_FAILED);
			}
			
		}
		
		return map;
	}
	
	
}
