package kr.co.softcampus.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.BoardInfoBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{
	
	
	private TopMenuService topMenuService; // 인터셉터는 자동주입을 통해 빈을 주입받지 못함 그래서 인터셉터에서 사용할 객체는 인터셉터를 등록하는 쪽에서 생성자로 넘겨야됨
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
		
		this.topMenuService = topMenuService;
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
	
	

}
