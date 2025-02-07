package com.ict.myapp.dao;

import java.util.List;

import com.ict.myapp.vo.BbsVO;
import com.ict.myapp.vo.PagingVO;

public interface BbsDAO {
	//�������
	public int bbsInsert(BbsVO vo);
	//�ѷ��ڵ��
	public int bbsTotalRecord(PagingVO pVO);
	//���ڵ弱��(���)
	public List<BbsVO> bbsSelect(PagingVO pVO);
	//��ȸ������
	public void hitCount(int news_no);
	//���ڵ弱��
	public BbsVO bbsViewSelect(int news_no);
	//��������
	public int bbsUpdate(BbsVO vo);
	//��������
	public int bbsDelete(int news_no);
}