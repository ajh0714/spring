package com.ict.myapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//�α��� ���θ� Ȯ���Ͽ� �α����� �ȵȰ�� �α��ο����� �̿��ϴ� Ŭ����
//�ݵ�� HandlerInterceptorAdapter ��ӹ޾� �޼ҵ带 �������̵� �ؾ��Ѵ�.
public class LoginInterceptor extends HandlerInterceptorAdapter{
	//��Ʈ�ѷ��� ȣ��Ǳ� ���� ����Ǵ� �޼ҵ�
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		
		// session��ü���� �α��� ������ Ȯ���� �α��� �ȵȰ�� �α��������� �̵�
		//							 �α��� �Ȱ�� ������������ �̵��Ѵ�.
		
		HttpSession session = request.getSession();
		
		//������ �α��� ������ ���ؿ´�.
		//
		String logStatus = (String)session.getAttribute("logStatus");
		
		if(logStatus == null || !logStatus.equals("Y")) {
			//�α��� �ȵȰ�� �α��������� �̵�
			
			//html : <a>, javascript : location, jsp : response.sendRedirect("")
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
		//��ȯ���� true�̸� ���� �����ּҷ� �̵�
		//��ȯ���� false�̸� ���ο� �ּҷ� �̵�
		return true;
	}
	//���������� �̵��ϱ� ���� ����Ǵ� �޼ҵ�
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView mav)throws Exception {
		
		
	}
	//��Ʈ�ѷ� ���� �� ȣ��Ǵ� �޼ҵ�
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception e)throws Exception {
		
		
	}
}
