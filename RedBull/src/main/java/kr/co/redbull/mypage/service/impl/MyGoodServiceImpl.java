package kr.co.redbull.mypage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.mypage.service.MyGoodService;

public class MyGoodServiceImpl implements MyGoodService{
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyGoodDaoImpl myGoodDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return myGoodDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return myGoodDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return myGoodDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return myGoodDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return myGoodDaoImpl.get_retrieve(dto);
	}
	
}
