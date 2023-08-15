package kr.co.softcampus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	
	private UserBean loginUserBean;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String str1 = request.getParameter("content_idx");
		int content_idx = Integer.parseInt(str1);
		
		ContentBean currContentBean = boardService.getContentInfo(content_idx);
		
		if (currContentBean.getContent_writer_idx() != loginUserBean.getUser_idx()) { // 작성자와 로그인한 사람이 다르면 not_writer.jsp 요청
			
			String contextPath = request.getContextPath();
			
			response.sendRedirect(contextPath + "/board/not_writer");
			
			return false;
			
		}
		return true;
	}
	
	
	
	
	

}
