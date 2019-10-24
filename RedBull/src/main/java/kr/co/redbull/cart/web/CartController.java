package kr.co.redbull.cart.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cart.service.CartService;
import kr.co.redbull.cart.service.impl.CartDaoImpl;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.opt.service.Opt;
import kr.co.redbull.user.service.User;

@Controller
public class CartController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartService cartService;
	
	
	//View
	private final String VIEW_LIST_NM = "cart/cart";
	private final String VIEW_MNG_NM = "cart/cart_mng";
	
	
	/**단건 조회*/
	@RequestMapping(value="cart/get_selectOne.do", method=RequestMethod.GET)
	public String get_selectOne(Cart inVO, Model model) {
		
		LOG.debug("=================================");
		LOG.debug("=board=" + inVO);
		LOG.debug("=================================");
		
		Cart outVO = (Cart) this.cartService.get_selectOne(inVO);
		model.addAttribute("vo",outVO);
		
		return VIEW_MNG_NM;
		
	}
	
	/**장바구니 화면*/
	@RequestMapping(value="cart/get_retrieve.do", method=RequestMethod.GET)
	public String get_retrieve(HttpSession session, HttpServletRequest req,Search search, Model model){
		
		LOG.debug("1==================================");
		LOG.debug("=1=search="+search);
		LOG.debug("1==================================");
		
		//User user = (User) session.getAttribute("user");
		//String regId = user.getRid();
		
		
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
		//search.setSearchWord(regId);
		
		LOG.debug("2==================================");
		LOG.debug("=2=search="+search);
		LOG.debug("2==================================");
		
		//목록조회
		
		List<Cart> list = (List<Cart>) this.cartService.get_retrieve(search);
		LOG.debug("1==================================");
		LOG.debug("=1=list="+list);
		LOG.debug("1==================================");
//		model.addAttribute("list",regId);
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
	
	/**수량 변경시 call*/
	@RequestMapping(value="cart/do_update.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(HttpSession session, Cart cart) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		Message message = new Message();
		
		User user = (User) session.getAttribute("user");
		
		
		if(user != null) {
			cart.setRegId(user.getRid());
			int flag = this.cartService.do_update(cart);
			
			
			if(flag>0) {
				message.setMsgId(String.valueOf(flag));
				message.setMsgMsg("수정 성공");
			}else {
				message.setMsgId(String.valueOf(flag));
				message.setMsgMsg("수정 실패");
			}
		}
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		
		
		return gsonStr;
	}
	
	/**제품상세에서 장바구니 call*/
	@RequestMapping(value="cart/do_save.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int do_save(HttpSession session, Cart cart) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		
		int result = 0;
		
		User user = (User) session.getAttribute("user");
		
			//Message message = new Message();
		
			if(user != null) {
				cart.setRegId(user.getRid());
				int count = this.cartService.countCart(cart);
				if(count == 0) {
					//없으면 save
					int flag = cartService.do_save(cart);
					//message.setMsgId(String.valueOf(flag));
					//message.setMsgMsg("담기 성공");
					
					}else {
					//있으면 updateCart
					int flag = cartService.updateCart(cart);
					//message.setMsgId(String.valueOf(flag));
					//message.setMsgMsg("수량 증가됨!");
				}
				result = 1;
			}

		//Gson gson = new Gson();
		//String gsonStr = gson.toJson(message);
		
		return result;
	}
	
	
	/**체크박스 선택시 삭제 call*/
	@RequestMapping(value="cart/do_delete.do", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int do_delete(Cart cart, HttpSession session, @RequestParam(value ="chbox[]") List<String> chArr ) {
		LOG.debug("===================================");
		LOG.debug("=cart: " + cart);
		LOG.debug("===================================");
		
		Message message = new Message();
		
		User user = (User) session.getAttribute("user");
		String regId = user.getRid();
		
		int result = 0;
		int cartNum = 0;
		
		if(user != null) {
			
			cart.setRegId(regId);
			for(String i : chArr) {
				cartNum = Integer.parseInt(i);
				cart.setCartNum(cartNum);
			this.cartService.do_delete(cart);
			}
			result = 1;
		}
		
		return result;
	}
	
}
