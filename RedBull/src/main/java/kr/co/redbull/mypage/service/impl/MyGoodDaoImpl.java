package kr.co.redbull.mypage.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.redbull.mypage.service.MyGood;
import kr.co.redbull.product.service.Product;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.WorkDiv;

@Repository
public class MyGoodDaoImpl implements WorkDiv {

	Logger LOG = LoggerFactory.getLogger(MyGoodDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.mypage";

	public List<?> get_pnameList(DTO dto) {
		String statement = NAMESPACE+".get_pnameList";
		Search search = (Search) dto;
		LOG.debug("======================================");
		LOG.debug("=1.param="+search);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=2.statement="+statement);
		LOG.debug("======================================");
		
		List<Product> list = this.sqlSessionTemplate.selectList(statement, search);
		LOG.debug("======================================");
		LOG.debug("=2.list="+list);
		LOG.debug("======================================");
		
		return list;
	}
	

	@Override
	public int do_delete(DTO dto) {
		MyGood mygood = (MyGood) dto;
		String statement = NAMESPACE + ".do_delete";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+mygood);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.delete(statement, mygood);
		
		LOG.debug("==============================");
		LOG.debug("3.flag:"+flag);
		LOG.debug("==============================");
		
		return flag;
	}

	@Override
	public int do_save(DTO dto) {
		MyGood mygood = (MyGood) dto;
		String statement = NAMESPACE + ".do_save";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+mygood);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.insert(statement, mygood);
		
		LOG.debug("==============================");
		LOG.debug("3.flag:"+flag);
		LOG.debug("==============================");
		
		return flag;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		MyGood mygood = (MyGood) dto;
		String statement = NAMESPACE + ".get_selectOne";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+mygood);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		MyGood outVO = sqlSessionTemplate.selectOne(statement, mygood);
		
		LOG.debug("==============================");
		LOG.debug("3.outVO:"+outVO);
		LOG.debug("==============================");
		
		return outVO;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		Search search = (Search)dto;
		String statement = NAMESPACE + ".get_myGoodList";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+search);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		List<MyGood> list = sqlSessionTemplate.selectList(statement, search);
		
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
	
	

}
