package kr.co.redbull.cart.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.WorkDiv;

@Repository
public class CartDaoImpl implements WorkDiv {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.cart"; //상수

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
		String statement = this.NAMESPACE+".do_save";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.insert(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. falg: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

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

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
