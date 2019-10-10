package kr.co.redbull.mypage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import kr.co.redbull.cmn.Search;
import kr.co.redbull.mypage.service.MyGood;
import kr.co.redbull.mypage.service.impl.MyGoodDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//알파벳 순으로 테스트 수행
public class DaoMyGoodTest {

	private Logger LOG = LoggerFactory.getLogger(DaoMyGoodTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MyGoodDaoImpl myGoodDaoImpl;
	
	List<MyGood> list;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new MyGood(0,"135","J01링크_135","J01등록자_135","noDate"),
				new MyGood(0,"136","J02링크_135","J01등록자_135","noDate"),
				new MyGood(0,"137","J03링크_135","J01등록자_135","noDate"),
				new MyGood(0,"138","J04링크_135","J01등록자_135","noDate"),
				new MyGood(0,"139","J05링크_135","J01등록자_135","noDate")
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
		search.setSearchWord("125");
		List<MyGood> getList = (List<MyGood>) myGoodDaoImpl.get_pnameList(search);
		for(MyGood vo:getList) {
			myGoodDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(MyGood vo:list) { 
			int flag = myGoodDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<MyGood> addlistData = (List<MyGood>) myGoodDaoImpl.get_retrieve(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("====================================================");
		LOG.debug("=context="+context);
		LOG.debug("=myGoodDaoImpl="+myGoodDaoImpl);
		LOG.debug("====================================================");
		assertThat(context, is(notNullValue()));
		assertThat(myGoodDaoImpl, is(notNullValue()));
	}
	
	@Test
	//@Ignore
	public void do_save() {
		myGoodDaoImpl.do_save(list.get(0));
	}	
	
	@Test
	@Ignore
	public void do_delete() {
		myGoodDaoImpl.do_delete(list.get(0));		
	}
	
}