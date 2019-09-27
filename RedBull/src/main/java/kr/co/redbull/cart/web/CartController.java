package kr.co.redbull.cart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="cart/do_save.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(Cart cart) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		
		int flag = this.cartService.do_save(cart);
		Message message = new Message();
		
		if(flag>0) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("담기 성공");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("담기 실패");
		}
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}

}
