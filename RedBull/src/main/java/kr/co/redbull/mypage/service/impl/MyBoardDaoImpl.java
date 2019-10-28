package kr.co.redbull.mypage.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.WorkDiv;
import kr.co.redbull.mypage.service.MyBoard;

@Repository
public class MyBoardDaoImpl implements WorkDiv {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.mypage";
	
	@Override
	public List<?> get_retrieve(DTO dto) {
		Search search = (Search)dto;
		String statement = NAMESPACE + ".get_aRetrieve";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+search);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		List<MyBoard> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==============================");
		LOG.debug("3.list:"+list);
		LOG.debug("==============================");
		
		return list;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
