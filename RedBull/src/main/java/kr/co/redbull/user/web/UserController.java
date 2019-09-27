package kr.co.redbull.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.redbull.user.service.UserService;

//UserController 객체 만들기
@Controller
public class UserController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	// UserService랑 연결하고
	@Autowired
	UserService userService;
	
	@RequestMapping("user/get_selectone_view.do")
	public String get_selectOne_view() {
		
		LOG.debug("===============================");
		LOG.debug("=@Controller get_selectOne_view=");
		LOG.debug("===============================");
		
		return "user/join";
	}
	
}//--class
