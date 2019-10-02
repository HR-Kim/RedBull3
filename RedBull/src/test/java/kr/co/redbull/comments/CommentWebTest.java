package kr.co.redbull.comments;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import kr.co.redbull.board.service.Board;
import kr.co.redbull.board.service.impl.BoardDaoImpl;
import kr.co.redbull.comment.service.Comment;
import kr.co.redbull.comment.service.impl.CommentDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
					,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CommentWebTest {

	private Logger LOG = LoggerFactory.getLogger(CommentWebTest.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	CommentDaoImpl dao;
	
	List<Comment> list;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("^^^^^^^^0  setUp^^^^^^^^^^^^");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		list = Arrays.asList(
				new Comment(1,1,20,1,"내용1",0,"admin","등록날짜","admin","수정날짜"),
				new Comment(1,2,20,1,"내용2",0,"admin","등록날짜","admin","수정날짜"),
				new Comment(2,3,20,1,"내용3",1,"admin","등록날짜","admin","수정날짜"),
				new Comment(2,4,20,1,"내용4",1,"admin","등록날짜","admin","수정날짜"),
				new Comment(3,5,20,1,"내용5",2,"admin","등록날짜","admin","수정날짜")		
			);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		LOG.debug("=============================");
		LOG.debug("context "+context);
		LOG.debug("mockMvc "+mockMvc);
		LOG.debug("dao "+dao);
		LOG.debug("=============================");
		
	}
	
	@Test
	@Ignore
	public void get_retrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/board/get_retrieve.do")
				.param("pageSize", "10")
				.param("pageNum", "1")
				.param("searchDiv", "10")
				.param("searchWord", "제목")
				;

			ResultActions resultActions = mockMvc.perform(createMessage)
					.andExpect(status().isOk())
					;
			
			String result = resultActions.andDo(print())
					.andReturn().getResponse().getContentAsString();
			
			LOG.debug("=============================");
			LOG.debug("result"+result);
			LOG.debug("=============================");
	}
	
	@Test
	@Ignore
	public void get_selectOne() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				   MockMvcRequestBuilders.get("/board/get_selectOne.do")
				.param("bNum", "204");
		
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
	@Ignore
	public void do_update() throws Exception{
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/board/do_update.do")
			.param("bNum", "204")
			.param("title", "update")
			.param("contents", "update")
			.param("tNum", "1")
			.param("category", "update")
			.param("modId", "update")
			;

		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")))
				;
		
		String result = resultActions.andDo(print())
				.andReturn().getResponse().getContentAsString();
		
		LOG.debug("=============================");
		LOG.debug("result"+result);
		LOG.debug("=============================");
	}
	
	@Test
	@Ignore
	public void do_save() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/board/do_save.do")
				.param("title", "제목123")
				.param("contents", "내용내용")
				.param("tNum", "1")
				.param("category", "카테고리02")
				.param("regId", "admin")
				;
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")))
				;
		
		String result = resultActions.andDo(print())
				.andReturn().getResponse().getContentAsString();
		
		LOG.debug("=============================");
		LOG.debug("result"+result);
		LOG.debug("=============================");
	}
	
	@Test
	//@Ignore
	public void do_delete() throws Exception {
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.get("/board/do_delete.do")
				.param("bNum", "187");
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.msgId", is("1")))
				;
		
		String result = resultActions.andDo(print())
				.andReturn().getResponse().getContentAsString();
		
		LOG.debug("=============================");
		LOG.debug("result"+result);
		LOG.debug("=============================");
	}
}
