package com.ict.myapp.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.myapp.dao.MemberDAO;
import com.ict.myapp.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;
	
	@Override
	public int memberInsert(MemberVO vo) {
		return dao.memberInsert(vo);
	}
	@Override
	public MemberVO loginOk(String userid, String userpwd) {
		return dao.loginOk(userid, userpwd);
	}
	@Override
	public MemberVO memberSelect(String userid) {
		// TODO Auto-generated method stub
		return dao.memberSelect(userid);
	}
	@Override
	public int memberUpdate(MemberVO vo) {
		return dao.memberUpdate(vo);
	}
	@Override
	public int idDuplicate(String userid) {
		// TODO Auto-generated method stub
		return dao.idDuplicate(userid);
	}
}
