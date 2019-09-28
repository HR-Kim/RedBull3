package kr.co.redbull.user.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.WorkDiv;
import kr.co.redbull.user.service.User;

/**회원관리 Dao*/
@Repository
public class UserDaoImpl implements WorkDiv {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; // mybatis
	
	private final String NAMESPACE = "kr.co.redbull.user"; // 쿼리찾을 때 필요
		

	/**회원정보 수정*/
	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**회원 탈퇴*/
	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**회원 가입(회원정보 등록)*/
	@Override
	public int do_save(DTO dto) {
		
		String statement = this.NAMESPACE + ".do_save"; // kr.co.redbull.user.do_save
		
		User user = (User) dto;
		
		LOG.debug("=============================");
		LOG.debug("01. statement="+statement);
		LOG.debug("=============================");			
		
		LOG.debug("=============================");
		LOG.debug("02. param="+user);
		LOG.debug("=============================");	
		
		int flag = this.sqlSessionTemplate.insert(statement, user);
		LOG.debug("=============================");
		LOG.debug("03. flag="+flag);
		LOG.debug("=============================");	
		
		return flag;
	}

	/**비밀번호 찾기(회원정보 단건 조회)*/
	@Override
	public DTO get_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/**단건 회원정보 엑셀 다운로드*/
	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}