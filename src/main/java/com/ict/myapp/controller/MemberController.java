package com.ict.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ict.myapp.service.MemberService;
import com.ict.myapp.vo.MemberVO;
import com.mysql.cj.log.Log;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	
	@Inject
	MemberService service;
	
	@GetMapping("/member/form")
	public String form() {
		return "member/memberform";
	}
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	@PostMapping("/member/formOk")
	public ModelAndView fomrOk(MemberVO vo) {
		int result;
		try {
			result = service.memberInsert(vo);
		} catch(Exception e) {
			result=0;
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName("member/memberOkResult");
		
		return mav;
	}
	
	@PostMapping("/member/loginOk")
	public ModelAndView loginOk(String userid, String userpwd, HttpSession session) {  //()안의 변수를 request해준다.	
		MemberVO vo = service.loginOk(userid, userpwd);
		
		ModelAndView mav = new ModelAndView();
		if(vo != null) { //아이디와 비밀번호가 일치하는 정보가 있으면 vo에 데이터가 담겨져서 return되고, 
			
			//로그인 성공 : session에 아이디, 이름, 로그인상태 저장 후 홈으로 이동
			//session 구하는 법 : HttpServletRequest, HttpSession (반드시 백엔드를 통해서 구한다.)
			session.setAttribute("logid", vo.getUserid());
			session.setAttribute("logName", vo.getUsername());
			session.setAttribute("logStatus", "Y");
			
			//다른 맵핑 주소로 바로 이동하기 위해서 'redirect:' 를 붙여서 해당 맵핑 주소를 호출한다.
			mav.setViewName("redirect:/");
			//mav.setViewName("home"); 으로 호출하면 home.jsp로 이동한다. homecontroller.java에서의 명령어 처리를 순서대로 하지못한다.
			
		} else { //일치하는 정보가 없으면 vo에 null이 담겨 객체가 안 만들어진다.
			//로그인 실패 : 로그인폼으로 이동
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/member/edit")
	public ModelAndView edit(HttpSession session) {
		String userid = (String)session.getAttribute("logid");
		MemberVO vo = service.memberSelect(userid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",vo);
		mav.setViewName("member/memberEdit");
		return mav;
	}
	@PostMapping("/member/editOk")
	public String editOk(MemberVO vo) {
		int result = service.memberUpdate(vo);
		System.out.println(result);
		return "redirect:edit";
	}
	
	//==아이디 중복검사 =======================
	@GetMapping("/member/idDuplicate")
	@ResponseBody
	public String idDuplicate(String userid){
		log.info("전송된 아이디는 {}입니다", userid);
		//반환형은 String, object, List, Map들 클래스는 가능하지만 int,double은 불가능
		return Integer.toString(service.idDuplicate(userid));
	}
}
