package kr.co.redbull.file.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;


public interface FileService {
	
	/**파일 Num자동증가 */
	public int num_max_plus_one(DTO dto);
	
	/**파일개수 조회*/
	public int get_file_count(DTO dto);
	
	/**삭제*/
	public int do_delete(DTO dto);
	
	/**등록*/
	public int do_save(DTO dto);
	
	/**목록조회*/
	public List<?> get_retrieve(DTO dto);
}
