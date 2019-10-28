package kr.co.redbull.mypage;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
	MyGoodDaoImpl myGoodDaoImpl;
	
	List<MyGood> list;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^");
		list = Arrays.asList(
				//new Cart(1,396,1,"cart_130"),
				//new Cart(2,396,1,"cart_131")
				);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //mockMvc생성자
		LOG.debug("================================");
		LOG.debug("=context=" + context);
		LOG.debug("=mockMvc=" + mockMvc);
		LOG.debug("=myGoodDaoImpl=" + myGoodDaoImpl);
		LOG.debug("================================");
	}
	
	@Test
	//@Ignore
	public void get_retrieve() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/good/get_retrieve.do")
				.param("pageSize", "10")
				.param("pageNum", "1")
				.param("searchDiv", "10")
				.param("searchWord", "test01");

	
		//url 호출 , 결과 return
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().isOk());
		
		String result = resultActions.andDo(print())
									.andReturn()
									.getResponse().getContentAsString();
	
		LOG.debug("===============================");
		LOG.debug("=result="+result);
		LOG.debug("===============================");
	}
	
	@Test 
	public void getBean() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("=getBean()=");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
	}
	
	@After
	public void tearDown() {
		LOG.debug("tearDown()");
	}
	
	@Test
	@Ignore
	public void do_save() {
		myGoodDaoImpl.do_save(list.get(0));
	}	
	
	@Test
	@Ignore
	public void do_delete() {
		myGoodDaoImpl.do_delete(list.get(0));		
	}
	
}
