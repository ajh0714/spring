package com.ict.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data		//getter,setter,toString,equals
public class BbsReplyVO {
	private int reply_no;
	private int news_no;
	private String comment;
	private String userid;
	private String writedate;
}
