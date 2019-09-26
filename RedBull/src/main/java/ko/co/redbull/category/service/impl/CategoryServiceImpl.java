package ko.co.redbull.category.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ko.co.redbull.category.service.CategoryService;
import kr.co.redbull.cmn.DTO;

public class CategoryServiceImpl implements CategoryService {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@Override
	public List<?> get_retrieve(DTO dto) {
		
		return categoryServiceImpl.get_retrieve(dto);
	}

}
