package kr.co.redbull.pay.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface PayService {
	
	/**바로 결제: 주문상세 입력*/
	public int direct_save(DTO dto);
	
	/** 결제 시 카트 내용 삭제 */
	public int do_delete(DTO dto);
	
	/** 배송지 삽입 */
	public int do_save(DTO dto);
	
	/**주문 상세 정보 삽입*/
	public int do_paydetail(DTO dto);
	
	/** 주문자 목록 List*/
	public List<?> get_retrieve(DTO dto);
	
}
