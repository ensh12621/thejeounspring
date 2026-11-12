package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private String commentNo;
	private String contents;
	private String userid;
	private String cdatetime;
	private String userName;
}

