package com.ict.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ict.myapp.vo.BbsReplyVO;

@Mapper
@Repository
public interface BbsReplyDAO {
	//´ñ±Û µî·Ï
	public int replyInsert(BbsReplyVO vo);
	//´ñ±Û ¸ñ·Ï
	public List<BbsReplyVO> replyList(int news_no);
	//´ñ±Û ¼öÁ¤
	public int replyUpdate(BbsReplyVO vo);
	//´ñ±Û »èÁ¦
	public int replyDelete(int reply_no);
}
