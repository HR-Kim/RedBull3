package kr.co.redbull.pay.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sun.mail.imap.protocol.Namespaces.Namespace;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.WorkDiv;
import kr.co.redbull.pay.service.Pay;

@Repository
public class PayDaoImpl implements WorkDiv {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.pay";

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	//결제 시 장바구니 삭제
	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE+".do_delete";
		Pay pay = (Pay) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + pay);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.delete(statement, pay);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

	
	//배송지 삽입
	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE+".do_save";
		Pay pay = (Pay) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + pay);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.insert(statement, pay);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}

	@Override
	public DTO get_selectOne(DTO dto) {

		return null;
	}
	
	//주문 상세 정보 삽입
	public int do_paydetail(DTO dto) {
		String statement = this.NAMESPACE+".do_paydetail";
		Pay pay = (Pay) dto;
		LOG.debug("================================");
		LOG.debug("1. statement: " + statement);
		LOG.debug("================================");
		
		LOG.debug("================================");
		LOG.debug("2. param: " + pay);
		LOG.debug("================================");
		
		int flag = this.sqlSessionTemplate.insert(statement, pay);
		LOG.debug("================================");
		LOG.debug("3. flag: " + flag);
		LOG.debug("================================");
		
		return flag;
	}
	
	//주문목록 List
	@Override
	public List<?> get_retrieve(DTO dto) {
		String statement = this.NAMESPACE +".get_retrieve";
		Search inVO = (Search) dto;
		LOG.debug("==================================");
		LOG.debug("1. param: " +inVO);
		LOG.debug("2. statement: " + statement);
		LOG.debug("==================================");
		
		List<Pay> list = this.sqlSessionTemplate.selectList(statement, inVO);
		
		LOG.debug("==================================");
		LOG.debug("3. list: " + list);
		LOG.debug("==================================");
		
		return list;
	}
	
	//기존 배송지 조회 -추후 삭제
	public List<?> get_address(DTO dto) {
		String statement = this.NAMESPACE +".get_address";
		Search inVO = (Search) dto;
		LOG.debug("==================================");
		LOG.debug("1. param: " +inVO);
		LOG.debug("2. statement: " + statement);
		LOG.debug("==================================");
		
		List<Pay> list = this.sqlSessionTemplate.selectList(statement, inVO);
		
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
