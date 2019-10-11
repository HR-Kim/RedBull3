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
import org.springframework.web.context.WebApplicationContext;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cart.service.impl.CartDaoImpl;
import kr.co.redbull.cmn.Search;
import oracle.sql.ARRAY;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml" }) //배열
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Test N mAME_ASCENDING으로 수행
public class DaoCartTest {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private CartDaoImpl cartDaoImpl;
	
	List<Cart> list;
	
	@Before
	public void setUp() {
		LOG.debug("setUp()");
		list = Arrays.asList(
				new Cart(1,47,1,"cart_130"),
				new Cart(2,47,1,"cart_140")
				);
	}
	
	//할인가
	@Test
	@Ignore
	public void sale() {
		//=======================================
		// 총 상품금액 조회
		//=======================================
		cartDaoImpl.sale(list.get(0));
	}
	
	//배송비
	@Test
	@Ignore
	public void delivery() {
		//=======================================
		// 총 상품금액 조회
		//=======================================
		cartDaoImpl.delivery(list.get(0));
	}
	
	//전체 금액
	@Test
	@Ignore
	public void total() {
		//=======================================
		// 총 상품금액 조회
		//=======================================
		cartDaoImpl.total(list.get(0));
	}
	
	
	@Test
	@Ignore
	public void do_update() {
		//=======================================
		//0. 기존 date삭제
		//=======================================
		for(Cart vo: list) {
			cartDaoImpl.do_delete(vo);
		}
		
		//=======================================
		//1. Date등록
		//=======================================
		for(Cart vo:list) {
			int flag = cartDaoImpl.do_save(vo);
			//assertThat(1, is(flag));
		}
		
		//=======================================
		//2. 등록 Date조회
		//=======================================
		List<Cart> addlistData = (List<Cart>) cartDaoImpl.get_retrieve(list.get(0));
		
		//--------------------------------------
		//3. 데이터 한 건 수정
		//--------------------------------------
		Cart updated = addlistData.get(0);
		updated.setCartCnt(2);
		
		//--------------------------------------
		//4. 수정된 데이터 등록
		//--------------------------------------
		
		int flag = cartDaoImpl.do_update(updated);
		assertThat(1, is(flag));
		
		//--------------------------------------
		//5. 수정된 데이터 조회
		//--------------------------------------
		addlistData = (List<Cart>) cartDaoImpl.get_retrieve(list.get(0));
		
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		//=======================================
		//0. 기존 date삭제
		//=======================================
		for(Cart vo: list) {
			cartDaoImpl.do_delete(vo);
		}
		
		//=======================================
		//1. Date등록
		//=======================================
		for(Cart vo:list) {
			int flag = cartDaoImpl.do_save(vo);
			//assertThat(1, is(flag));
		}
		
		//=======================================
		//2. 등록 Date조회
		//=======================================
		
		List<Cart> addlistData = (List<Cart>) cartDaoImpl.get_retrieve(list.get(0));
		//assertThat(1, is(addlistData.size()));
		
		//=======================================
		//3. 비교:boardId는 자동증가로 비교할 수 없음(제목, 내용, 둥록자)
		//=======================================
//		for(int i=0; i<list.size();i++) {
//			checkData(addlistData.get(i), list.get(i));
//		}
		
	}
	private void checkData(Cart org, Cart vs) {
		LOG.debug("checkData()");
		assertThat(org.getCartNum(), is(vs.getCartNum()));
		assertThat(org.getoNum(), is(vs.getoNum()));
		assertThat(org.getCartCnt(), is(vs.getCartCnt()));
		assertThat(org.getRegId(), is(vs.getRegId()));
	}
	
	
	@Test
	@Ignore
	public void save() { 
		LOG.debug("==========================");
		LOG.debug("save()");
		LOG.debug("===========================");
		int count = cartDaoImpl.countCart(list.get(0));
		if(count == 0) {
			//상품 없을경우 추가
			cartDaoImpl.do_save(list.get(0));
		}else {
			//상품 있을경우 수량 증가
			cartDaoImpl.updateCart(list.get(0));
		}
		
//		for(Cart vo: list) {
//			int flag = cartDaoImpl.do_save(vo);
//			assertThat(1, is(flag));
//		}
	}
	
	@Test
	public void do_delete() {
		cartDaoImpl.do_delete(list.get(0));
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
