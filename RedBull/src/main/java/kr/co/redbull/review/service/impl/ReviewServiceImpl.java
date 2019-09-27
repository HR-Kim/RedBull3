package kr.co.redbull.review.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.review.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReviewDaoImpl reviewDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return reviewDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return reviewDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return reviewDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return reviewDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return reviewDaoImpl.get_retrieve(dto);
	}
}
