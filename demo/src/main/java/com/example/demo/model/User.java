package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


	private String userId;
	private String pwd;
	private String userName;
	private String addr;
	private String role;
	
	
}
