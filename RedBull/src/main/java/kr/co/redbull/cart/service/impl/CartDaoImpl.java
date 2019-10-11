package kr.co.redbull.cart.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.WorkDiv;

@Repository
public class CartDaoImpl implements WorkDiv {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.cart"; //상수
	
	
	//할인가격
	public int sale(DTO dto) {
		String statement = this.NAMESPACE+".sale";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}
	//배송비
	public int delivery(DTO dto) {
		String statement = this.NAMESPACE+".delivery";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}
	
	//총 상품금액
	public int total(DTO dto) {
		String statement = this.NAMESPACE+".total";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}
	
	public int countCart(DTO dto) {
		String statement = this.NAMESPACE+".countCart";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.selectOne(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

	public int updateCart(DTO dto) {
		String statement = this.NAMESPACE+".updateCart";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.update(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}
	
	@Override
	public int do_update(DTO dto) {
		String statement = this.NAMESPACE+".do_update";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.update(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE+".do_delete";
		Cart cart = (Cart) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + cart);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.delete(statement, cart);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

	//장바구니 추가
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
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		String statement = this.NAMESPACE +".get_selectOne";
		Cart inVO = (Cart) dto;
		LOG.debug("==================================");
		LOG.debug("1. param: " + inVO);
		LOG.debug("2. statement: " + statement);
		LOG.debug("==================================");
		
		Cart outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("==================================");
		LOG.debug("3. flag: " + outVO);
		LOG.debug("==================================");
		
		return outVO;
	}
	
	public List<?> get_cartIdList(DTO dto){
		String statement = this.NAMESPACE + ".get_cartIdList";
		Search search = (Search) dto;
		
		LOG.debug("==================================");
		LOG.debug("1. param: " +search);
		LOG.debug("2. statement: " + statement);
		LOG.debug("==================================");
		
		List<Cart> list = this.sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("3. list: " + list);
		LOG.debug("==================================");
		
		return list;
	}
	
	
	@Override
	public List<?> get_retrieve(DTO dto) {
		String statement = this.NAMESPACE +".get_retrieve";
		Search inVO = (Search) dto;
		LOG.debug("==================================");
		LOG.debug("1. param: " +inVO);
		LOG.debug("2. statement: " + statement);
		LOG.debug("==================================");
		
		List<Cart> list = this.sqlSessionTemplate.selectList(statement, inVO);
		
		LOG.debug("==================================");
		LOG.debug("3. list: " + list);
		LOG.debug("==================================");
		
		return list;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
