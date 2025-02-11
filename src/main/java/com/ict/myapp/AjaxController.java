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
	@ResponseBody // ���� �����ּҴ� Model�� �ְ� view�� ����
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
		
		int result = serviceBbs.bbsInsert(vo);//�������
		
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
	//post����� �񵿱�ó�� : bbs�� �ش������� ����� �����Ѵ�.
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
	//									��ȯ���� String�϶� �ѱ��� ������ ������ �����ڵ带 ���
	@GetMapping(value="/ajax/ajaxString", produces="application/text;charset=UTF-8")
	@ResponseBody
	public String ajaxString(int no, String name){
		System.out.println("no="+no+", name="+name);
		
		return "���� �̸��� "+name+"�Դϴ�.";
	}
	@PostMapping("/ajax/ajaxObject")
	@ResponseBody
	public MemberVO ajaxObject(MemberVO vo){
		System.out.println(vo.toString());
		service.memberInsert(vo);
		return service.memberSelect(vo.getUserid());
	}
	// List����
	@GetMapping("/ajax/ajaxList")
	@ResponseBody
	public List<BbsVO> ajaxList(PagingVO vo){
		System.out.println(vo.toString());
		return serviceBbs.bbsSelect(vo);
	}
	
	//Map ����
	@GetMapping("/ajax/ajaxMap")
	@ResponseBody
	public Map ajaxMap(PagingVO pVO) {
		
		Map map = new HashMap();
		//�˻�� �̿��� ���ڵ弱��
		map.put("bbsList", serviceBbs.bbsSelect(pVO));
		//����������(pVO)
		map.put("page", pVO);
		//�ѷ��ڵ��
		map.put("totalRecord",serviceBbs.bbsTotalRecord(pVO));
		
		return map;
	}
}
