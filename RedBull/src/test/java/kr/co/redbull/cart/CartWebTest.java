package kr.co.redbull.cart;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
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

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cart.service.impl.CartDaoImpl;
import kr.co.redbull.cmn.Search;
import oracle.sql.ARRAY;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml" }) //배열
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Test N mAME_ASCENDING으로 수행
public class CartWebTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	CartDaoImpl cartDaoImpl;
	
	List<Cart> list;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^");
		list = Arrays.asList(
				new Cart(1,336,1,"cart_130"),
				new Cart(2,367,1,"cart_131")
				);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //mockMvc생성자
		LOG.debug("================================");
		LOG.debug("=context=" + context);
		LOG.debug("=mockMvc=" + mockMvc);
		LOG.debug("=cartDaoImpl=" + cartDaoImpl);
		LOG.debug("================================");
	}
	
	//cart 조회 test
	@Test
	@Ignore
	public void get_retrieve() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/cart/get_retrieve.do")
				.param("searchDiv", "10")
				.param("searchWord", "_130")
				.param("pageSize", "10")
				.param("pageNum", "1");
	
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
	
	//cart 수량 변경 test
	@Test
	@Ignore
	public void do_update() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/cart/do_update.do")
				.param("pNum", "366") //param연결
				.param("cartCnt", "3")
				.param("regId", "cart_130");
		
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
	
	//cart 담기 test
	@Test
	@Ignore
	public void do_save() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/cart/do_save.do")
				.param("pNum", "366") //param연결
				.param("cartCnt", "1")
				.param("regId", "cart_130");
		
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
	
	//삭제 test
	@Test
	@Ignore
	public void do_delete() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/cart/do_delete.do")
				.param("regId", "cart_130"); //param연결
		
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
