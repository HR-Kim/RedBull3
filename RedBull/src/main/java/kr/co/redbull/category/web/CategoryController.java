package kr.co.redbull.category.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import kr.co.redbull.category.service.CategoryService;
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.image.service.Image;
import kr.co.redbull.image.service.ImageService;
import kr.co.redbull.opt.service.Opt;
import kr.co.redbull.opt.service.OptService;
import kr.co.redbull.product.service.Product;
import net.sf.json.JSONArray;

@Controller
public class CategoryController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	OptService optService;
	
	private final String VIEW_LIST_NM ="product/category_all";
	
	//베스트상품 조회
	@RequestMapping(value = "product/get_rankList.do", method = RequestMethod.GET)
	public String get_rankList(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		//NUll 처리
		if(search.getPageSize()==0) search.setPageSize(9);
		if(search.getSearchDiv()==null) search.setSearchDiv("10");
		if(search.getPageNum()==0)  search.setPageNum(1);
		if(search.getSearchWord()==null) search.setSearchWord("");
		
		
		model.addAttribute("vo", search);
		
		List<Product> tmpList = (List<Product>)categoryService.get_rankList(search);
		List<Product> outList = new ArrayList<Product>();
		//썸네일 설정 : 안쓰는 detail에 이미지 경로를 넣자
		for(int i=0; i<tmpList.size(); i++) {
			Product getOne = tmpList.get(i);
			String getPnum = getOne.getpNum();
			
			//Pnum으로 검색
			Search tmpSearch = new Search();
			tmpSearch.setSearchWord(getPnum);
			String saveFileNm = "";
			List<Image> tmpImageList = new ArrayList<Image>();
			tmpImageList = (List<Image>) imageService.get_refnumList(tmpSearch);
			//검색 결과가 없다면? : 기본 이미지
			if(tmpImageList.size()<1) {
				saveFileNm = "resources/img/product/noimage.jpg";
			}else {
				//있으면 첫번째 이미지
				saveFileNm = tmpImageList.get(0).getSaveFileNm();
			}
			
			getOne.setDetail(saveFileNm);
			outList.add(getOne);
		}
		
		model.addAttribute("list", outList);
		
		//총건수
		int totalCnt = 0;
		if(null!=outList && outList.size()>0) {
			totalCnt = outList.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		
		return VIEW_LIST_NM;
	}

	//세일상품 조회
	@RequestMapping(value = "product/get_hotSaleList.do", method = RequestMethod.GET)
	public String get_hotSaleList(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		//NUll 처리
		if(search.getPageSize()==0) search.setPageSize(9);
		if(search.getSearchDiv()==null) search.setSearchDiv("10");
		if(search.getPageNum()==0)  search.setPageNum(1);
		if(search.getSearchWord()==null) search.setSearchWord("");
		
		
		model.addAttribute("vo", search);
		
		List<Product> tmpList = (List<Product>)categoryService.get_hotSaleList(search);
		List<Product> outList = new ArrayList<Product>();
		//썸네일 설정 : 안쓰는 detail에 이미지 경로를 넣자
		for(int i=0; i<tmpList.size(); i++) {
			Product getOne = tmpList.get(i);
			String getPnum = getOne.getpNum();
			
			//Pnum으로 검색
			Search tmpSearch = new Search();
			tmpSearch.setSearchWord(getPnum);
			String saveFileNm = "";
			List<Image> tmpImageList = new ArrayList<Image>();
			tmpImageList = (List<Image>) imageService.get_refnumList(tmpSearch);
			//검색 결과가 없다면? : 기본 이미지
			if(tmpImageList.size()<1) {
				saveFileNm = "resources/img/product/noimage.jpg";
			}else {
				//있으면 첫번째 이미지
				saveFileNm = tmpImageList.get(0).getSaveFileNm();
			}
			
			getOne.setDetail(saveFileNm);
			outList.add(getOne);
		}
		
		model.addAttribute("list", outList);
		
		//총건수
		int totalCnt = 0;
		if(null!=outList && outList.size()>0) {
			totalCnt = outList.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		
		return VIEW_LIST_NM;
	}
	
	
	//신상품 조회
	@RequestMapping(value = "product/get_newList.do", method = RequestMethod.GET)
	public String get_newList(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		//NUll 처리
		if(search.getPageSize()==0) search.setPageSize(9);
		if(search.getSearchDiv()==null) search.setSearchDiv("10");
		if(search.getPageNum()==0)  search.setPageNum(1);
		if(search.getSearchWord()==null) search.setSearchWord("");
		
		
		model.addAttribute("vo", search);
		
		List<Product> tmpList = (List<Product>)categoryService.get_newList(search);
		List<Product> outList = new ArrayList<Product>();
		//썸네일 설정 : 안쓰는 detail에 이미지 경로를 넣자
		for(int i=0; i<tmpList.size(); i++) {
			Product getOne = tmpList.get(i);
			String getPnum = getOne.getpNum();
			
			//Pnum으로 검색
			Search tmpSearch = new Search();
			tmpSearch.setSearchWord(getPnum);
			String saveFileNm = "";
			List<Image> tmpImageList = new ArrayList<Image>();
			tmpImageList = (List<Image>) imageService.get_refnumList(tmpSearch);
			//검색 결과가 없다면? : 기본 이미지
			if(tmpImageList.size()<1) {
				saveFileNm = "resources/img/product/noimage.jpg";
			}else {
				//있으면 첫번째 이미지
				saveFileNm = tmpImageList.get(0).getSaveFileNm();
			}
			
			getOne.setDetail(saveFileNm);
			outList.add(getOne);
		}
		
		model.addAttribute("list", outList);
		
		//총건수
		int totalCnt = 0;
		if(null!=outList && outList.size()>0) {
			totalCnt = outList.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		
		return VIEW_LIST_NM;
	}
	
//	@RequestMapping(value = "product/get_selectOne.do", method = RequestMethod.GET)
//	public String get_selectOne(Product product, Model model) {
//		
//		LOG.debug("================================");
//		LOG.debug("Product:"+product);
//		LOG.debug("================================");
//		
//		if(product.getpNum().equals("") || product.getpNum()==null) {
//			throw new IllegalArgumentException("글이 존재하지 않습니다.");
//		}
//		//상품테이블
//		Product productVO = (Product)productService.get_selectOne(product);
//		
//		//Search Word 설정
//		Search searchOne = new Search();
//		searchOne.setSearchWord(productVO.getpNum());
//		
//		//옵션테이블
//		List<Opt> optList = (List<Opt>)optService.get_pNumList(searchOne);
//		
//		//이미지리스트
//		List<Image> imageList = (List<Image>) imageService.get_refnumList(searchOne);
//		
//		//파람 전달
//		model.addAttribute("productVO", productVO);
//		model.addAttribute("optList", optList);
//		model.addAttribute("imageList", imageList);
//		
//		return VIEW_DETAIL;
//	}
	
}
