package kr.co.redbull.main.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.redbull.category.service.CategoryService;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.image.service.Image;
import kr.co.redbull.image.service.ImageService;
import kr.co.redbull.product.service.Product;

@Controller
public class MainController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ImageService imageService;
	
	// View를 상수로 선언
	private final String VIEW_NM = "main/main";
	
	/**화면 호출*/
	//http://localhost:8080/redbull/main/main.do
	@RequestMapping(value="main/main.do", method = RequestMethod.GET)
	public String main(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("============================");
		LOG.debug("=MainController VIEW_NM: " + VIEW_NM);
		LOG.debug("============================");
		
		//0---search------------------------------------------------------------------
		LOG.debug("================================");
		LOG.debug("MainController search:"+search);
		LOG.debug("================================");
		
		//NUll 처리
		if(search.getPageSize()==0) search.setPageSize(9);
		if(search.getSearchDiv()==null) search.setSearchDiv("10");
		if(search.getPageNum()==0)  search.setPageNum(1);
		if(search.getSearchWord()==null) search.setSearchWord("");
		
		model.addAttribute("vo", search);
		
		//1---랭킹 리스트------------------------------------------------------------------
		List<Product> rankList = (List<Product>)categoryService.get_rankList(search); 
		List<Product> rankoutList = new ArrayList<Product>();
		
		// 썸네일 설정 : 안쓰는 detail에 이미지 경로를 넣자
		for (int i = 0; i < rankList.size(); i++) { // 랭킹 리스트에서 
			Product getrankOne = rankList.get(i); // 객체와 
			String getrankPnum = getrankOne.getpNum(); // 제품 번호를 가져옴

			// Pnum으로 검색
			Search rankSearch = new Search();
			rankSearch.setSearchWord(getrankPnum); // 제품 번호를 set하고 
			String ranksaveFileNm = "";
			List<Image> rankImageList = new ArrayList<Image>();
			rankImageList = (List<Image>) imageService.get_refnumList(rankSearch); // 이미지를 검색
			// 검색 결과가 없다면? : 기본 이미지
			if (rankImageList.size() < 1) {
				ranksaveFileNm = "resources/img/product/noimage.jpg";
			} else {
				// 있으면 첫번째 이미지
				ranksaveFileNm = rankImageList.get(0).getSaveFileNm();
			}

			getrankOne.setDetail(ranksaveFileNm); // 이미지를 제품 객체에 set
			rankoutList.add(getrankOne);
		}
		
		model.addAttribute("ranklist", rankoutList); 
		
		// 랭킹 총건수
		int ranktotalCnt = 0;
		if(null!=rankoutList && rankoutList.size()>0) {
			ranktotalCnt = rankoutList.get(0).getTotalCnt();
			model.addAttribute("ranktotalCnt", ranktotalCnt);
		}
		
		//2---세일상품 리스트------------------------------------------------------------------
		List<Product> saleList = (List<Product>)categoryService.get_hotSaleList(search); 
		List<Product> saleoutList = new ArrayList<Product>();
		
		// 썸네일 설정 : 안쓰는 detail에 이미지 경로를 넣자
		for (int i = 0; i < saleList.size(); i++) { // 랭킹 리스트에서 
			Product getsaleOne = saleList.get(i); // 객체와 
			String getsalePnum = getsaleOne.getpNum(); // 제품 번호를 가져옴

			// Pnum으로 검색
			Search saleSearch = new Search();
			saleSearch.setSearchWord(getsalePnum); // 제품 번호를 set하고 
			String salesaveFileNm = "";
			List<Image> saleImageList = new ArrayList<Image>();
			saleImageList = (List<Image>) imageService.get_refnumList(saleSearch); // 이미지를 검색
			// 검색 결과가 없다면? : 기본 이미지
			if (saleImageList.size() < 1) {
				salesaveFileNm = "resources/img/product/noimage.jpg";
			} else {
				// 있으면 첫번째 이미지
				salesaveFileNm = saleImageList.get(0).getSaveFileNm();
			}

			getsaleOne.setDetail(salesaveFileNm); // 이미지를 제품 객체에 set
			saleoutList.add(getsaleOne);
		}
		
		model.addAttribute("salelist", saleoutList); 
		
		// 세일 총건수
		int saletotalCnt = 0;
		if(null!=saleoutList && saleoutList.size()>0) {
			saletotalCnt = saleoutList.get(0).getTotalCnt();
			model.addAttribute("saletotalCnt", saletotalCnt);
		}
		
		//3---신상 리스트------------------------------------------------------------------
		List<Product> newList = (List<Product>)categoryService.get_newList(search); 
		List<Product> newoutList = new ArrayList<Product>();
		
		// 썸네일 설정 : 안쓰는 detail에 이미지 경로를 넣자
		for (int i = 0; i < newList.size(); i++) { // 랭킹 리스트에서 
			Product getnewOne = newList.get(i); // 객체와 
			String getnewPnum = getnewOne.getpNum(); // 제품 번호를 가져옴

			// Pnum으로 검색
			Search newSearch = new Search();
			newSearch.setSearchWord(getnewPnum); // 제품 번호를 set하고 
			String newsaveFileNm = "";
			List<Image> newImageList = new ArrayList<Image>();
			newImageList = (List<Image>) imageService.get_refnumList(newSearch); // 이미지를 검색
			// 검색 결과가 없다면? : 기본 이미지
			if (newImageList.size() < 1) {
				newsaveFileNm = "resources/img/product/noimage.jpg";
			} else {
				// 있으면 첫번째 이미지
				newsaveFileNm = newImageList.get(0).getSaveFileNm();
			}

			getnewOne.setDetail(newsaveFileNm); // 이미지를 제품 객체에 set
			newoutList.add(getnewOne);
		}
		
		model.addAttribute("newlist", newoutList); 
		
		// 신상 총건수
		int newtotalCnt = 0;
		if(null!=newoutList && newoutList.size()>0) {
			newtotalCnt = newoutList.get(0).getTotalCnt();
			model.addAttribute("newtotalCnt", newtotalCnt);
		}
		
		return VIEW_NM;
		
	}//--main

}//--class
