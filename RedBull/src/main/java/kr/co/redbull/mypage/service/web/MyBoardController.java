package kr.co.redbull.mypage.service.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.mypage.service.MyBoard;
import kr.co.redbull.mypage.service.MyBoardService;
import kr.co.redbull.user.service.User;

@Controller
public class MyBoardController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyBoardService myBoardService;
	
	private String viewListNm ="mypage/myquestion";
	
	@RequestMapping(value = "myboard/get_aRetrieve.do", method = RequestMethod.GET)
	public String get_retrieve(HttpSession session, HttpServletRequest req, Search search, Model model) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		User user = (User) session.getAttribute("user");
		String regId = user.getRid();
		
		if(search.getPageSize()==0) search.setPageSize(10);
		if(search.getPageNum()==0)  search.setPageNum(1);
		
		search.setSearchWord(regId);

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
		model.addAttribute("vo", search);
		
		List<MyBoard> list = (List<MyBoard>)myBoardService.get_aRetrieve(search);
		
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null!=list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		if(search.getSearchDiv().equals("10")) {
			viewListNm = "board/notice_list";
		}else if(search.getSearchDiv().equals("20")) {
			viewListNm = "board/question_list";
		}
		
		return viewListNm;
	}
}
