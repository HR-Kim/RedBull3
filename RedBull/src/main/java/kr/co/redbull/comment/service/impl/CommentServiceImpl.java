package kr.co.redbull.comment.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CommentDaoImpl CommentsDaoImpl;

	@Override
	public int do_update(DTO dto) {
		return CommentsDaoImpl.do_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return CommentsDaoImpl.do_delete(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return CommentsDaoImpl.do_save(dto);
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		return CommentsDaoImpl.get_selectOne(dto);
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		return CommentsDaoImpl.get_retrieve(dto);
	}

}
