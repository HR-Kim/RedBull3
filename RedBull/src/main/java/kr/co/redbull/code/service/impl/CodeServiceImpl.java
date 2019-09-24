package kr.co.redbull.code.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.code.service.CodeService;

public class CodeServiceImpl implements CodeService {
	@Autowired
	private CodeDaoImpl codeDaoImpl;
	@Override
	public List<?> get_retrieve(DTO dto) {
		
		return codeDaoImpl.get_retrieve(dto);
	}
}
