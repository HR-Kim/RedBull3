package kr.co.redbull.product.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.WorkDiv;
import kr.co.redbull.product.service.Product;

@Repository
public class ProductDaoImpl implements WorkDiv {
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.product";

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
	public int do_save(DTO dto) {
		String statement = NAMESPACE+".do_save";
		Product product = (Product) dto;
		LOG.debug("======================================");
		LOG.debug("=1.param="+product);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=2.statement="+statement);
		LOG.debug("======================================");
		
		int flag = this.sqlSessionTemplate.insert(statement, product);
		LOG.debug("======================================");
		LOG.debug("=2.flag="+flag);
		LOG.debug("======================================");
		
		return flag;
	}
	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE+".do_update";
		Product product = (Product) dto;
		LOG.debug("======================================");
		LOG.debug("=1.param="+product);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=2.statement="+statement);
		LOG.debug("======================================");
		
		int flag = this.sqlSessionTemplate.update(statement, product);
		LOG.debug("======================================");
		LOG.debug("=2.flag="+flag);
		LOG.debug("======================================");
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE+".do_delete";
		Product product = (Product) dto;
		LOG.debug("======================================");
		LOG.debug("=1.param="+product);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=2.statement="+statement);
		LOG.debug("======================================");
		
		int flag = this.sqlSessionTemplate.delete(statement, product);
		LOG.debug("======================================");
		LOG.debug("=2.flag="+flag);
		LOG.debug("======================================");
		
		return flag;
	}


	@Override
	public DTO get_selectOne(DTO dto) {
		String statement = NAMESPACE+".get_selectOne";
		Product product = (Product) dto;
		LOG.debug("======================================");
		LOG.debug("=1.param="+product);
		LOG.debug("======================================");
		
		LOG.debug("======================================");
		LOG.debug("=2.statement="+statement);
		LOG.debug("======================================");
		
		Product outVO = this.sqlSessionTemplate.selectOne(statement, product);
		LOG.debug("======================================");
		LOG.debug("=2.outVO="+outVO);
		LOG.debug("======================================");
		
		return outVO;
	}	
	
	@Override
	public List<?> get_retrieve(DTO dto) {
		String statement = NAMESPACE+".get_retrieve";
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
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
