package com.ict.myapp.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter + setter + tostring + eqauls
@NoArgsConstructor
@AllArgsConstructor
public class DataVO {
	private int no;
	private String subject;
	private	String content;
	private	String userid;
	private	int hit;
	private String writedate;
	private MultipartFile mf;  // 업로드 된 파일명
	private String filename;
}
