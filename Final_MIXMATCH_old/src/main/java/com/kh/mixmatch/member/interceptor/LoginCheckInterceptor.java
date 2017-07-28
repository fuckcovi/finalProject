package com.kh.mixmatch.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("=============LoginCheckInterceptor 진입=========");
		}
		HttpSession session = request.getSession();
		if(session.getAttribute("user_id")==null){
			response.sendRedirect(request.getContextPath()+"/login.do");
			return false;
		}
		return true;
	}
}
