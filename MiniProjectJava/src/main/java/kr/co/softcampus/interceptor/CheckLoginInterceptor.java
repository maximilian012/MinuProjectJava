package kr.co.softcampus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (loginUserBean.isUserLogin() == false) { // 로그인 안된상태
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");
			return false;
			
		}
		
		return true;  
	}
}
