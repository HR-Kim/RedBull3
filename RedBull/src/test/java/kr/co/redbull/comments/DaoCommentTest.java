package kr.co.redbull.comments;

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

import kr.co.redbull.board.service.Board;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.comment.service.Comment;
import kr.co.redbull.comment.service.impl.CommentDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //메소드 이름 알파벳 순으로 수행
public class DaoCommentTest {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private CommentDaoImpl commentDaoImpl;
	
	List<Comment> list;
	
	@Before
	public void setUp() {
//Comment(int cLevel, int cNum, int tNum, int refNum, String contents, int hcNum, String regId, String regDt,String modId, String modDt)
		list = Arrays.asList(
				new Comment(1,1,20,1,"내용1",0,"admin","등록날짜","admin","수정날짜"),
				new Comment(1,2,20,1,"내용2",0,"admin","등록날짜","admin","수정날짜"),
				new Comment(2,3,20,1,"내용3",1,"admin","등록날짜","admin","수정날짜"),
				new Comment(2,4,20,1,"내용4",1,"admin","등록날짜","admin","수정날짜"),
				new Comment(3,5,20,1,"내용5",2,"admin","등록날짜","admin","수정날짜")				
				);			
	}
	
	@Test
	public void getBean() {
		LOG.debug("===========================");
		LOG.debug("context:"+context);
		LOG.debug("commentsDaoImpl:"+commentDaoImpl);
		LOG.debug("===========================");
		
		assertThat(context, is(notNullValue()));
		assertThat(commentDaoImpl, is(notNullValue()));
	}
	
	@Test
	@Ignore
	public void get_retrieve() {
		//-----------------------------------------------
		//  0. 기존 데이터 삭제
		//-----------------------------------------------
		Search search = new Search();
		search.setSearchWord("20");
		List<Comment> commentIdList = (List<Comment>) commentDaoImpl.get_commentIdList(search);
		assertThat(5, is(commentIdList.size()));

		LOG.debug("===========================");
		LOG.debug("commentIdList:"+commentIdList);
		LOG.debug("===========================");
		
		for(Comment vo : commentIdList) {
			int flag = commentDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}	
		
		//-----------------------------------------------
		//  1. 데이터 추가
		//-----------------------------------------------
		for(Comment vo : list) {
			int flag = commentDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}	
		
		//-----------------------------------------------
		//  2. 조회
		//-----------------------------------------------
		Search searchCondition = new Search();
		searchCondition.setPageSize(10);
		searchCondition.setPageNum(1);
		searchCondition.setSearchDiv("20");
		searchCondition.setSearchWord("1");
		
		List<Comment> commentList = (List<Comment>)commentDaoImpl.get_retrieve(searchCondition);
		
		LOG.debug("===========================");
		LOG.debug("commentList:"+commentList);
		LOG.debug("===========================");
		
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		//--------------------------------------
		// 1. 기존 데이터 삭제
		//--------------------------------------
		
		Search search = new Search();
		search.setSearchWord("20");
		List<Comment> commentIdList = (List<Comment>) commentDaoImpl.get_commentIdList(search);
		assertThat(5, is(commentIdList.size()));

		LOG.debug("===========================");
		LOG.debug("commentIdList:"+commentIdList);
		LOG.debug("===========================");
		
		for(Comment vo : commentIdList) {
			int flag = commentDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 2. 새로 등록
		//--------------------------------------
		for(Comment vo : list) {
			int flag = commentDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 3. 단건조회
		//--------------------------------------
		for(int i=0; i<list.size(); i++) {
			checkData(commentIdList.get(i), list.get(i));
		}
	}
	
	@Test
	@Ignore
	public void do_update() {
		//--------------------------------------
		// 1. 기존 데이터 삭제
		//--------------------------------------
		
		Search search = new Search();
		search.setSearchWord("20");
		List<Comment> idList = (List<Comment>)commentDaoImpl.get_commentIdList(search);		
		
		LOG.debug("===========================");
		LOG.debug("idList:"+idList);
		LOG.debug("===========================");
		
		for(Comment vo : idList) {
			int flag = commentDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 2. 새 데이터 등록
		//--------------------------------------
		
		for(int i=0; i<list.size(); i++) {
			int flag = commentDaoImpl.do_save(list.get(i));
			assertThat(1, is(flag));
		}
		
		//--------------------------------------
		// 3. 등록한 데이터리스트 가져오기
		//--------------------------------------
		
		List<Comment> addedDataList = (List<Comment>)commentDaoImpl.get_commentIdList(search);
		assertThat(5, is(addedDataList.size()));
		
		//--------------------------------------
		// 4. 데이터 한 건 수정
		//--------------------------------------
		
		Comment updated = addedDataList.get(0);
		updated.setContents("수정");
		updated.setModId("수정");
		
		//--------------------------------------
		// 5. 수정된 데이터 등록
		//--------------------------------------
		
		int flag = commentDaoImpl.do_update(updated);
		assertThat(1, is(flag));
		
		//--------------------------------------
		// 6. 수정된 데이터 조회
		//--------------------------------------
		
		addedDataList = (List<Comment>)commentDaoImpl.get_commentIdList(search);
		
		//--------------------------------------
		// 7. 비교
		//--------------------------------------
		
		this.checkData(updated, addedDataList.get(0));
	
	}
	
	private void checkData(Comment org, Comment vs) {
		assertThat(org.getContents(), is(vs.getContents()));
		assertThat(org.getModId(), is(vs.getModId()));
	}
	
	@Test
	@Ignore
	public void get_idList() {
		Search search = new Search();
		search.setSearchWord("20");
		List<Comment> list = (List<Comment>)commentDaoImpl.get_commentIdList(search);
		for(Comment comment : list) {
			LOG.debug(comment.toString());
		}		
	}
	
	@Test
	@Ignore
	public void do_save() {
		
		for(Comment comment : list) {
			int flag = commentDaoImpl.do_save(comment);
			assertThat(1, is(flag));
		}

	}
	
	@Test
	@Ignore
	public void do_delete() {
		
		Search search = new Search();
		search.setSearchWord("20");
		List<Comment> list = (List<Comment>)commentDaoImpl.get_commentIdList(search);		
		
		Comment comment = new Comment();
		comment.setcNum(list.get(0).getcNum());
		
		int flag = commentDaoImpl.do_delete(comment);
		
		assertThat(1, is(flag));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
