package kr.co.redbull.pay.web;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.pay.service.Pay;
import kr.co.redbull.pay.service.PayDetail;
import kr.co.redbull.pay.service.PayService;
import kr.co.redbull.user.service.User;

@Controller
public class PayController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PayService payService;
	
	private final String VIEW_LIST_NM = "pay/pay_list"; //결제 화면
	private final String VIEW_COMPLETE = "pay/pay_complete";

	/** 장바구니 -> 주문목록  / 바로 주문목록  /결제 총 금액 가져오기!!!! */
	@RequestMapping(value="pay/get_retrieve.do", method=RequestMethod.GET)
	public String get_retrieve(HttpSession session, Pay pay, Model model, Search search) {
		
		LOG.debug("=================================");
		LOG.debug("=search=" + search);
		LOG.debug("=================================");
		
		User ruser = (User) session.getAttribute("user");
		String rid = ruser.getRid();
		
		if(null==search.getSearchDiv() || "".equals(search.getSearchDiv())) {
			search.setSearchDiv("10");
		}
		
		search.setSearchWord(rid);
		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));
		model.addAttribute("vo",search);
		
		LOG.debug("2==================================");
		LOG.debug("=2=search="+search);
		LOG.debug("2==================================");
		
		//user.setRid(rid);
		//pay.setPayId(rid);
		
		
		//List<Pay> list = (List<Pay>) this.payService.get_retrieve(pay);
		//Pay outVO = (Pay) this.payService.get_selectOne(pay);
		//User outVO = (User) this.payService.get_selectOne(user);
		//model.addAttribute("vo", outVO);
		
//		LOG.debug("2==================================");
//		LOG.debug("=2=list="+list);
//		LOG.debug("2==================================");
//		model.addAttribute("list",list);
	
		List<Pay> payList = (List<Pay>) this.payService.get_retrieve(search);
		model.addAttribute("payList", payList);
		
//		int totalCnt = 0;
//		if(null != list && list.size()>0) {
//			totalCnt = list.get(0).getTotalCnt();
//		}
//		model.addAttribute("totalCnt", totalCnt);
		
		LOG.debug("1==================================");
		LOG.debug("=1=VIEW_LIST_NM="+VIEW_LIST_NM);
		LOG.debug("1==================================");
		
		return VIEW_LIST_NM; //결제 화면
		
	}


	/**최종 결제  저장*/
	@RequestMapping(value="pay/do_save.do", method=RequestMethod.POST)
	public String do_save(HttpSession session,Pay pay, PayDetail payDetail){
		
		LOG.debug("1==================================");
		LOG.debug("=1=pay="+pay);
		LOG.debug("1==================================");
	
		User user = (User) session.getAttribute("user");
		String payId = user.getRid();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		String subNum = "";
		
		for(int i=0; i<=6; i++) {
			subNum  += (int)(Math.random() * 10);
		}
		
		String orderNum = ymd + "_" + subNum;
		
		//주문테이블 저장
		pay.setOrderNum(orderNum);
		pay.setPayId(payId);
		
		payService.do_save(pay);
		
		//cart.setRegId(payId);
		
		//주문상세 테이블 저장
		payDetail.setOrderNum(orderNum);
		payDetail.setRegId(payId);
		LOG.debug("1==================================");
		LOG.debug("=1=payDetail="+payDetail);
		LOG.debug("1==================================");
		
		payService.do_paydetail(payDetail);
		
		//장바구니 비우기
		payService.do_delete(payDetail);
		
		return VIEW_COMPLETE;
	}
	
}
