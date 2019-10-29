package kr.co.redbull.mypage.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface MyBoardService {
	
	/**목록조회 */
	public List<?> get_aRetrieve(DTO dto);
	
}
