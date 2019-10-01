package kr.co.redbull.comment.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.redbull.board.service.Board;
import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.WorkDiv;
import kr.co.redbull.comment.service.Comment;

@Repository
public class CommentDaoImpl implements WorkDiv {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.redbull.comment";
	
	public List<?> get_commentIdList(DTO dto){
		Search search = (Search)dto;
		String statement = NAMESPACE + ".get_commentIdList";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+search);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		List<Board> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==============================");
		LOG.debug("3.list:"+list);
		LOG.debug("==============================");
		
		return list;
	}
	
	@Override
	public int do_update(DTO dto) {
		Comment comment = (Comment)dto;
		String statement = NAMESPACE + ".do_update";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+comment);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.update(statement, comment);
		
		LOG.debug("==============================");
		LOG.debug("3.flag:"+flag);
		LOG.debug("==============================");
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		Comment comment = (Comment)dto;
		String statement = NAMESPACE + ".do_delete";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+comment);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.update(statement, comment);
		
		LOG.debug("==============================");
		LOG.debug("3.flag:"+flag);
		LOG.debug("==============================");
		
		return flag;
	}

	@Override
	public int do_save(DTO dto) {
		Comment comment = (Comment)dto;
		String statement = NAMESPACE + ".do_save";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+comment);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.update(statement, comment);
		
		LOG.debug("==============================");
		LOG.debug("3.flag:"+flag);
		LOG.debug("==============================");
		
		return flag;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		Comment comment = (Comment)dto;
		String statement = NAMESPACE + ".get_selectOne";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+comment);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		Board outVO = sqlSessionTemplate.selectOne(statement, comment);
		
		LOG.debug("==============================");
		LOG.debug("3.outVO:"+outVO);
		LOG.debug("==============================");
		
		return outVO;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		Search search = (Search)dto;
		String statement = NAMESPACE + ".get_retrieve";
		
		LOG.debug("==============================");
		LOG.debug("1.param:"+search);
		LOG.debug("==============================");
		
		LOG.debug("==============================");
		LOG.debug("2.statement:"+statement);
		LOG.debug("==============================");
		
		List<Board> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==============================");
		LOG.debug("3.list:"+list);
		LOG.debug("==============================");
		
		return list;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
