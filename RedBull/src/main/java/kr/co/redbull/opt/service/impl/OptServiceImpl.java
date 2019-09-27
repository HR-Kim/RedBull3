package kr.co.redbull.opt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.opt.service.OptService;

@Service
public class OptServiceImpl implements OptService{
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OptDaoImpl optDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return optDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return optDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return optDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return optDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return optDaoImpl.get_retrieve(dto);
	}
}
