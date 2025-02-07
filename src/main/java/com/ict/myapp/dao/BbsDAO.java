package com.ict.myapp.dao;

import java.util.List;

import com.ict.myapp.vo.BbsVO;
import com.ict.myapp.vo.PagingVO;

public interface BbsDAO {
	//뉴스등록
	public int bbsInsert(BbsVO vo);
	//총레코드수
	public int bbsTotalRecord(PagingVO pVO);
	//레코드선택(목록)
	public List<BbsVO> bbsSelect(PagingVO pVO);
	//조회수증가
	public void hitCount(int news_no);
	//레코드선택
	public BbsVO bbsViewSelect(int news_no);
	//뉴스수정
	public int bbsUpdate(BbsVO vo);
	//뉴스삭제
	public int bbsDelete(int news_no);
}