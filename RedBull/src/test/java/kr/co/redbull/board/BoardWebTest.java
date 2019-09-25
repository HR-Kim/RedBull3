package kr.co.redbull.board;

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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
					,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardWebTest {

	private Logger LOG = LoggerFactory.getLogger(BoardWebTest.class);
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	BoardDaoImpl dao;
	
	List<Board> list;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("^^^^^^^^0  setUp^^^^^^^^^^^^");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		list = Arrays.asList(
				new Board(0, "J01제목_128", "J01내용_128", 0, 1, "카테고리01", "admin", "noDate", "admin", "noDate"),
				new Board(0, "J02제목_128", "J02내용_128", 0, 1, "카테고리02", "admin", "noDate", "admin", "noDate"),
				new Board(0, "J03제목_128", "J03내용_128", 0, 1, "카테고리03", "admin", "noDate", "admin", "noDate"),
				new Board(0, "J04제목_128", "J04내용_128", 0, 1, "카테고리04", "admin", "noDate", "admin", "noDate"),
				new Board(0, "J05제목_128", "J05내용_128", 0, 1, "카테고리05", "admin", "noDate", "admin", "noDate")
			);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		LOG.debug("=============================");
		LOG.debug("context "+context);
		LOG.debug("mockMvc "+mockMvc);
		LOG.debug("dao "+dao);
		LOG.debug("=============================");
		
	}
	
	@Test
	//@Ignore
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
	@Ignore
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
