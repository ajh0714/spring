package com.ict.myapp.service;

import java.util.List;

import com.ict.myapp.vo.BbsReplyVO;

public interface BbsReplyService {
	//´ñ±Û µî·Ï
		public int replyInsert(BbsReplyVO vo);
		//´ñ±Û ¸ñ·Ï
		public List<BbsReplyVO> replyList(int news_no);
		//´ñ±Û ¼öÁ¤
		public int replyUpdate(BbsReplyVO vo);
		//´ñ±Û »èÁ¦
		public int replyDelete(int reply_no);	
}
