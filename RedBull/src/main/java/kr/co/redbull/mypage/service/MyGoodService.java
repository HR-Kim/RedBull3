package kr.co.redbull.mypage.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface MyGoodService {
	
	/**수정 */
	public int do_update(DTO dto);

	/**삭제 */
	public int do_delete(DTO dto);

	/**저장 */
	public int do_save(DTO dto);

	/**단건조회 */
	public DTO get_selectOne(DTO dto);
	
	/**목록조회 */
	public List<?> get_retrieve(DTO dto);
	
	/**동일상품 확인*/
	public int countCart(DTO dto);
}
