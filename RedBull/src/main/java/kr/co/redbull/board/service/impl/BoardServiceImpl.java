package kr.co.redbull.board.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.board.service.BoardService;
import kr.co.redbull.cmn.DTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardDaoImpl boardDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return boardDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return boardDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return boardDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return boardDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return boardDaoImpl.get_retrieve(dto);
	}

}
