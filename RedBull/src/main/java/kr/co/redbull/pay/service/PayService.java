package kr.co.redbull.pay.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface PayService {

	/** 결제 시 카트 내용 삭제 */
	public int do_delete(DTO dto);
	
	/** 신규 배송지 삽입 */
	public int do_save(DTO dto);
	
	/** 기존 배송지 조회*/
	public List<?> get_address(DTO dto);
	
	/** 주문 목록 payList*/
	public List<?> get_retrieve(DTO dto);
	
	/**주문 상세 정보*/
	public int do_paydetail(DTO dto);
	
}
