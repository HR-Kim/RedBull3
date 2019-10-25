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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.mypage.service.MyGood;
import kr.co.redbull.mypage.service.MyGoodService;
import kr.co.redbull.user.service.User;

@Controller
public class MyGoodController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MyGoodService myGoodService;

	private final String VIEW_LIST_NM = "mypage/mygood";

	/** 좋아요 화면 */
	@RequestMapping(value = "good/get_retrieve.do", method = RequestMethod.GET)
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

		List<MyGood> list = (List<MyGood>) this.myGoodService.get_retrieve(search);
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

	/** 제품상세에서 좋아요 call */
	@RequestMapping(value = "good/do_save.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int do_save(HttpSession session, MyGood good) {
		LOG.debug("===================================");
		LOG.debug("=good: " + good);
		LOG.debug("===================================");

		int result = 0;

		User user = (User) session.getAttribute("user");

		Message message = new Message();

		if (user != null) {
			good.setRegId(user.getRid());
			int count = this.myGoodService.countGood(good);
			if (count == 0) {
				// 없으면 save
				int flag = myGoodService.do_save(good);
				// message.setMsgId(String.valueOf(flag));
				message.setMsgMsg("좋아요 성공");
			} else {
				message.setMsgMsg("이미 좋아요한 상품입니다.");
			}
			result = 1;
		}

		return result;
	}

	/** 체크박스 선택시 삭제 call */
	@RequestMapping(value = "good/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int do_delete(MyGood good, HttpSession session, @RequestParam(value = "chbox[]") List<String> chArr) {
		LOG.debug("===================================");
		LOG.debug("=good: " + good);
		LOG.debug("===================================");

		Message message = new Message();

		User user = (User) session.getAttribute("user");
		String regId = user.getRid();

		int result = 0;
		int gNum = 0;

		if (user != null) {

			good.setRegId(regId);
			for (String i : chArr) {
				gNum = Integer.parseInt(i);
				good.setgNum(gNum);
				this.myGoodService.do_delete(good);
			}
			result = 1;
		}

		return result;
	}
	
	@RequestMapping(value="mypage/get_mypage.do", method = RequestMethod.GET)
	public String get_fakemypage(HttpSession session) {
		
		return "mypage/mypage.jsp";
		
	}
	
}
