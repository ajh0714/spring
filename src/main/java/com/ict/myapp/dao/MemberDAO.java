package com.ict.myapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ict.myapp.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	//아이디 중복검사
	
		public int memberInsert(MemberVO vo);
		//회원등록
		
		//회원수정
		public MemberVO memberSelect(String userid);
		
		public int memberUpdate(MemberVO vo);
		//회원탈퇴
		
		//로그인
		public MemberVO loginOk(String userid, String userpwd);
		//아이디찾기
		
		//비밀번호찾기
		
		//아이디 중복검사
		public int idDuplicate(String userid);
}
