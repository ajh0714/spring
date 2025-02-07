package com.ict.myapp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	//매핑정보
	// @RequestMapping, @GetMapping, @PostMapping
	@RequestMapping(value="/test1",method=RequestMethod.GET)
	public String test1(HttpServletRequest request,Model model) {
		// 통신에서는 String -> 정수로 변경
		int num = Integer.parseInt(request.getParameter("num"));
		String name= request.getParameter("name");
		
		System.out.println(num+","+name);
		
		//Model 객체에 설정된 데이터는 홈페이지(jsp)에서 사용가능
		//				변수, 데이터
		model.addAttribute("username","세종대왕");
		model.addAttribute("hi","Welcome");
		
		return "home";// 
	}
	@RequestMapping("/test2") //Get접속에 대한 매핑
	public ModelAndView test2(int num, String name) {
		System.out.println(num+10+"===="+name);
		
		//model과 view의 기능을 가진 클래스
		ModelAndView mav = new ModelAndView();
		mav.addObject("username","손흥민");
		mav.addObject("hi", "Bye");

		mav.setViewName("home");
		
		return mav;
	}
	@GetMapping("/test3")
	public String test3(TestVO vo,Model model) {
		System.out.println(vo.toString());
		
		TestVO obj = new TestVO();
		obj.setNum(9999);
		obj.setName("김연아");
		obj.setTel("010-8888-9999");
		
		model.addAttribute("vo",obj);
		
		return "test/view";
	}
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public ModelAndView test4(String productName, int price, String opt){
		
		System.out.println("주문 상품정보 =>"+ productName +","+price+","+opt);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("pName", productName+"("+opt+")");
		mav.addObject("sales", price*0.9);
		
		mav.setViewName("test/view");
		
		return mav;
	}
}
