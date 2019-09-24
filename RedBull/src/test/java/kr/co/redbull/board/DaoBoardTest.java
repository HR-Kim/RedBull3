package kr.co.redbull.board;

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
import kr.co.redbull.board.service.impl.BoardDaoImpl;
import kr.co.redbull.cmn.Search;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //메소드 이름 알파벳 순으로 수행
public class DaoBoardTest {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private BoardDaoImpl boardDaoImpl;
	
	List<Board> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new Board(1, "제목01", "내용01", 0, 1, "카테고리01", "admin", "2019-09-23", "admin", "2019-09-23"),
				new Board(2, "제목02", "내용02", 0, 1, "카테고리02", "admin", "2019-09-23", "admin", "2019-09-23"),
				new Board(3, "제목03", "내용03", 0, 1, "카테고리03", "admin", "2019-09-23", "admin", "2019-09-23"),
				new Board(4, "제목04", "내용04", 0, 1, "카테고리04", "admin", "2019-09-23", "admin", "2019-09-23"),
				new Board(5, "제목05", "내용05", 0, 1, "카테고리05", "admin", "2019-09-23", "admin", "2019-09-23")
				);
	}
	
	@Test
	public void get_retrieve() {
		//-----------------------------------------------
		//  0. 기존 데이터 삭제
		//-----------------------------------------------
		Search search = new Search();
		search.setSearchWord("제목");
		List<Board> boardIdList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		assertThat(5, is(boardIdList.size()));

		LOG.debug("===========================");
		LOG.debug("boardIdList:"+boardIdList);
		LOG.debug("===========================");
		
		for(Board vo : boardIdList) {
			int flag = boardDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}	
		
		//-----------------------------------------------
		//  1. 데이터 추가
		//-----------------------------------------------
		for(Board vo : list) {
			int flag = boardDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}	
		
		//-----------------------------------------------
		//  2. 조회
		//-----------------------------------------------
		search.setPageSize(10);
		search.setPageNum(1);
		search.setSearchDiv("1");
		search.setSearchWord("제목");
		
		List<Board> boardList = (List<Board>)boardDaoImpl.get_retrieve(search);
		
		LOG.debug("===========================");
		LOG.debug("boardList:"+boardList);
		LOG.debug("===========================");
		
	}
	
	@Test
	@Ignore
	public void do_update() {
		//--------------------------------------
		// 1. 기존 데이터 삭제
		//--------------------------------------
		Search search = new Search();
		search.setSearchWord("제목");
		List<Board> boardIdList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		assertThat(5, is(boardIdList.size()));

		LOG.debug("===========================");
		LOG.debug("boardIdList:"+boardIdList);
		LOG.debug("===========================");
		
		for(Board vo : boardIdList) {
			int flag = boardDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 2. 새 데이터 등록
		//--------------------------------------
		for(Board vo : list) {
			int flag = boardDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 3. 등록한 데이터리스트 가져오기
		//--------------------------------------
		List<Board> addedDataList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		assertThat(5, is(addedDataList.size()));
		
		//--------------------------------------
		// 4. 데이터 한 건 수정
		//--------------------------------------
		
		Board updated = addedDataList.get(0);
		updated.setTitle(updated.getTitle()+"U");
		updated.setContents(updated.getContents()+"U");
		updated.setModId(updated.getModId()+"U");
		
		//--------------------------------------
		// 5. 수정된 데이터 등록
		//--------------------------------------
		
		int flag = boardDaoImpl.do_update(updated);
		assertThat(1, is(1));
		
		//--------------------------------------
		// 6. 수정된 데이터 조회
		//--------------------------------------
		
		addedDataList = (List<Board>)boardDaoImpl.get_boardIdList(search);
		
		//--------------------------------------
		// 6. 수정된 데이터 조회
		//--------------------------------------
		
		this.checkData(updated, addedDataList.get(0));
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		//--------------------------------------
		// 1. 기존 데이터 삭제
		//--------------------------------------
		
		Search search = new Search();
		search.setSearchWord("제목");
		List<Board> boardIdList = (List<Board>) boardDaoImpl.get_boardIdList(search);
		assertThat(5, is(boardIdList.size()));

		LOG.debug("===========================");
		LOG.debug("boardIdList:"+boardIdList);
		LOG.debug("===========================");
		
		for(Board vo : boardIdList) {
			int flag = boardDaoImpl.do_delete(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 2. 새로 등록
		//--------------------------------------
		for(Board vo : list) {
			int flag = boardDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}	
		
		//--------------------------------------
		// 3. 단건조회
		//--------------------------------------
		for(int i=0; i<list.size(); i++) {
			checkData(boardIdList.get(i), list.get(i));
		}
	}
	
	private void checkData(Board org, Board vs) {
		//new Board(1, "제목01", "내용01", 0, 1, "카테고리01", "admin", "2019-09-23", "admin", "2019-09-23"),
		assertThat(org.getTitle(), is(vs.getTitle()));
		assertThat(org.getContents(), is(vs.getContents()));
		assertThat(org.getCategory(), is(vs.getCategory()));
		assertThat(org.getRegId(), is(vs.getRegId()));
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("===========================");
		LOG.debug("context:"+context);
		LOG.debug("boardDaoImpl:"+boardDaoImpl);
		LOG.debug("===========================");
		
		assertThat(context, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
