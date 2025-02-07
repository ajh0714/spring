package com.ict.myapp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.myapp.service.BbsService;
import com.ict.myapp.vo.BbsVO;
import com.ict.myapp.vo.PagingVO;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	@Inject
	BbsService service;
	
	//bbs 목록 /bbs/list
	@GetMapping("/list")
	public ModelAndView list(PagingVO pVO){
		//총 레코드 수 구하기
		pVO.setTotalRecord(service.bbsTotalRecord(pVO));
		
		
		System.out.println(pVO.toString());
		//해당페이지 레코드 선택
		List<BbsVO> list=service.bbsSelect(pVO);
		//해당페이지에 레코드를 선택,검색어
		ModelAndView mav = new ModelAndView();
		mav.addObject("pVO",pVO);//페이지, 검색어 정보를 jsp에서 사용호도록
		mav.addObject("list",list);
		mav.setViewName("bbs/list");
		return mav;
	}
	//글쓰기 폼  /bbs/write
	@GetMapping("/write")
	public String write() {
		return"bbs/write";
	}
	//글쓰기(DB) /bbs/writeOk
	@PostMapping("/writeOk")
	public ModelAndView wirteOk(BbsVO vo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		vo.setUserid((String)session.getAttribute("logid"));
		vo.setIp(request.getRemoteAddr());
		int result = service.bbsInsert(vo);
		ModelAndView mav = new ModelAndView();
		if(result==1) {
			mav.setViewName("redirect:list");
		}else {
			mav.setViewName("bbs/result");
		}
		return mav;
	}
	//글내용보기  /bbs/view
	@GetMapping("/view")
	public ModelAndView view(int news_no) {
		
		//조회수 증가
		service.hitCount(news_no);
		//레코드 선택
		BbsVO vo = service.bbsViewSelect(news_no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("bbs/view");
		return mav;
	}
	//글수정용 /bbs/edit
	@GetMapping("/edit")
	public ModelAndView edit(int news_no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",service.bbsViewSelect(news_no));
		mav.setViewName("bbs/edit");
		return mav;
	}
	@PostMapping("/editOk")
	public ModelAndView editOk(BbsVO vo){
	int result = service.bbsUpdate(vo);
	
	ModelAndView mav = new ModelAndView();
	if(result>0) {
		mav.setViewName("redirect:view?news_no="+vo.getNews_no());
	}else {
		mav.setViewName("bbs/result");
	}
	return mav;
		
	}
	//글삭제
	@GetMapping("/del")
	public ModelAndView del(int news_no) {
		int result = service.bbsDelete(news_no);
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:list");
		}else {
			mav.setViewName("redirect:view?news_no="+news_no);
			
		}
		return mav;
	}
}