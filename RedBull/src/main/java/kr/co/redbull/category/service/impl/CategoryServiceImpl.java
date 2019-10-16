package kr.co.redbull.category.service.impl;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.category.service.CategoryService;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.product.service.impl.ProductDaoImpl;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	ProductDaoImpl productDaoImpl;

	@Override
	public List<?> get_newList(DTO dto) {
		return productDaoImpl.get_newList(dto);
	}

	@Override
	public List<?> get_rankList(DTO dto) {
		return productDaoImpl.get_rankList(dto);
	}

	@Override
	public List<?> get_hotSaleList(DTO dto) {
		// TODO Auto-generated method stub
		return productDaoImpl.get_hotSaleList(dto);
	}

	@Override
	public List<?> get_categoryList(DTO dto) {
		// TODO Auto-generated method stub
		return productDaoImpl.get_categoryList(dto);
	}



}
