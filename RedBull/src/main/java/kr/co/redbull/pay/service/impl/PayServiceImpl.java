package kr.co.redbull.pay.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.pay.service.PayService;

@Service
public class PayServiceImpl implements PayService {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	private PayDaoImpl payDaoImpl; 
	
	@Override
	public int do_delete(DTO dto) {
		
		return payDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		
		return payDaoImpl.do_save(dto);
	}

	@Override
	public List<?> get_address(DTO dto) {
		
		return payDaoImpl.get_address(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		
		return payDaoImpl.get_retrieve(dto);
	}

	@Override
	public int do_paydetail(DTO dto) {
		
		return payDaoImpl.do_paydetail(dto);
	}



}
