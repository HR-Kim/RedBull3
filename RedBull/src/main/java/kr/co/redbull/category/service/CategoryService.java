package kr.co.redbull.category.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;

public interface CategoryService {
	/**신상품 조회 */
	public List<?> get_newList(DTO dto);
	/**베스트상품 조회 */
	public List<?> get_rankList(DTO dto);
	/**세일상품 조회 */
	public List<?> get_hotSaleList(DTO dto);
	/**카테고리 조회 */
	public List<?> get_categoryList(DTO dto);
	

}
