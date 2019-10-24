package kr.co.redbull.user.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import kr.co.redbull.cmn.StringUtil;;

@Controller
public class LocaleChangeController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LocaleResolver localeResolver; // SessionLocaleResolver 올리기

	
	// setter
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver; 
	}

	@RequestMapping(value="/change_locale.do", method = RequestMethod.GET) // 매핑 설정
	public String changeLocale(HttpServletRequest request, HttpServletResponse response) {
		
		String language = StringUtil.nvl(request.getParameter("lang"), "ko"); // 언어 값 받기
		
		LOG.debug("=================================");
		LOG.debug("=language=" + language);
		LOG.debug("=================================");
		
		Locale locale = new Locale(language); // 얻어온 언어 값을 전달하면서 locale 객체 생성
		
		localeResolver.setLocale(request, response, locale);
		
		return "main/main"; // 화면 던지기
		
	}//--changeLocale
	
}//--class
