package kr.co.redbull.cart.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface CartService {
	
	/**저장*/
	public int do_save(DTO dto);
	
	/**수정*/
	public int do_updateI(DTO dto);
	
	/**삭제*/
	public int do_delete(DTO dto);
	
	/**목록조회*/
	public List<?> get_retrieve(DTO dto);
	
	/**총 금액*/
	public int total(DTO dto);
	
	/**동일상품 확인*/
	public int product_check(DTO dto);
	
	/**동일상품 수량 변경*/
	public int accumulation(DTO dto);

}
