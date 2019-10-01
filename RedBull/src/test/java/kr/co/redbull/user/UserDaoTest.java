package kr.co.redbull.user;

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
				new User("j01_145", "password", "이상무145_01", "1900/01/01", "010-1111-1111", 12345, "주소",
						 "상세주소", 9999), 
				new User("j02_145", "password", "이상무145_02", "1900/01/01", "010-1111-1111", 12345, "주소",
						 "상세주소", 9999), 
				new User("j03_145", "password", "이상무145_03", "1900/01/01", "010-1111-1111", 12345, "주소",
						 "상세주소", 9999)	
		);
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		
		User user = users.get(0);
		
		LOG.debug("=========================");
		LOG.debug("=01. 기존 데이터를 삭제=");
		LOG.debug("=========================");
		
		for(User vo : users) {
			
			userDaoImpl.do_delete(vo);
		}
		
		LOG.debug("=========================");
		LOG.debug("=02. 데이터를 입력=");
		LOG.debug("=========================");
		
		for(User vo : users) {
			
			userDaoImpl.do_save(vo);
		}
		
		LOG.debug("=========================");
		LOG.debug("=03. 단건 조회=");
		LOG.debug("=========================");
		
		User userOne = (User) userDaoImpl.get_selectOne(user);
		
		LOG.debug("=============================");
		LOG.debug("=outUser=" + userOne);
		LOG.debug("=============================");
		
		assertThat(userOne.getRid(), is(users.get(0).getRid()));
		assertThat(userOne.getPasswd(), is(users.get(0).getPasswd()));
		
	}//--addAndGet
	
	
	/**회원정보 수정*/
	@Test
	@Ignore
	public void do_update() {
				
		LOG.debug("=========================");
		LOG.debug("=01. 기존 데이터를 삭제=");
		LOG.debug("=========================");
		
		for(User vo : users) {
			
			userDaoImpl.do_delete(vo); 
		}
		
		LOG.debug("=========================");
		LOG.debug("=02. 데이터를 입력=");
		LOG.debug("=========================");
		
		for(User vo : users) {
			
			int flag = userDaoImpl.do_save(vo);
			assertThat(1, is(flag)); // 데이터 등록 성공 여부 확인
		}
		
		LOG.debug("=========================");
		LOG.debug("=03. 입력한 데이터를 수정=");
		LOG.debug("=03.1 user01을 수정=");
		LOG.debug("=03.2 user01 업데이트 수행=");
		LOG.debug("=========================");
		
		User user01 = users.get(0);  // 등록한 첫 번째 데이터를 가져옴
		
		// 값을 변경하고 
		user01.setUname("이상무145_01_u");
		user01.setPasswd("password_u");
		user01.setBirth("1900-uu-uu");
		user01.setPhone("010-uuuu-uuuu");
		user01.setPostnum(6789);
		user01.setAddress("주소_u");
		user01.setDetadd("상세주소_u");
		user01.setLvl(20);
		user01.setUpoint(1);
		user01.setInum(1);
		
		// 변경한 값으로 업데이트
		int flag = userDaoImpl.do_update(user01);
		assertThat(1, is(flag)); // 업데이트 성공 여부 확인
		
		LOG.debug("=========================");
		LOG.debug("=04. 비교=");
		LOG.debug("=========================");
		
		User vsUser = (User) userDaoImpl.get_selectOne(user01); // 업데이트한 객체의 아이디 값으로 데이터를 조회해옴
		
		// 등독한  
		assertThat(user01.getRid(), is(vsUser.getRid())); // 업데이트한 객체와 조회된 데이터의 일치 여부 확인(수정이 됐나 확인)
		assertThat(user01.getPasswd(), is(vsUser.getPasswd()));
		assertThat(user01.getUname(), is(vsUser.getUname()));
		assertThat(user01.getBirth(), is(vsUser.getBirth()));
		assertThat(user01.getPhone(), is(vsUser.getPhone()));
		
		assertThat(user01.getPostnum(), is(vsUser.getPostnum()));
		assertThat(user01.getAddress(), is(vsUser.getAddress()));
		assertThat(user01.getDetadd(), is(vsUser.getDetadd()));
		assertThat(user01.getLvl(), is(vsUser.getLvl()));
		assertThat(user01.getUpoint(), is(vsUser.getUpoint()));
		
		assertThat(user01.getInum(), is(vsUser.getInum()));
		
	}//--do_update
	
	/**회원가입*/
	@Test
	@Ignore
	public void do_save() {
		
		User user = users.get(0);
		
		userDaoImpl.do_delete(user);
		userDaoImpl.do_save(user);
	}
	
	/**회원탈퇴*/
	@Test
//	@Ignore
	public void do_delete() {
		
		User user = users.get(0);
		
		userDaoImpl.do_delete(user);
	}
	
	/**비밀번호 찾기*/
	@Test
	@Ignore
	public void get_selectOne() {
		
		User user = users.get(0);
		
		userDaoImpl.get_selectOne(user);
		
		LOG.debug("=========================");
		LOG.debug("=get_selectOne user=" + user);
		LOG.debug("=========================");
		
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
