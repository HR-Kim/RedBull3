package kr.co.redbull.mypage.service.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.mypage.service.MyGood;
import kr.co.redbull.mypage.service.MyGoodService;

public class MyGoodController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyGoodService myGoodService;
	
	private final String VIEW_LIST_NM  ="mypage/mygood";
	
	/**장바구니 조회*/
	@RequestMapping(value="mypage/get_retrieve.do", method=RequestMethod.GET)
	public String get_retrieve(HttpServletRequest req,Search search, Model model){
		
		LOG.debug("1==================================");
		LOG.debug("=1=search="+search);
		LOG.debug("1==================================");
		//param
		if(search.getPageSize() == 0) {
			search.setPageSize(10);
		}
		
		if(search.getPageNum() == 0) {
			search.setPageNum(1);
		}
		
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
		model.addAttribute("vo",search);
		
		LOG.debug("2==================================");
		LOG.debug("=2=search="+search);
		LOG.debug("2==================================");
		
		//목록조회
		List<MyGood> list = (List<MyGood>) this.myGoodService.get_retrieve(search);
		LOG.debug("1==================================");
		LOG.debug("=1=list="+list);
		LOG.debug("1==================================");
		model.addAttribute("list",list);
		
		//총 건수
		int totalCnt = 0;
		if(null != list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
		}
		model.addAttribute("totalCnt", totalCnt);
		LOG.debug("1==================================");
		LOG.debug("=1=VIEW_LIST_NM="+VIEW_LIST_NM);
		LOG.debug("1==================================");
		
		return VIEW_LIST_NM;
	}
	
	@RequestMapping(value="mypage/do_save.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(MyGood good) {
		LOG.debug("===================================");
		LOG.debug("=good: " + good);
		LOG.debug("===================================");
		
		Message message = new Message();
		int count = this.myGoodService.countCart(good);
		if(count == 0) {
			//없으면 save
			int flag = myGoodService.do_save(good);
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("좋아요 성공");
			
		}else {
			message.setMsgMsg("이미 좋아요한 상품입니다.");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}
	
	
	
	@RequestMapping(value="mypage/do_delete.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(MyGood good) {
		LOG.debug("===================================");
		LOG.debug("=good: " + good);
		LOG.debug("===================================");
		
		int flag = this.myGoodService.do_delete(good);
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("좋아요 취소");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("좋아요 취소 실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}
}
