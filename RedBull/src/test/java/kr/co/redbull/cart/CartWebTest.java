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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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
				new Cart(1,396,1,"cart_130"),
				new Cart(2,396,1,"cart_131")
				);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //mockMvc생성자
		LOG.debug("================================");
		LOG.debug("=context=" + context);
		LOG.debug("=mockMvc=" + mockMvc);
		LOG.debug("=cartDaoImpl=" + cartDaoImpl);
		LOG.debug("================================");
	}
	
	@Test
	@Ignore
	public void addAndGet() throws Exception {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");
		Search search = new Search();
		search.setSearchWord("_130");
		List<Cart> getIdList = (List<Cart>) cartDaoImpl.get_cartIdList(search);
		
		for(Cart vo: getIdList) {
			do_delete(vo);
		}
		
		LOG.debug("=======================================");
		LOG.debug("=02. 데이터 입력=");
		LOG.debug("=======================================");
		for(Cart vo: list) {
			do_save(vo);
		}
		
		LOG.debug("=======================================");
		LOG.debug("=03. 단건조회=");
		LOG.debug("=======================================");
		getIdList = (List<Cart>) cartDaoImpl.get_cartIdList(search);
		
		for(Cart vo: getIdList) {
			Cart vsVO = get_selectOne(vo);
			checkData(vsVO,vo);
		}
	}
	
	private void checkData(Cart org, Cart vs) {
		assertThat(org.getpNum(), is(vs.getpNum()));
		assertThat(org.getCartCnt(), is(vs.getCartCnt()));
		assertThat(org.getRegId(), is(vs.getRegId()));
	}
	
	private Cart get_selectOne(Cart vo) throws Exception {
		//uri, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/cart/get_selectOne.do")
				.param("regId", vo.getRegId());
		
		//url call 결과 return
		MvcResult result = mockMvc.perform(createMessage)
				                     .andExpect(status().isOk())
				                     .andReturn();
		
		ModelAndView modelAndView = result.getModelAndView();
		
		Cart outVO = (Cart) modelAndView.getModel().get("vo");
		
		
		LOG.debug("===============================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("===============================");
		
		return outVO;
		
	}
	
	public void do_save(Cart vo) throws Exception {
		//uri, param, post, get
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/cart/do_save.do")
				.param("pNum", vo.getpNum()+"") //param 연결
				.param("cartCnt", vo.getCartCnt()+"")
				.param("regId", vo.getRegId());
		
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
	
	private void do_delete(Cart cart) throws Exception {
		//uri, param, post, get
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/cart/do_delete.do")
				.param("regId", cart.getRegId()); //param연결
		
		//url 호출 , 결과 return
		ResultActions resultActions = mockMvc.perform(createMessage);
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=====================================");
		LOG.debug("=result=" + result);
		LOG.debug("=====================================");
	}
	
	
	//단건조회 
	@Test
	@Ignore
	public void get_selectOne() throws Exception {
		//uri, param
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/cart/get_selectOne.do")
				.param("regId", "cart_130");
		
		//url 호출 , 결과 return
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().isOk());
		
		//result: return VO 객체로 됨.(결과 출력 안됨)
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
	
		LOG.debug("=====================================");
		LOG.debug("=result=" + result);
		LOG.debug("=====================================");
	}
	
		
	//cart 조회 test
	@Test
	public void get_retrieve() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/cart/get_retrieve.do")
				.param("pageSize", "10")
				.param("pageNum", "1")
				.param("searchDiv", "10")
				.param("searchWord", "cart_130");

	
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
