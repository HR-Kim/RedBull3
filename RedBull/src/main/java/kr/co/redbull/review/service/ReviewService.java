package kr.co.redbull.review.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;

public interface ReviewService {
	/**pNum으로 조회 */
	public List<?> get_pNumList(DTO dto);
	
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
	
}
