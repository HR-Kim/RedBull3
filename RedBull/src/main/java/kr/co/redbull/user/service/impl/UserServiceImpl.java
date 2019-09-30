package kr.co.redbull.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.ExcelWriter;
import kr.co.redbull.user.service.User;
import kr.co.redbull.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExcelWriter excelWriter;

	// Dao와 연결
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Override
	public DTO get_selectOne(DTO dto) {
		
		LOG.debug("==========================");
		LOG.debug("=@Service=" + dto);
		LOG.debug("==========================");
		
		return userDaoImpl.get_selectOne(dto);
	}

	@Override
	public int do_update(DTO dto) {
		
		return userDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		
		return userDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		
		return userDaoImpl.do_save(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get_excelDown(User vo, String ext) {

		// 헤더를 리스트로 만들고
		List<String> headers = Arrays.asList("아이디"
											,"비밀번호"
											,"이름"
											,"생년월일"
											,"휴대폰번호"
											,"우편번호"
											,"주소"
											,"자세한주소"
											,"레벨"
											,"포인트"
											,"이미지번호"
											,"등록일");
		
		// 데이터 조회해서 리스트로 만듦
		List<User> list = (List<User>) userDaoImpl.get_selectOne(vo);
		
		String saveFileNm = "";
		
		// 확장자에 따라 excelWriter의 엑셀 파일 생성 메소드를 호출
		if(ext.equalsIgnoreCase("csv")) {
			
			saveFileNm = excelWriter.csvWriterGeneralization(list, headers);
			
		}
		else if(ext.equalsIgnoreCase("xlsx")) {
			
			saveFileNm = excelWriter.xlsxWriterGeneralization(list, headers);
		}
		else if(ext.equalsIgnoreCase("xls")) {
			
			saveFileNm = excelWriter.xlsWriterGeneralization(list, headers);
		}
		
		return saveFileNm; // 생성한 엑셀 파일의 저장파일명을 반환
		
	}

}//--class
