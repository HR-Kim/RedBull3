package kr.co.redbull.cart.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cart.service.CartService;
import kr.co.redbull.cart.service.impl.CartDaoImpl;
import kr.co.redbull.cmn.Message;

@Controller
public class CartController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartDaoImpl cartDaoImpl;
	
	//View
	private final String VIEW_LIST_NM = "cart/cart_list";
	private final String VUEW_MNG_NM = "cart/cart_mng";
	
//	/**목록조회*/
//	@RequestMapping(value="cart/get_retrieve.do", method=RequestMethod.GET)
//	public ModelAndView get_retrieve(HttpServletRequest req, ModelAndView model){ 
//		String uId = (String) req.getAttribute("uId");
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Cart> list = cartService.get_retrieve(uId);
//		
////		//목록조회
////		List<Cart> list = (List<Cart>) this.cartService.get_retrieve();
////		LOG.debug("1==================================");
////		LOG.debug("=1=list="+list);
////		LOG.debug("1==================================");
////		model.addAttribute("list",list);
////		
////		//총 건수
////		int totalCnt = 0;
////		if(null != list && list.size()>0) {
////			totalCnt = list.get(0).getTotalCnt();
////		}
////		model.addAttribute("totalCnt", totalCnt);
////		LOG.debug("1==================================");
////		LOG.debug("=1=VIEW_LIST_NM="+VIEW_LIST_NM);
////		LOG.debug("1==================================");
//		
//		return model;
//	}
	
	@RequestMapping(value="cart/do_update.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(Cart cart) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		
		int flag = this.cartService.do_update(cart);
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 성공");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}
	
	
	@RequestMapping(value="cart/do_save.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(Cart cart) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		
		Message message = new Message();
		int count = this.cartService.countCart(cart);
		if(count == 0) {
			//없으면 save
			int flag = cartService.do_save(cart);
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("담기 성공");
			
		}else {
			//있으면 updateCart
			int flag = cartService.updateCart(cart);
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수량 증가됨!");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}
	
	
	
	@RequestMapping(value="cart/do_delete.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(Cart cart) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		
		int flag = this.cartService.do_delete(cart);
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 성공");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}
	
}
