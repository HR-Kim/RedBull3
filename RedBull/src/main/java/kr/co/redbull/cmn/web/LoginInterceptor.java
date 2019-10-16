package kr.co.redbull.cmn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.redbull.user.service.User;

/**로그인이 안 된 경우: /login/login.jsp로 return */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

	// 컨트롤러 실행 전 가로채기
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try {
			
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("user");
			
			LOG.debug("===========================");
			LOG.debug("=user=" + user);
			LOG.debug("===========================");
			
			if(null == user) {
				
				String context = request.getContextPath();
				response.sendRedirect(context + "/login/login.jsp");	
			}
		
		} catch (Exception e){
			
			e.printStackTrace();
		}
	
		return true;
		
	}//--preHandle
	
	private String getViewName(HttpServletRequest request) throws Exception {
		
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		
		if(null == uri || uri.trim().equals("")) {
			
			uri = request.getRequestURI();
		}
		
		return uri;
		
	}//--getViewName

	// 컨트롤러 수행 후 DispatcherServlet이 뷰로 보내기 전에 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	// 뷰까지 수행하고 나서 호출
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}//--class
