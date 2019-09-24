package kr.co.redbull.product;

//import static com.ehr.service.UserServiceImpl.MIN_LOGINCOUNT_FOR_SILVER;
//import static com.ehr.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//알파벳 순으로 테스트 수행
public class DaoBoardTest {
	
	private Logger LOG = LoggerFactory.getLogger(DaoBoardTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private BoardDaoImpl boardDaoImpl;
	
	List<Board> list;
	
	//public Board(String boardId, String title, int readCnt, String contents, String regId, String regDt) {
	@Before
	public void setUp() {
		list = Arrays.asList(
				new Board("noBoardId","J01제목_125",0,"J01내용_125","J01작성자_125","no_date"),
				new Board("noBoardId","J02제목_125",0,"J02내용_125","J02작성자_125","no_date"),
				new Board("noBoardId","J03제목_125",0,"J03내용_125","J03작성자_125","no_date"),
				new Board("noBoardId","J04제목_125",0,"J04내용_125","J04작성자_125","no_date"),
				new Board("noBoardId","J05제목_125",0,"J05내용_125","J05작성자_125","no_date")
				);
				
				
	}
		
	@After
	public void tearDown() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("99 tearDown()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
	}
	
	@Test
	public void get_retrieve() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("_125");
		List<Board> getList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		for(Board vo:getList) {
			boardDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Board vo:list) { 
			int flag = boardDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Board> addlistData = (List<Board>) boardDaoImpl.get_retrieve(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void do_update() {
		//01. 기존 데이터 삭제
		Search search = new Search();
		search.setSearchWord("제목_125");
		List<Board> getList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		for(Board vo : getList) {
			int flag = boardDaoImpl.do_delete(vo);			
		}		
		//02. 데이터 추가
		for(Board vo : list) {
			int flag = boardDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//02.01.데이터 조회
		List<Board> addListData = (List<Board>) boardDaoImpl.get_boardIdList(search);
		assertThat(5, is(addListData.size()));
		
		//03. 데이터 수정
		Board changeData = addListData.get(0);
		changeData.setTitle(changeData.getTitle()+"_U");
		changeData.setContents(changeData.getContents()+"_U");
		changeData.setRegId(changeData.getRegId()+"_U");
		
		//03.01. 수정 데이터 등록
		int flag = boardDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
		
		//04. 등록 데이터 조회
		addListData = (List<Board>) boardDaoImpl.get_boardIdList(search);
		
		//05. 비교
		checkData(changeData,addListData.get(0));
		
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		//0.데이터 삭제
		Search search = new Search();
		search.setSearchWord("제목_125");
		List<Board> getList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		for(Board vo : getList) {
			int flag = boardDaoImpl.do_delete(vo);			
		}
		
		//1.데이터 등록
		for(Board vo : list) {
			int flag = boardDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//2.데이터 조회
		List<Board> getList2 = (List<Board>) boardDaoImpl.get_boardIdList(search);
		assertThat(5, is(getList2.size()));
		
		//3.데이터 비교:boardId는 자동 증가이므로 비교 할 수 없음.(제목,내용,등록자)
		for(int i=0; i<getList2.size(); i++ ) {
			checkData(getList2.get(i),list.get(i));
		}
		
	}
	
	private void checkData(Board org,Board vs) {
		assertThat(org.getTitle(), is(vs.getTitle()));
		assertThat(org.getContents(), is(vs.getContents()));
		assertThat(org.getRegId(), is(vs.getRegId()));		
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("====================================================");
		LOG.debug("=context="+context);
		LOG.debug("=boardDaoImpl="+boardDaoImpl);
		LOG.debug("====================================================");
		assertThat(context, is(notNullValue()));
		assertThat(boardDaoImpl, is(notNullValue()));
	}	
}
