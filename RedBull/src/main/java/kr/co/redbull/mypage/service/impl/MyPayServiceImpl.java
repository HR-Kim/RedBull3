package kr.co.redbull.mypage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.mypage.service.MyPayService;

@Service
public class MyPayServiceImpl implements MyPayService{
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyPayDaoImpl myPageDaoImpl;

	@Override
	public List<?> get_payRetrieve(DTO dto) {
		return myPageDaoImpl.get_retrieve(dto);
	}
}
