package com.kh.mixmatch.team.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TeamCheckInterceptor extends HandlerInterceptorAdapter{
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("=============TeamCheckInterceptor 진입=========");
		}
		// 로그인 여부 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("user_team")==null){
			response.sendRedirect(request.getContextPath()+"/team.do");
			return false;
		}
		return true;
	}
}
