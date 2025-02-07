package com.ict.myapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인 여부를 확인하여 로그인이 안된경우 로그인용으로 이용하는 클래스
//반드시 HandlerInterceptorAdapter 상속받아 메소드를 오버라이딩 해야한다.
public class LoginInterceptor extends HandlerInterceptorAdapter{
	//컨트롤러가 호출되기 전에 실행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		
		// session객체에서 로그인 정보를 확인후 로그인 안된경우 로그인폼으로 이동
		//							 로그인 된경우 원래접속으로 이동한다.
		
		HttpSession session = request.getSession();
		
		//세션의 로그인 정보를 구해온다.
		//
		String logStatus = (String)session.getAttribute("logStatus");
		
		if(logStatus == null || !logStatus.equals("Y")) {
			//로그인 안된경우 로그인폼으로 이동
			
			//html : <a>, javascript : location, jsp : response.sendRedirect("")
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
		//반환형이 true이면 원래 매핑주소로 이동
		//반환형이 false이면 새로운 주소로 이동
		return true;
	}
	//뷰페이지로 이동하기 전에 실행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView mav)throws Exception {
		
		
	}
	//컨트롤러 실행 후 호출되는 메소드
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception e)throws Exception {
		
		
	}
}
