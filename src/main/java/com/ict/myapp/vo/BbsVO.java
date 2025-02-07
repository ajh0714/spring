package com.ict.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BbsVO {
	private int news_no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
	private String ip;
	
	
}
