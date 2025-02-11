package com.ict.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ict.myapp.dao.BbsReplyDAO;
import com.ict.myapp.vo.BbsReplyVO;

@Service
public class BbsReplyServiceImpl implements BbsReplyService {
	@Inject
	BbsReplyDAO dao;

	@Override
	public int replyInsert(BbsReplyVO vo) {
		// TODO Auto-generated method stub
		return dao.replyInsert(vo);
	}

	@Override
	public List<BbsReplyVO> replyList(int news_no) {
		// TODO Auto-generated method stub
		return dao.replyList(news_no);
	}

	@Override
	public int replyUpdate(BbsReplyVO vo) {
		// TODO Auto-generated method stub
		return dao.replyUpdate(vo);
	}

	@Override
	public int replyDelete(int reply_no) {
		// TODO Auto-generated method stub
		return dao.replyDelete(reply_no);
	}
}
