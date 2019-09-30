package kr.co.redbull.review;

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
import kr.co.redbull.review.service.Review;
import kr.co.redbull.review.service.impl.ReviewDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//알파벳 순으로 테스트 수행
public class DaoReviewTest {
	
	private Logger LOG = LoggerFactory.getLogger(DaoReviewTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ReviewDaoImpl reviewDaoImpl;
	
	List<Review> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new Review("nornum","71","1","contents1","J01등록자_125","nodate","noUser","noDate"),
				new Review("nornum","72","2","contents2","J02등록자_125","nodate","noUser","noDate"),
				new Review("nornum","73","3","contents3","J03등록자_125","nodate","noUser","noDate"),
				new Review("nornum","74","4","contents4","J04등록자_125","nodate","noUser","noDate"),
				new Review("nornum","75","5","contents5","J05등록자_125","nodate","noUser","noDate")
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
		List<Review> getList = (List<Review>) reviewDaoImpl.get_pnumList(search);
		for(Review vo:getList) {
			reviewDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Review vo:list) { 
			int flag = reviewDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		List<Review> addlistData = (List<Review>) reviewDaoImpl.get_pnumList(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void do_update() {
		//01. 기존 데이터 삭제
		Search search = new Search();
		search.setSearchWord("");
		List<Review> getList = (List<Review>) reviewDaoImpl.get_pnumList(search);
		for(Review vo : getList) {
			int flag = reviewDaoImpl.do_delete(vo);			
		}		
		//02. 데이터 추가
		for(Review vo : list) {
			int flag = reviewDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//02.01.데이터 조회
		List<Review> addListData = (List<Review>) reviewDaoImpl.get_pnumList(search);
		assertThat(5, is(addListData.size()));
		
		//03. 데이터 수정
		Review changeData = addListData.get(0);
		changeData.setScore("1.5");
		changeData.setContents(changeData.getContents()+"_U");
		
		
		//03.01. 수정 데이터 등록
		int flag = reviewDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
		
		//04. 등록 데이터 조회
		addListData = (List<Review>) reviewDaoImpl.get_pnumList(search);
		
		//05. 비교
		checkData(changeData,addListData.get(0));
		
	}
	
	@Test
	public void addAndGet() {
		//0.데이터 삭제
		Search search = new Search();
		search.setSearchWord("");
		List<Review> getList = (List<Review>) reviewDaoImpl.get_pnumList(search);
		for(Review vo : getList) {
			int flag = reviewDaoImpl.do_delete(vo);			
		}
		//1.데이터 등록
		for(Review vo : list) {
			int flag = reviewDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//2.데이터 조회
		List<Review> getList2 = (List<Review>) reviewDaoImpl.get_pnumList(search);
		assertThat(5, is(getList2.size()));
		
		//3.데이터 비교:boardId는 자동 증가이므로 비교 할 수 없음.(제목,내용,등록자)
		for(int i=0; i<getList2.size(); i++ ) {
			checkData(getList2.get(i),list.get(i));
		}		
	}
	
	private void checkData(Review org,Review vs) {
		assertThat(org.getScore(), is(vs.getScore()));	
		assertThat(org.getContents(), is(vs.getContents()));
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("====================================================");
		LOG.debug("=context="+context);
		LOG.debug("=boardDaoImpl="+reviewDaoImpl);
		LOG.debug("====================================================");
		assertThat(context, is(notNullValue()));
		assertThat(reviewDaoImpl, is(notNullValue()));
	}	
}
