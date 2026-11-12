package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	private String boardNo;
	private String title;
	private String contents;
	private String userId;
	private int cnt;
	private String kind;
	private String cDateTime;
	private String uDateTime;
	private String userName;
	
}
