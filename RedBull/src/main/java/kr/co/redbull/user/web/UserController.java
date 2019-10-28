package kr.co.redbull.user.web;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;

import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.code.service.CodeService;
import kr.co.redbull.user.service.NaverLoginBO;
import kr.co.redbull.user.service.User;
import kr.co.redbull.user.service.UserService;
import kr.co.redbull.user.service.impl.UserServiceImpl;

//UserController 객체 만들기
@Controller
public class UserController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	// UserService랑 연결하고
	@Autowired
	UserService userService;
	
	@Autowired
	private LocaleResolver localeResolver;//SessionLocaleResolver
	
	@Resource(name="downloadView")
	private View download;
	
	// View를 상수로 선언
	private final String VIEW_JOIN_NM = "user/join";
	private final String VIEW_UPDATE_NM = "user/update";
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	  @RequestMapping(value = "login")
	  public String initLogin(Model model, HttpSession session) throws Exception {

		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		/* 생성한 인증 URL을 View로 전달 */
		model.addAttribute("naver_url", naverAuthUrl);
		
		/* 생성한 인증 URL을 Model에 담아서 전달 */
		return "login/login";
	  }
	  
	  // 네이버 Callback호출 메소드
	  @RequestMapping(value = "callback")
	  public String naverCallback() throws IOException {

	    System.out.println("naver login success");

	  return "main/main.do";
	  }

	/**아이디 중복 검사*/
	@RequestMapping(value="user/check_id.do", method = RequestMethod.POST ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String check_id(User user) {
		
		LOG.debug("1============================");
		LOG.debug("1=@Controller outVO=" + user);
		LOG.debug("1============================");
		
		Message msg = new Message();; // 전달할 메시지
		
		// 이메일 입력란에 입력을 안 했으면
		if(null == user.getRid() || "".equals(user.getRid())) {
			
			msg.setMsgId("20");
		}
		
		else if(user.getRid().length() < 2) { // 2자 미만으로 입력한 경우
			
			msg.setMsgId("40");
		}
		
		else { // 이메일 입력란에 입력을 한 경우
			
			// 아이디 존재 유무 확인
			msg = (Message) userService.idCheck(user);
		}
		
		LOG.debug("2=========================");
		LOG.debug("2= msg="+ msg); 
		LOG.debug("2=========================");
		
		// JSON
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		
		LOG.debug("2============================");
		LOG.debug("2=@Controller json=" + json);
		LOG.debug("2============================");
		
		return json;
		
	}//--check_id

	/**비밀번호 찾기*/
	@RequestMapping(value="user/find_passwd.do", method = RequestMethod.POST ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String find_passwd(User user) {
		
		LOG.debug("1============================");
		LOG.debug("1=@Controller outVO=" + user);
		LOG.debug("1============================");
		
		// 이메일 입력란에 입력을 했는지 체크
		if(null == user.getRid() || "".equals(user.getRid())) {
			
			throw new IllegalArgumentException("이메일을 입력하시오.");
		}
		
		// 아이디가 존재 유무 확인
		Message msg = (Message) userService.idCheck(user);
		
		LOG.debug("2=========================");
		LOG.debug("2= msg="+ msg); 
		LOG.debug("2=========================");
		
		// 아이디가  존재하면
		if(msg.getMsgId().equals("30")) {
		
			User outVO = (User) userService.get_selectOne(user); // 해당 아이디를 단건조회하고
			userService.find_passwd(outVO); // 비빌번호를 메일로 보내는 메소드 수행
			
		} 
		
		// JSON
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		
		LOG.debug("2============================");
		LOG.debug("2=@Controller json=" + json);
		LOG.debug("2============================");
		
		return json;
		
	}//--find_passwd
	
	/**로그아웃*/
	@RequestMapping(value="login/do_logout.do",method=RequestMethod.GET,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_logout(HttpServletRequest request) throws Exception {
		
		request.getSession().removeAttribute("user");
		
		Message msg = new Message();
		msg.setMsgMsg("로그아웃되었습니다");

		// JSON
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		
		LOG.debug("2============================");
		LOG.debug("2=@Controller json=" + json);
		LOG.debug("2============================");
		
		return json;
		
	}//--do_logout
	
	/**로그인*/
	@RequestMapping(value="login/do_login.do",method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_login(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		LOG.debug("1=========================");
		LOG.debug("1=user="+user); // 아이디와 비밀번호
		LOG.debug("1=========================");
		
		//String language = StringUtil.nvl(request.getParameter("lang"), "ko");
		//LOG.debug("=language="+language);
		
		Message msg = (Message) userService.idPassCheck(user);
		
		LOG.debug("2=========================");
		LOG.debug("2= msg="+ msg); 
		LOG.debug("2=========================");
		
		// 10: 아이디 오류, 20: 비밀번호 오류
		if(msg.getMsgId().equals("10") || msg.getMsgId().equals("20")) {
			
//			return "user/login"; // 로그인 화면으로 던지기
		}
		else { // 아이디, 비밀번호 체크 둘 다 성공하면
			
			// 데이터 단건 조회
			User outVO = (User) userService.get_selectOne(user);
			outVO.setintlvl(outVO.getLvl().intValue());
			
			LOG.debug("3=========================");
			LOG.debug("3= outVO="+ outVO); 
			LOG.debug("3=========================");
			
			// locale 객체를 생성하면서 언어 값을 전달
			Locale locale=new Locale(user.getLang());
			localeResolver.setLocale(request, response, locale);
			
			session.setAttribute("user", outVO);
			session.setAttribute("rid", outVO.getRid());
			session.setAttribute("locale", locale);
			
//			return "main/main"; // 메인 화면 던지기 
//			return "redirect:/main/main.jsp"; // sendredirect와 같은 개념
			
		}
		
		// JSON
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		
		LOG.debug("2============================");
		LOG.debug("2=@Controller json=" + json);
		LOG.debug("2============================");
		
		return json;
		
	}//--do_login
	
	
	/**ExcelDown*/
	@RequestMapping(value="user/do_exceldown.do",method=RequestMethod.GET)
	public ModelAndView excelDown(User user, HttpServletRequest req, ModelAndView mView) {
		
		/** 확장자 */
		String ext = StringUtil.nvl(req.getParameter("ext"));		
		
		// 변수값을 setting
		user.setRid(StringUtil.nvl(user.getRid()));
		user.setPasswd(StringUtil.nvl(user.getPasswd()));
		user.setUname(StringUtil.nvl(user.getUname()));
		user.setBirth(StringUtil.nvl(user.getBirth()));
		user.setPhone(StringUtil.nvl(user.getPhone()));
		
		user.setPostnum(user.getPostnum());
		user.setAddress(StringUtil.nvl(user.getAddress()));
		user.setDetadd(StringUtil.nvl(user.getDetadd()));
		user.setLvl(user.getLvl());
		user.setUpoint(user.getUpoint());
		
		user.setInum(user.getInum());
		user.setRegdt(user.getRid());
		
		// userService의 엑셀 다운로드 메소드 호출(search 객체와 확장자를 전달)
		String saveFileNm = this.userService.get_excelDown(user, ext); // 저장파일명: 무작위로 생성됨
		String orgFileNm  = user.getUname() + "님의_회원정보_" + StringUtil.cureDate("yyyyMMdd" )+ "." + ext; // 기존 파일명을 변경
		
		mView.setView(download); // 뷰 세팅
		
		File downloadFile = new File(saveFileNm); // 저장파일명으로 파일 객체 생성
		mView.addObject("downloadFile", downloadFile);
		mView.addObject("orgFileNm", orgFileNm);
		
		return mView;
	}
	
	/**삭제*/
	@RequestMapping(value="user/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(User user, HttpSession session) {
		
		LOG.debug("=========================");
		LOG.debug("=user=" + user);
		LOG.debug("=========================");
		
		// 메소드 성공 여부 반환
		int flag = this.userService.do_delete(user);
		
		// 성공여부에 따라 메시지 출력
		Message message = new Message();
		
		if(flag > 0) {
			
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("탈퇴 완료");
		}
		else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("탈퇴 실패");
			
		}
		
		session.removeAttribute("user");

		// message를 json으로 변경
		Gson gson = new Gson();
		
		String gsonStr = gson.toJson(message);
		
		LOG.debug("=========================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("=========================");
		
		
		return gsonStr;
		
	}//--do_delete
	
	/**단건 조회*: 비밀번호 찾기용*/
	@RequestMapping(value="user/get_selectOne.do", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=UTF-8")
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
	
	//------testing---------------------------------------------------------------------------
	
//	/**업데이트 화면 던지기*/
//	@RequestMapping(value="user/get_updateForm.do", method = RequestMethod.GET)
//	public String get_selectOneUpdate(HttpSession session, Model model) {
//		
//		String rid = (String) session.getAttribute("rid");
//		
//		
//		LOG.debug("============================");
//		LOG.debug("=@@@@@@@@@@@@@@@@@@@@@@Controller user=" + rid);
//		LOG.debug("============================");
//		
////		if(null == user.getRid() || "".equals(user.getRid())) {
////			
////			throw new IllegalArgumentException("id를 입력하시오.");
////		}
//		
//		// 조회결과를 변수에 담고 모델에 set함
////		User outVO = (User) userService.get_selectOne(user);
////		LOG.debug("============================");
////		LOG.debug("%%%%%%%%%%%%%%%%%%Controller outVO=" + outVO);
////		LOG.debug("============================");
//		model.addAttribute("vo", rid);
//				
//		// 업데이트 화면을 던짐
//		return VIEW_UPDATE_NM;
//		
//	}//--get_selectOne
	
//	/**업데이트 화면 던지기*/
//	@RequestMapping(value="user/get_updateForm.do", method = RequestMethod.GET)
//	public ModelAndView get_selectOneUpdate(HttpSession session) {
//		
//		String rid = (String) session.getAttribute("rid");
//		User outUser = (User) session.getAttribute("user");
//		
//		LOG.debug("============================");
//		LOG.debug("=@@@@@@@@@@@@@@@@@@@@@@Controller outUser=" + outUser);
//		LOG.debug("============================");
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("user", outUser);
//		mav.setViewName(VIEW_UPDATE_NM);
//		
//		return mav;
//		
//	}//--get_selectOne
	
//	/**업데이트 화면 던지기*/
//	@RequestMapping(value="user/get_updateForm.do", method = RequestMethod.GET)
//	public String get_updateForm() {
//
//		LOG.debug("============================");
//		LOG.debug("=@@@@@@@@@@@@@@@@get_updateForm outUser@@@@@@@@@@");
//		LOG.debug("============================");
//		
//		return VIEW_UPDATE_NM;
//		
//	}//--get_updateForm
	
	/**업데이트 화면 던지기*/
	@RequestMapping(value="user/get_updateForm.do", method = RequestMethod.GET )
	public ModelAndView get_updateForm(HttpSession session) throws Exception {
		
		String rid = (String) session.getAttribute("rid");
		User outUser = (User) session.getAttribute("user");
		
		LOG.debug("============================");
		LOG.debug("=@@@@@@@@@@@@@get_updateForm@@@@@@@@@@");
		LOG.debug("============================");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", outUser);
		mav.setViewName(VIEW_UPDATE_NM);
		
		return mav;
		
	}//--get_updateForm
	
	//---testing---------------------------------------------------------------------------------
	
	/**가짜 마이페이지 화면 던지기*/
	@RequestMapping(value="fakemypage/get_fakemypage.do", method = RequestMethod.GET)
	public String get_fakemypage(HttpSession session) {
		
		return "fakemypage/fakemypage";
		
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
		
		
		// 등록 성공여부 반환
		int flag = userService.do_save(user); 
		
		Message message = new Message();
		
		if(flag > 0) {
			
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getRid() + "님 가입완료");
		}
		else {
			
			message.setMsgId(flag+"");
			message.setMsgMsg(user.getRid() + "님 가입실패");
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
	@RequestMapping(value="user/do_update.do", method = RequestMethod.GET ,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_update(User user, HttpSession session) {
		
		LOG.debug("1============================");
		LOG.debug("1=@Controller user=" + user);
		LOG.debug("1============================");
		
		// validation
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
		
		
		int flag = this.userService.do_update(user); // 수정 성공여부 반환
		
		session.setAttribute("user", user);
		session.setAttribute("rid", user.getRid());
		
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
