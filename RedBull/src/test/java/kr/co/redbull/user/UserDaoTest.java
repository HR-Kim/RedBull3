package kr.co.redbull.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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

import kr.co.redbull.user.service.Level;
import kr.co.redbull.user.service.User;
import kr.co.redbull.user.service.impl.UserDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // @Test를 NAME_ASCENDING으로 수행
public class UserDaoTest {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	List<User> users;
	
	@Before
	public void setUp() {
		
		// public User(String rid, String passwd, String uname, String birth, String phone, int postnum, String address,
		//			   String detadd, int lvl, int upoint, int inum, String regdt)
		users = Arrays.asList(
				new User("j01_145", "password", "이상무145_01", "1900-01-01", "010-1111-1111", 12345, "주소",
						 "상세주소", 10, 0, 9999, "2019-01-01") 
				
		);
	}
		
	@Test
	public void getBean() {
		
		LOG.debug("===========================");
		LOG.debug("=context=" + context);
		LOG.debug("=userDaoImpl=" + userDaoImpl);
		LOG.debug("===========================");
		
		assertThat(context, is(notNullValue()));
		assertThat(userDaoImpl,  is(notNullValue()));
		
	}
	
	@After
	public void tearDown() {
		
		
	}

}
