package kr.co.redbull.image;

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
import kr.co.redbull.image.service.Image;
import kr.co.redbull.image.service.impl.ImageDaoImpl;
import kr.co.redbull.opt.service.Opt;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//알파벳 순으로 테스트 수행
public class DaoImageTest {
	
	private Logger LOG = LoggerFactory.getLogger(DaoImageTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ImageDaoImpl imageDaoImpl;
	
	List<Image> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new Image("noinum","56","orgFileNm1","saveFileNm1",1,"ext"),
				new Image("noinum","57","orgFileNm2","saveFileNm2",2,"ext"),
				new Image("noinum","58","orgFileNm3","saveFileNm3",3,"ext"),
				new Image("noinum","59","orgFileNm4","saveFileNm4",4,"ext"),
				new Image("noinum","60","orgFileNm5","saveFileNm5",5,"ext")
				);
				
				
	}
		
	@After
	public void tearDown() {
		LOG.debug("^^^^^^^^^^^^^^^^^^");
		LOG.debug("99 tearDown()");
		LOG.debug("^^^^^^^^^^^^^^^^^^");
	}
	
	//category_all()에서 출력
	@Test
	public void get_category() {
		Search search=new Search();
		search.setSearchWord("");
		List<Image> getList = (List<Image>) imageDaoImpl.get_refnumList(search);
	}
	
	@Test
	@Ignore
	public void get_retrieve() {
		LOG.debug("======================================");
		LOG.debug("=01. 기존 데이터 삭제=");
		LOG.debug("======================================");	
		Search search=new Search();
		search.setSearchWord("");
		List<Image> getList = (List<Image>) imageDaoImpl.get_refnumList(search);
		for(Image vo:getList) {
			imageDaoImpl.do_delete(vo);
		}	
		
		LOG.debug("======================================");
		LOG.debug("=02. 데이터 추가=");
		LOG.debug("======================================");	
		for(Image vo:list) { 
			int flag = imageDaoImpl.do_save(vo);
			assertThat(1, is(flag));
		}		
		
		//=====================================
		//2.01 등록Data조회
		//=====================================
		List<Opt> addlistData = (List<Opt>) imageDaoImpl.get_refnumList(search);
		assertThat(5, is(addlistData.size()));		
	}
	
	@Test
	@Ignore
	public void do_update() {
		//01. 기존 데이터 삭제
		Search search = new Search();
		search.setSearchWord("");
		List<Image> getList = (List<Image>) imageDaoImpl.get_refnumList(search);
		for(Image vo : getList) {
			int flag = imageDaoImpl.do_delete(vo);			
		}		
		//02. 데이터 추가
		for(Image vo : list) {
			int flag = imageDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//02.01.데이터 조회
		List<Image> addListData = (List<Image>) imageDaoImpl.get_refnumList(search);
		assertThat(5, is(addListData.size()));
		
		//03. 데이터 수정
		Image changeData = addListData.get(0);
		changeData.setSaveFileNm(changeData.getSaveFileNm()+"_U");
		changeData.setOrgFileNm(changeData.getOrgFileNm()+"_U");
		changeData.setExtNm(changeData.getExtNm()+"U");
		
		
		//03.01. 수정 데이터 등록
		int flag = imageDaoImpl.do_update(changeData);
		assertThat(1, is(flag));
		
		//04. 등록 데이터 조회
		addListData = (List<Image>) imageDaoImpl.get_refnumList(search);
		
		//05. 비교
		checkData(changeData,addListData.get(0));
		
	}
	
	@Test
	@Ignore
	public void addAndGet() {
		//0.데이터 삭제
		Search search = new Search();
		search.setSearchWord("");
		List<Image> getList = (List<Image>) imageDaoImpl.get_refnumList(search);
		for(Image vo : getList) {
			int flag = imageDaoImpl.do_delete(vo);			
		}
		//1.데이터 등록
		for(Image vo : list) {
			int flag = imageDaoImpl.do_save(vo);
			assertThat(flag,is(1));
		}
		//2.데이터 조회
		List<Image> getList2 = (List<Image>) imageDaoImpl.get_refnumList(search);
		assertThat(5, is(getList2.size()));
		
		//3.데이터 비교:boardId는 자동 증가이므로 비교 할 수 없음.(제목,내용,등록자)
		for(int i=0; i<getList2.size(); i++ ) {
			checkData(getList2.get(i),list.get(i));
		}		
	}
	
	private void checkData(Image org,Image vs) {
		assertThat(org.getSaveFileNm(), is(vs.getSaveFileNm()));	
		assertThat(org.getOrgFileNm(), is(vs.getOrgFileNm()));	
		assertThat(org.getExtNm(), is(vs.getExtNm()));	
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("====================================================");
		LOG.debug("=context="+context);
		LOG.debug("=boardDaoImpl="+imageDaoImpl);
		LOG.debug("====================================================");
		assertThat(context, is(notNullValue()));
		assertThat(imageDaoImpl, is(notNullValue()));
	}	
}
