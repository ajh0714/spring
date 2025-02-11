package com.ict.myapp.service;

import com.ict.myapp.vo.MemberVO;

public interface MemberService {
	public int memberInsert(MemberVO vo);
	public MemberVO loginOk(String userid, String userpwd);
	public MemberVO memberSelect(String userid);
	public int memberUpdate(MemberVO vo);
	public int idDuplicate(String userid);
}
