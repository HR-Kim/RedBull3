package kr.co.redbull.cart.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;

public interface CartService {
	
	/**단건조회: 조회Count증가+ */
	public DTO get_selectOne(DTO dto);
	
	/**저장*/
	public int do_save(DTO dto);
	
	/**수정*/
	public int do_update(DTO dto);
	
	/**삭제*/
	public int do_delete(DTO dto);
	
	/**목록조회*/
	public List<?> get_retrieve(DTO dto);
	
	/**총 금액*/
	public int total(DTO dto);
	
	/**동일상품 확인*/
	public int countCart(DTO dto);
	
	/**동일상품 수량 변경*/
	public int updateCart(DTO dto);
	
	/**배송비 */
	public int delivery(DTO dto);
	
	/**할인가격*/
	public int sale(DTO dto);

}
