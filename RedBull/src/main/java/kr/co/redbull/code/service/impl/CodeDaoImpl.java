package kr.co.redbull.code.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.cmn.WorkDiv;
import kr.co.redbull.code.service.Code;

public class CodeDaoImpl implements WorkDiv  {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Code> userMapper = new RowMapper<Code>() {

		@Override
		public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
			Code vo = new Code();
			vo.setCodeId(rs.getString("code_id"));
			vo.setCodeNm(rs.getString("code_nm"));
			vo.setNum(rs.getInt("num"));
			return vo;
		}
		
	};
	
	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO get_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> get_retrieve(DTO dto) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT a.code_id,                   \n");
		sb.append("        a.code_nm,                   \n");
		sb.append("        a.num                        \n");
		sb.append(" FROM code a JOIN code_type b        \n");
		sb.append(" ON a.code_type_id = b.code_type_id  \n");
		sb.append(" WHERE a.code_type_id = ?            \n");
		sb.append(" AND a.use_yn = 1                    \n");
		sb.append(" ORDER BY a.num                      \n");
		Code inVo = (Code) dto;
		LOG.debug("=============================================================");
		LOG.debug("=inVo="+inVo);
		LOG.debug("=============================================================");
		LOG.debug("=============================================================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=============================================================");
		
		List<Code> list = this.jdbcTemplate.query(sb.toString()
				, new Object[] {inVo.getCodeId()}
				, userMapper);
		LOG.debug("=============================================================");
		LOG.debug("=list=\n"+list);
		LOG.debug("=============================================================");
		return list;
	}

	@Override
	public List<?> get_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
