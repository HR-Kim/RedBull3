package kr.co.redbull.product;

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
import kr.co.redbull.product.service.Product;
import kr.co.redbull.product.service.impl.ProductDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//알파벳 순으로 테스트 수행
public class DaoProductTest {
	
	private Logger LOG = LoggerFactory.getLogger(DaoProductTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ProductDaoImpl productDaoImpl;
	
	List<Product> list;
	
	//public Board(String boardId, String title, int readCnt, String contents, String regId, String regDt) {
	@Before
	public void setUp() {
		list = Arrays.asList(
				new Product("noBoardId","J01상품명_125",10000,"J01상세정보_125",0.1,1000,"10","J01등록자_125","noDate","noUser","noDate"),
				new Product("noBoardId","J02상품명_125",20000,"J02상세정보_125",0.2,2000,"10","J01등록자_125","noDate","noUser","noDate"),
				new Product("noBoardId","J03상품명_125",30000,"J03상세정보_125",0.3,3000,"20","J01등록자_125","noDate","noUser","noDate"),
				new Product("noBoardId","J04상품명_125",40000,"J04상세정보_125",0.4,4000,"30","J01등록자_125","noDate","noUser","noDate"),
				new Product("noBoardId","J05상품명_125",50000,"J05상세정보_125",0.5,5000,"30","J01등록자_125","noDate","noUser","noDate")
				);
				
				
	}
		
	@After
	public void tearDown() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("99 tearDown()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
	}
	
	/**
	 * 카테고리 순
	 */
	@Test
	@Ignore
	public void get_categoryList() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo:getList) {
			productDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Product vo:list) { 
			int flag = productDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Product> addlistData = (List<Product>) productDaoImpl.get_categoryList(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	/**
	 * 세일순
	 */
	@Test
	@Ignore
	public void get_hotSaleList() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo:getList) {
			productDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Product vo:list) { 
			int flag = productDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Product> addlistData = (List<Product>) productDaoImpl.get_hotSaleList(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	/**
	 * 신상품순
	 */
	@Test
	@Ignore
	public void get_newList() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo:getList) {
			productDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Product vo:list) { 
			int flag = productDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Product> addlistData = (List<Product>) productDaoImpl.get_newList(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	/**
	 * 베스트 랭킹순
	 */
	@Test
	@Ignore
	public void get_rankList() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo:getList) {
			productDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Product vo:list) { 
			int flag = productDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Product> addlistData = (List<Product>) productDaoImpl.get_rankList(search);
//		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void get_retrieve() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("125");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo:getList) {
			productDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Product vo:list) { 
			int flag = productDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		search.setSearchDiv("10");
		search.setPageSize(10);
		search.setPageNum(1);
		List<Product> addlistData = (List<Product>) productDaoImpl.get_retrieve(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void do_update() {
		//01. 기존 데이터 삭제
		Search search = new Search();
		search.setSearchWord("125");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo : getList) {
			int flag = productDaoImpl.do_delete(vo);			
		}		
		//02. 데이터 추가
		for(Product vo : list) {
			int flag = productDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//02.01.데이터 조회
		List<Product> addListData = (List<Product>) productDaoImpl.get_pnameList(search);
		assertThat(5, is(addListData.size()));
		
		//03. 데이터 수정
		Product changeData = addListData.get(0);
		changeData.setpName(changeData.getpName()+"_U");
		changeData.setDetail(changeData.getDetail()+"_U");
		changeData.setModId(changeData.getRegId()+"_U");
		
		//03.01. 수정 데이터 등록
		int flag = productDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
		
		//04. 등록 데이터 조회
		addListData = (List<Product>) productDaoImpl.get_pnameList(search);
		
		//05. 비교
		checkData(changeData,addListData.get(0));
		
	}
	
	@Test
	public void addAndGet() {
		//0.데이터 삭제
		Search search = new Search();
		search.setSearchWord("");
		List<Product> getList = (List<Product>) productDaoImpl.get_pnameList(search);
		for(Product vo : getList) {
			int flag = productDaoImpl.do_delete(vo);			
		}
		//1.데이터 등록
		for(Product vo : list) {
			int flag = productDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//2.데이터 조회
		List<Product> getList2 = (List<Product>) productDaoImpl.get_pnameList(search);
		assertThat(5, is(getList2.size()));
		
		//3.데이터 비교:boardId는 자동 증가이므로 비교 할 수 없음.(제목,내용,등록자)
		for(int i=0; i<getList2.size(); i++ ) {
			checkData(getList2.get(i),list.get(i));
		}		
	}
	
	private void checkData(Product org,Product vs) {
		assertThat(org.getpName(), is(vs.getpName()));
		assertThat(org.getDetail(), is(vs.getDetail()));
		assertThat(org.getModId(), is(vs.getModId()));		
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("====================================================");
		LOG.debug("=context="+context);
		LOG.debug("=boardDaoImpl="+productDaoImpl);
		LOG.debug("====================================================");
		assertThat(context, is(notNullValue()));
		assertThat(productDaoImpl, is(notNullValue()));
	}	
}
