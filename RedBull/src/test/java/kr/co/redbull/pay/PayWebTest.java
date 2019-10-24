package kr.co.redbull.pay;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.co.redbull.pay.service.impl.PayDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml" }) //배열
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Test N mAME_ASCENDING으로 수행
public class PayWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	PayDaoImpl payDaoImpl;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		LOG.debug("================================");
		LOG.debug("=context=" + context);
		LOG.debug("=mockMvc=" + mockMvc);
		LOG.debug("=payDaoImpl=" + payDaoImpl);
		LOG.debug("================================");
	}
	
	
	@Test
	@Ignore
	public void do_save() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/pay/do_save.do")
				.param("orderNum", "84") //param연결
				.param("total", "50000")
				.param("payCnt", "1")
				.param("dName", "영은")
				.param("dPostNum", "21111")
				.param("dAddr", "인천광역시")
				.param("dDetAddr", "연수구")
				.param("dPhone", "010-0000-0000")
				.param("dMemo", "경비실")
				.param("payMethod", "카드")
				.param("payId", "fnnjdd@naver.com");
		
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
		
		String result = resultActions.andDo(print())
									.andReturn()
									.getResponse().getContentAsString();
	
		LOG.debug("===============================");
		LOG.debug("=result="+result);
		LOG.debug("===============================");
	}
	
	
	@Test //안됨
	public void get_retrieve() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/pay/get_retrieve.do")
				//.param("pageSize", "10")
				//.param("pageNum", "1")
				//.param("searchDiv", "10")
				//.param("searchWord", "fnnjdd@naver.com");
				.param("rid", "fnnjdd@naver.com");

	
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
}
