package kr.co.redbull.opt;

import static org.hamcrest.CoreMatchers.is;
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

import kr.co.redbull.cmn.Search;
import kr.co.redbull.opt.service.Opt;
import kr.co.redbull.opt.service.impl.OptDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//알파벳 순으로 테스트 수행
public class DaoOptTest {
	
	private Logger LOG = LoggerFactory.getLogger(DaoOptTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private OptDaoImpl optDaoImpl;
	
	List<Opt> list;
	
	//public Board(String boardId, String title, int readCnt, String contents, String regId, String regDt) {
	@Before
	public void setUp() {
		list = Arrays.asList(
				new Opt("noOnum","J01옵션명_125",10000,"391","1"),
				new Opt("noOnum","J02옵션명_125",20000,"391","1"),
				new Opt("noOnum","J03옵션명_125",30000,"391","1"),
				new Opt("noOnum","J04옵션명_125",40000,"391","1"),
				new Opt("noOnum","J05옵션명_125",50000,"391","1")
				);
				
				
	}
		
	@After
	public void tearDown() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("99 tearDown()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
	}
	
	@Test
	@Ignore
	public void get_retrieve() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("");
		List<Opt> getList = (List<Opt>) optDaoImpl.get_pNumList(search);
		for(Opt vo:getList) {
			optDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Opt vo:list) { 
			int flag = optDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Opt> addlistData = (List<Opt>) optDaoImpl.get_retrieve(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void do_update() {
		//01. 기존 데이터 삭제
		Search search = new Search();
		search.setSearchWord("125");
		List<Opt> getList = (List<Opt>) optDaoImpl.get_pNumList(search);
		for(Opt vo : getList) {
			int flag = optDaoImpl.do_delete(vo);			
		}		
		//02. 데이터 추가
		for(Opt vo : list) {
			int flag = optDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//02.01.데이터 조회
		List<Opt> addListData = (List<Opt>) optDaoImpl.get_pNumList(search);
		assertThat(5, is(addListData.size()));
		
		//03. 데이터 수정
		Opt changeData = addListData.get(0);
		changeData.setoName("J01옵션명_125_U");
		changeData.setoPrice(99999);
		
		//03.01. 수정 데이터 등록
		int flag = optDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
		
		//04. 등록 데이터 조회
		addListData = (List<Opt>) optDaoImpl.get_pNumList(search);
		
		//05. 비교
		checkData(changeData,addListData.get(0));
		
	}
	
	@Test
	public void addAndGet() {
		//0.데이터 삭제
		Search search = new Search();
		search.setSearchWord("");
		List<Opt> getList = (List<Opt>) optDaoImpl.get_pNumList(search);
		for(Opt vo : getList) {
			int flag = optDaoImpl.do_delete(vo);			
		}
		//1.데이터 등록
		for(Opt vo : list) {
			int flag = optDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//2.데이터 조회
		List<Opt> getList2 = (List<Opt>) optDaoImpl.get_pNumList(search);
		assertThat(5, is(getList2.size()));
		
		//3.데이터 비교:boardId는 자동 증가이므로 비교 할 수 없음.(제목,내용,등록자)
		for(int i=0; i<getList2.size(); i++ ) {
			checkData(getList2.get(i),list.get(i));
		}		
	}
	
	private void checkData(Opt org,Opt vs) {
		assertThat(org.getoName(), is(vs.getoName()));
		assertThat(org.getoPrice(), is(vs.getoPrice()));	
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("====================================================");
		LOG.debug("=context="+context);
		LOG.debug("=boardDaoImpl="+optDaoImpl);
		LOG.debug("====================================================");
		assertThat(context, is(notNullValue()));
		assertThat(optDaoImpl, is(notNullValue()));
	}	
}
