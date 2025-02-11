package com.ict.myapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.myapp.service.BbsService;
import com.ict.myapp.service.MemberService;
import com.ict.myapp.vo.BbsVO;
import com.ict.myapp.vo.MemberVO;
import com.ict.myapp.vo.PagingVO;

@Controller
public class AjaxController {
	
	@Inject
	MemberService service;
	@Inject
	BbsService serviceBbs;
	@GetMapping("/ajax/home")
	public String ajaxHome(){
		return "ajax/ajaxHome";
	}
	@GetMapping("/ajax/xmlHttpRequest")
	public String xmlHttp() {
		return"ajax/xmlHttp";
	}
	
	@GetMapping("/ajax/xmlHttpTest")
	@ResponseBody // 현재 매핑주소는 Model은 있고 view는 없다
	public MemberVO xmlHttpTest(String userid){
		MemberVO vo = service.memberSelect(userid);
		return vo; 
	}
	@PostMapping("/ajax/xmlHttpPost")
	@ResponseBody
	public List<BbsVO> xmlHttpPost(BbsVO vo, HttpServletRequest request) {
		vo.setUserid("goguma");
		vo.setIp(request.getRemoteAddr());
		
		System.out.println(vo.toString());
		
		int result = serviceBbs.bbsInsert(vo);//뉴스등록
		
		List<BbsVO> list = serviceBbs.bbsSelect(new PagingVO());
		return list;
	}
	/////////////////////////////////////////fetch/////////////////////////////////////////////
	@GetMapping("/ajax/fetchTest")
	public String fetchTest() {
		return "ajax/fetchTest";
	}
	@GetMapping("/fetch/getTest")
	@ResponseBody
	public MemberVO fetchgetTest(MemberVO vo) {
		vo.setEmail("fetchTest@nate.com");
		System.out.println(vo.toString());
		return vo;
	}
	//post방식의 비동기처리 : bbs의 해당페이지 목록을 선택한다.
	@PostMapping("/fetch/fetchPostBbsList")
	@ResponseBody
	public List<BbsVO> fetchPostTest(PagingVO pVO) {
		
		
		System.out.println(pVO.toString());
		return serviceBbs.bbsSelect(pVO);
		
	}
	////////////////////AJAX
	@GetMapping("/ajax/ajaxTest")
	public String ajaxTest(){
		return "ajax/ajaxTest";
	}
	//									반환형이 String일때 한글이 꺠지기 떄문에 문자코드를 기술
	@GetMapping(value="/ajax/ajaxString", produces="application/text;charset=UTF-8")
	@ResponseBody
	public String ajaxString(int no, String name){
		System.out.println("no="+no+", name="+name);
		
		return "나의 이름은 "+name+"입니다.";
	}
	@PostMapping("/ajax/ajaxObject")
	@ResponseBody
	public MemberVO ajaxObject(MemberVO vo){
		System.out.println(vo.toString());
		service.memberInsert(vo);
		return service.memberSelect(vo.getUserid());
	}
	// List리턴
	@GetMapping("/ajax/ajaxList")
	@ResponseBody
	public List<BbsVO> ajaxList(PagingVO vo){
		System.out.println(vo.toString());
		return serviceBbs.bbsSelect(vo);
	}
	
	//Map 리턴
	@GetMapping("/ajax/ajaxMap")
	@ResponseBody
	public Map ajaxMap(PagingVO pVO) {
		
		Map map = new HashMap();
		//검색어를 이용한 레코드선택
		map.put("bbsList", serviceBbs.bbsSelect(pVO));
		//페이지정보(pVO)
		map.put("page", pVO);
		//총레코드수
		map.put("totalRecord",serviceBbs.bbsTotalRecord(pVO));
		
		return map;
	}
}
