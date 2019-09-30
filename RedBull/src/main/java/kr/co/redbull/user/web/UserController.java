package kr.co.redbull.user.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.redbull.cmn.Message;
import kr.co.redbull.code.service.CodeService;
import kr.co.redbull.user.service.User;
import kr.co.redbull.user.service.UserService;

//UserController 객체 만들기
@Controller
public class UserController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	// UserService랑 연결하고
	@Autowired
	UserService userService;
	
//	@Autowired
//	CodeService codeService;
	
//	@Resource(name="downloadView")
//	private View download;
	
	// View를 상수로 선언
	private final String VIEW_JOIN_NM = "user/join";
	private final String VIEW_UPDATE_NM = "user/update";
	
	/**삭제*/
	@RequestMapping(value="user/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(User user) {
		
		LOG.debug("=========================");
		LOG.debug("=user=" + user);
		LOG.debug("=========================");
		
		// 메소드 성공 여부 반환
		int flag = this.userService.do_delete(user);
		
		// 성공여부에 따라 메시지 출력
		Message message = new Message();
		
		if(flag > 0) {
			
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제되었습니다");
		}
		else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패");
			
		}
		
		// message를 json으로 변경
		Gson gson = new Gson();
		
		String gsonStr = gson.toJson(message);
		
		LOG.debug("=========================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("=========================");
		
		return gsonStr;
		
	}//--do_delete
	
	/**단건 조회*/
	@RequestMapping(value="user/get_selectOne.do", method = RequestMethod.POST ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String get_selectOne(User user, Model model) {
		
		LOG.debug("============================");
		LOG.debug("=@Controller user=" + user);
		LOG.debug("============================");
		
		if(null == user.getRid() || "".equals(user.getRid())) {
			
			throw new IllegalArgumentException("id를 입력하시오.");
		}
		
		User outVO = (User) userService.get_selectOne(user);
		
		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		
		LOG.debug("============================");
		LOG.debug("=@Controller gson=" + json);
		LOG.debug("============================");
		
		return json;
		
	}//--get_selectOne
	
	/**등록*/
	@RequestMapping(value="user/do_save.do", method = RequestMethod.POST ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_save(User user) {
		
		LOG.debug("1============================");
		LOG.debug("1=@Controller user=" + user);
		LOG.debug("1============================");
		
		// validation
		if(null == user.getRid() || "".equals(user.getRid().trim())) {
			
			throw new IllegalArgumentException("아이디를 입력하시오");
		}
		
		if(null == user.getPasswd() || "".equals(user.getPasswd().trim())) {
			
			throw new IllegalArgumentException("비밀번호를 입력하시오");
		}
		
		if(null == user.getUname() || "".equals(user.getUname().trim())) {
			
			throw new IllegalArgumentException("이름을 입력하시오");
		}
		
		if(null == user.getBirth()|| "".equals(user.getBirth().trim())) {
			
			throw new IllegalArgumentException("생년월일을 입력하시오");
		}
		
		if(null == user.getPhone() || "".equals(user.getPhone().trim())) {
			
			throw new IllegalArgumentException("휴대폰번호를 입력하시오");
		}
		
		if(0 == user.getPostnum()) {
			
			throw new IllegalArgumentException("우편번호를 입력하시오");
		}
		
		if(null == user.getAddress() || "".equals(user.getAddress().trim())) {
			
			throw new IllegalArgumentException("주소를 입력하시오");
		}
		
		if(null == user.getDetadd() || "".equals(user.getDetadd().trim())) {
			
			throw new IllegalArgumentException("상세주소를 입력하시오");
		}
		
		// 등록 성공여부 반환
		int flag = userService.do_save(user); 
		
		Message message = new Message();
		
		if(flag > 0) {
			
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getRid() + "님 등록성공");
		}
		else {
			
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getRid() + "님 등록실패");
		}
		
		// JSON
		Gson gson = new Gson();
		String json = gson.toJson(message);
		
		LOG.debug("2============================");
		LOG.debug("2=@Controller json=" + json);
		LOG.debug("2============================");
		
		return json;
		
	}//--do_save
	
	/**수정*/
	@RequestMapping(value="user/do_update.do", method = RequestMethod.POST ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_update(User user) {
		
		LOG.debug("1============================");
		LOG.debug("1=@Controller user=" + user);
		LOG.debug("1============================");
		
		// validation
		if(null == user.getRid() || "".equals(user.getRid().trim())) {
			
			throw new IllegalArgumentException("아이디를 입력하시오");
		}
		
		if(null == user.getPasswd() || "".equals(user.getPasswd().trim())) {
			
			throw new IllegalArgumentException("비밀번호를 입력하시오");
		}
		
		if(null == user.getUname() || "".equals(user.getUname().trim())) {
			
			throw new IllegalArgumentException("이름을 입력하시오");
		}
		
		if(null == user.getBirth()|| "".equals(user.getBirth().trim())) {
			
			throw new IllegalArgumentException("생년월일을 입력하시오");
		}
		
		if(null == user.getPhone() || "".equals(user.getPhone().trim())) {
			
			throw new IllegalArgumentException("휴대폰번호를 입력하시오");
		}
		
		if(0 == user.getPostnum()) {
			
			throw new IllegalArgumentException("우편번호를 입력하시오");
		}
		
		if(null == user.getAddress() || "".equals(user.getAddress().trim())) {
			
			throw new IllegalArgumentException("주소를 입력하시오");
		}
		
		if(null == user.getDetadd() || "".equals(user.getDetadd().trim())) {
			
			throw new IllegalArgumentException("상세주소를 입력하시오");
		}
		
		
		int flag = userService.do_update(user); // 수정 성공여부 반환
		
		Message message = new Message();
		
		if(flag > 0) {
			
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getRid() + "님 수정성공");
		}
		else {
			
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getRid() + "님 수정실패");
		}
		
		// JSON
		Gson gson = new Gson();
		String json = gson.toJson(message);
		
		LOG.debug("2============================");
		LOG.debug("2=@Controller json=" + json);
		LOG.debug("2============================");
		
		return json;
		
	}//--do_update
	
	@RequestMapping("user/get_selectone_view.do")
	public String get_selectOne_view() {
		
		LOG.debug("===============================");
		LOG.debug("=@Controller get_selectOne_view=");
		LOG.debug("===============================");
		
		return "user/join";
	}
	
	/**화면 호출*/
	//http://localhost:8080/ehr/user/do_user_view.do
	@RequestMapping(value="user/do_user_view.do", method = RequestMethod.GET)
	public String doUserView() {
		
		LOG.debug("============================");
		LOG.debug("=@Controller doUserView=");
		LOG.debug("============================");
		
		return VIEW_JOIN_NM;
	}
	
	
}//--class
