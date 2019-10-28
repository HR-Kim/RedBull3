package kr.co.redbull.mypage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.mypage.service.MyBoardService;

@Service
public class MyBoardServiceImpl implements MyBoardService {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyBoardDaoImpl myBoardDaoImpl;

	@Override
	public List<?> get_aRetrieve(DTO dto) {
		return myBoardDaoImpl.get_retrieve(dto);
	}

}
