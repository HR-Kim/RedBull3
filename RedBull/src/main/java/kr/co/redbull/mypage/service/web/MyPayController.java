package kr.co.redbull.mypage.service.web;

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
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.mypage.service.MyPay;
import kr.co.redbull.mypage.service.MyPayService;
import kr.co.redbull.user.service.User;

@Controller
public class MyPayController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyPayService myPageService;

	private final String VIEW_LIST_NM = "mypage/mypay";

	/** 구매 목록 화면 */
	@RequestMapping(value = "pay/get_payRetrieve.do", method = RequestMethod.GET)
	public String get_retrieve(HttpSession session, HttpServletRequest req, Search search, Model model) {

		LOG.debug("1==================================");
		LOG.debug("=1=search=" + search);
		LOG.debug("1==================================");

		User user = (User) session.getAttribute("user");
		String regId = user.getRid();

		if (null == search.getSearchDiv() || "".equals(search.getSearchDiv())) {
			search.setSearchDiv("10");
		}
		search.setSearchWord(regId);

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
		model.addAttribute("vo", search);

		LOG.debug("2==================================");
		LOG.debug("=2=search=" + search);
		LOG.debug("2==================================");

		// 목록조회

		List<MyPay> list = (List<MyPay>) this.myPageService.get_payRetrieve(search);
		LOG.debug("1==================================");
		LOG.debug("=1=list=" + list);
		LOG.debug("1==================================");
//		model.addAttribute("list",regId);
		model.addAttribute("list", list);

		// 총 건수
		int totalCnt = 0;
		if (null != list && list.size() > 0) {
			totalCnt = list.get(0).getTotalCnt();
		}
		model.addAttribute("totalCnt", totalCnt);
		LOG.debug("1==================================");
		LOG.debug("=1=VIEW_LIST_NM=" + VIEW_LIST_NM);
		LOG.debug("1==================================");

		return VIEW_LIST_NM;
	}

	@RequestMapping(value="mypage/get_mypage.do", method = RequestMethod.GET)
	public String get_fakemypage(HttpSession session) {
		
		return "mypage/mypage.jsp";
		
	}
	
}
