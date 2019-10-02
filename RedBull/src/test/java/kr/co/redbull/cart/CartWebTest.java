package kr.co.redbull.cart;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
	private CartDaoImpl cartDaoImpl;
	
	List<Cart> list;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUp()");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^");
		list = Arrays.asList(
				new Cart(1,336,1,"J01등록자_125")
				//new Cart(2,187,1,"J01등록자_126"),
				//new Cart(3,188,1,"J01등록자_127")
				);
		
		//mockMvc = MockB
	}
	
	
	@Test
	public void getBean() {
		LOG.debug("getBean()");
		LOG.debug("==========================");
		LOG.debug("=context="+context);
		LOG.debug("cartDaoImpl=" + cartDaoImpl);
		LOG.debug("==========================");
		assertThat(context, is(notNullValue()));
		assertThat(cartDaoImpl, is(notNullValue()));
		
	}
	
	@After
	public void tearDown() {
		LOG.debug("tearDown()");
	}

}
