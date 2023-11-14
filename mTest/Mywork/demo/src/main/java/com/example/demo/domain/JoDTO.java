package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoDTO {
	private int jno;
	private String jname;
	private String id;
	private String project;
	private String slogan;
	
	private String cname; // 조장이름, 필요시사용
	
} //class
