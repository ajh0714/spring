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
	
	//bbs ��� /bbs/list
	@GetMapping("/list")
	public ModelAndView list(PagingVO pVO){
		//�� ���ڵ� �� ���ϱ�
		pVO.setTotalRecord(service.bbsTotalRecord(pVO));
		
		
		System.out.println(pVO.toString());
		//�ش������� ���ڵ� ����
		List<BbsVO> list=service.bbsSelect(pVO);
		//�ش��������� ���ڵ带 ����,�˻���
		ModelAndView mav = new ModelAndView();
		mav.addObject("pVO",pVO);//������, �˻��� ������ jsp���� ���ȣ����
		mav.addObject("list",list);
		mav.setViewName("bbs/list");
		return mav;
	}
	//�۾��� ��  /bbs/write
	@GetMapping("/write")
	public String write() {
		return"bbs/write";
	}
	//�۾���(DB) /bbs/writeOk
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
	//�۳��뺸��  /bbs/view
	@GetMapping("/view")
	public ModelAndView view(int news_no) {
		
		//��ȸ�� ����
		service.hitCount(news_no);
		//���ڵ� ����
		BbsVO vo = service.bbsViewSelect(news_no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("bbs/view");
		return mav;
	}
	//�ۼ����� /bbs/edit
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
	//�ۻ���
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