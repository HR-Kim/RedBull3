package kr.co.redbull.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import kr.co.redbull.user.service.Level;
import kr.co.redbull.user.service.User;
import kr.co.redbull.user.service.impl.UserDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserWebTest {
	
	private final Logger LOG = LoggerFactory.getLogger(UserWebTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	List<User> users;
	
	// 웹 애플리케이션을 애플리케이션 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		
		LOG.debug("=========================");
		LOG.debug("0. setUp()");
		LOG.debug("=========================");
		
		// public User(String rid, String passwd, String uname, String birth, String phone, int postnum, String address,
		//			   String detadd, int lvl, int upoint, int inum, String regdt)
		users = Arrays.asList(
				new User("j01_145", "password", "이상무145_01", "1900/01/01", "010-1111-1111", 12345, "주소",
						 "상세주소", Level.BASIC, 0, 9999, "2019/10/01"), 
				new User("j02_145", "password", "이상무145_02", "1900/01/01", "010-1111-1111", 12345, "주소",
						 "상세주소", Level.SILVER, 0, 9999, "2019/10/01"),  
				new User("j03_145", "password", "이상무145_03", "1900/01/01", "010-1111-1111", 12345, "주소",
						 "상세주소", Level.GOLD, 0, 9999, "2019/10/01")
		);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		LOG.debug("=========================");
		LOG.debug("context: " + context);
		LOG.debug("mockMvc: " + mockMvc);
		LOG.debug("userDaoImpl: " + userDaoImpl);
		LOG.debug("=========================");
	}
	
	@Test
	@Ignore
	public void doUserView() throws Exception {
		
		// Request call : url, param
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/user/do_user_view.do");
		
		mockMvc.perform(createMessage)
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("/user/join.jsp"))
		.andDo(print());
	}
	
	@Test
	@Ignore
	public void excelDown() throws Exception {
		
		// 목록 조회 메소드를 호출하고 
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/user/do_exceldown.do")
				.param("rid", "excel") // 파라미터 넘기기
				.param("passwd", "excel")
				.param("uname", "excel")
				.param("birth", "excel")
				.param("phone", "excel")
				.param("postnum", 5555+"")
				.param("address", "excel")
				.param("detadd", "excel")
				.param("lvl", 10+"")
				.param("upoint", 55+"")
				.param("inum",5555+"")
				.param("ext", "xlsx");
		
		
		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(status().isOk());
				
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=========================");
		LOG.debug("result: " + result);
		LOG.debug("=========================");
		
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
	public void do_update() throws Exception {

		LOG.debug("=========================");
		LOG.debug("=01. 기존 데이터를 삭제=");
		LOG.debug("=========================");
		
		for(User user : users) {
			
			do_delete(user);
		}
		
		LOG.debug("=========================");
		LOG.debug("=02. 데이터를 입력=");
		LOG.debug("=========================");
		
		userDaoImpl.do_save(users.get(0));
		
		User user01 = users.get(0);
		user01.setRid("j01_145");
		user01.setPasswd("1234_u");
		user01.setUname("업데이트");
		user01.setBirth("uuuu-uu-uu");
		user01.setPhone("uuu-uuuu-uuuu");
		user01.setPostnum(1212);
		user01.setAddress("업데이트");
		user01.setDetadd("업데이트");
		user01.setLvl(Level.GOLD);
		user01.setUpoint(1212);
		user01.setInum(1212);
		
		LOG.debug("=========================");
		LOG.debug("=03. 입력한 데이터를 수정=");
		LOG.debug("=03.1 user01을 수정=");
		LOG.debug("=03.2 user01 업데이트 수행=");
		LOG.debug("=========================");
		
		do_update(user01);
		
		LOG.debug("=========================");
		LOG.debug("=04. 수정한 데이터와 입력한 데이터를 비교=");
		LOG.debug("=========================");
		
		User vsUser = get_selectOne(user01);
		checkUser(vsUser, user01);
		
	}
	
	public void do_update(User user) throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/user/do_update.do")
				.param("rid", user.getRid()) // 파라미터 넘기기
				.param("passwd", user.getPasswd())
				.param("uname", user.getUname())
				.param("birth", user.getBirth())
				.param("phone", user.getPhone())
				.param("postnum", user.getPostnum()+"")
				.param("address", user.getAddress())
				.param("detadd", user.getDetadd())
				.param("lvl", user.getLvl()+"")
				.param("upoint", user.getUpoint()+"")
				.param("inum", user.getInum()+"");
		
		ResultActions resultActions =mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
		
		String result =	resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();

		LOG.debug("===============================");
		LOG.debug("=result="+result);
		LOG.debug("===============================");		
		
	}
	
	public void checkUser(User vsUser, User user) {
		
		assertThat(vsUser.getRid(), is(user.getRid()));
		assertThat(vsUser.getPasswd(), is(user.getPasswd()));
		assertThat(vsUser.getUname(), is(user.getUname()));
		assertThat(vsUser.getBirth(), is(user.getBirth()));
		
		assertThat(vsUser.getPhone(), is(user.getPhone()));
		assertThat(vsUser.getPostnum(), is(user.getPostnum()));
		assertThat(vsUser.getAddress(), is(user.getAddress()));
		assertThat(vsUser.getDetadd(), is(user.getDetadd()));
		
		assertThat(vsUser.getLvl(), is(user.getLvl()));
		assertThat(vsUser.getUpoint(), is(user.getUpoint()));
		assertThat(vsUser.getInum(), is(user.getInum()));
		
	}//--checkUser
	
	/**회원가입*/
	@Test
	@Ignore
	public void do_save() throws Exception {
		
		LOG.debug("======================================");
		LOG.debug("=01. 삭제=");
		LOG.debug("======================================");
		
		for(User user : users) {
			
			do_delete(user);
		}
		
		LOG.debug("======================================");
		LOG.debug("=02. 단건등록=");
		LOG.debug("======================================");
		
		for(User user : users) {
			
			do_save(user);
		}
		
	}
	
	private void do_save(User user) throws Exception {
		
		// 등록 메소드를 호출하고 
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/user/do_save.do")
				.param("rid", user.getRid()) // 파라미터 넘기기
				.param("passwd", user.getPasswd())
				.param("uname", user.getUname())
				.param("birth", user.getBirth())
				.param("phone", user.getPhone())
				.param("postnum", user.getPostnum()+"")
				.param("address", user.getAddress())
				.param("detadd", user.getDetadd())
				.param("lvl", user.getLvl()+"")
				.param("upoint", user.getUpoint()+"")
				.param("inum", user.getInum()+"");
		
		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
		
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=========================");
		LOG.debug("result: " + result);
		LOG.debug("=========================");
		
	}
	
	/**회원탈퇴*/
	@Test
	@Ignore
	public void do_delete() throws Exception {
		
		// 삭제 메소드를 호출하고 
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/user/do_delete.do")
		.param("rid", "j02_145"); // 파라미터 넘기기
		
		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")));
				
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=========================");
		LOG.debug("result: " + result);
		LOG.debug("=========================");
		
	}
	
	private void do_delete(User user) throws Exception {
		
		// 삭제 메소드를 호출하고 
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/user/do_delete.do")
		.param("rid", user.getRid()); // 파라미터 넘기기
		
		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
				
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=========================");
		LOG.debug("result: " + result);
		LOG.debug("=========================");
		
	}
	
	/**단건조회: 비밀번호 찾기*/
	@Test
	@Ignore
	public void get_selectOne() throws Exception {
		
		// 단건조회 메소드를 호출하고 
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/user/get_selectOne.do")
		.param("rid", "j03_145"); // 파라미터 넘기기
		
		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(status().isOk()); // 200번이 떨어졌는지(성공했는지) 확인
				
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=========================");
		LOG.debug("result: " + result);
		LOG.debug("=========================");
		
	}
	
	private User get_selectOne(User user) throws Exception {
		
		// 단건조회 메소드를 호출하고 
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/user/get_selectOne.do")
		.param("rid", user.getRid()); // 파라미터 넘기기
		
		ResultActions resultActions =  mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.rid", is(user.getRid())));
				
		String result = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		
		LOG.debug("=========================");
		LOG.debug("result: " + result);
		LOG.debug("=========================");
		
		// Json -> User
		Gson gson = new Gson();
		User outVO = gson.fromJson(result, User.class);
		
		return outVO;
		
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
