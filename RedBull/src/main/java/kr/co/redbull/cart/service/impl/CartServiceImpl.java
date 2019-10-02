package kr.co.redbull.cart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cart.service.CartService;
import kr.co.redbull.cmn.DTO;

@Service
public class CartServiceImpl implements CartService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartDaoImpl cartDaoImpl;

	@Override
	public int do_save(DTO dto) {
		
		return cartDaoImpl.do_save(dto);
	}

	@Override
	public int do_updateI(DTO dto) {
		
		return cartDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		
		return cartDaoImpl.do_delete(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		List<Cart> list = (List<Cart>) cartDaoImpl.get_retrieve(dto);
		LOG.debug("================================");
		LOG.debug("list: " + list);
		LOG.debug("================================");
		
		return list;
	}

	@Override
	public int total(DTO dto) { //밑에 3개 CartDaoImpl에 추가
	
		return cartDaoImpl.total(dto);
	}

	@Override
	public int countCart(DTO dto) {
		
		return cartDaoImpl.countCart(dto);
	}

	@Override
	public int updateCart(DTO dto) {
		
		return cartDaoImpl.updateCart(dto);
	}

	@Override
	public int delivery(DTO dto) {
		
		return cartDaoImpl.delivery(dto);
	}

	@Override
	public int sale(DTO dto) {
		
		return cartDaoImpl.sale(dto);
	}

}
