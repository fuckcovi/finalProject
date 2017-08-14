package com.kh.mixmatch.team.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.mixmatch.team.service.TeamMemService;

public class TeamCheckInterceptor extends HandlerInterceptorAdapter{
	private Logger log = Logger.getLogger(this.getClass());
	private TeamMemService teamMemService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("=============TeamCheckInterceptor 진입=========");
		}
		// 로그인 여부 검사
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		System.out.println(user_id + " : 아이디");
		System.out.println(teamMemService.getRowTeamCount(user_id));
		int teamCount = teamMemService.getRowTeamCount(user_id);
		
		System.out.println("팀 수 : " + teamCount);
		if(teamCount <1){
			
			response.sendRedirect(request.getContextPath()+"/team.do");
			return false;
		}
		return true;
	}
}