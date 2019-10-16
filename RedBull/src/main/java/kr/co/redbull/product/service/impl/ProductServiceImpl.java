package kr.co.redbull.product.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductDaoImpl productDaoImpl;
	
	@Override
	public DTO get_nextPnum() {
		return productDaoImpl.get_nextPnum();
	}
	
	@Override
	public int do_update(DTO dto) {
		return productDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return productDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return productDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return productDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return productDaoImpl.get_retrieve(dto);
	}

	@Override
	public String excelDown(Search vo, String ext) {
		// TODO Auto-generated method stub
		return null;
	}

}
