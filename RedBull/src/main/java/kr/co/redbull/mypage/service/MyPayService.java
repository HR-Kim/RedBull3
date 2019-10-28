package kr.co.redbull.mypage.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface MyPayService {
	
	/** 구매 목록조회 */
	public List<?> get_payRetrieve(DTO dto);
}
