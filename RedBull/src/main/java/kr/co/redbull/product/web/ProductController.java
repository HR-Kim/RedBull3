package kr.co.redbull.product.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.opt.service.Opt;
import kr.co.redbull.opt.service.OptService;
import kr.co.redbull.product.service.Product;
import kr.co.redbull.product.service.ProductService;

@Controller
public class ProductController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OptService optService;
	
	private String viewListNm ="product/category_all";
	private final String VIEW_DETAIL  ="product/product_detail";
	private final String VIEW_MNG_NM  ="product/product_mng";
	private final String VIEW_OPT_NM  ="product/product_option";
	
	//옵션 추가 화면 이동
	@RequestMapping(value = "product/do_product_option.do", method = RequestMethod.GET)
	public String do_product_option(Model model, HttpServletRequest request, HttpSession session) {
		LOG.debug("================================");
		LOG.debug("do_product_option");
		LOG.debug("================================");	
		
		//VO 설정
		Product product = new Product();
		product.setpCategory(request.getParameter("pCategory"));
		product.setpName(request.getParameter("pName"));
		product.setbPrice(Integer.parseInt(StringUtil.nvl(request.getParameter("bPrice"),"0")));
		product.setDiscount(Double.parseDouble(StringUtil.nvl(request.getParameter("discount"),"0")));
		product.setdPrice(Integer.parseInt(StringUtil.nvl(request.getParameter("dPrice"),"0")));
		product.setDetail(request.getParameter("detail"));
		
		session.setAttribute("newProduct", product);
		
		return VIEW_OPT_NM;
	}
	//글쓰기 화면 이동
	@RequestMapping(value = "product/do_product_mng.do", method = RequestMethod.GET)
	public String do_product_mng(Model model, HttpServletRequest request) {
		LOG.debug("================================");
		LOG.debug("do_product_mng");
		LOG.debug("================================");
		
		return VIEW_MNG_NM;
	}
	
	@RequestMapping(value = "product/get_retrieve.do", method = RequestMethod.GET)
	public String get_retrieve(Search search, Model model, HttpServletRequest request) {
		
		LOG.debug("================================");
		LOG.debug("search:"+search);
		LOG.debug("================================");
		
		if(search.getPageSize()==0) search.setPageSize(10);
		if(search.getPageNum()==0)  search.setPageNum(1);
		
		
		model.addAttribute("vo", search);
		
		List<Product> list = (List<Product>)productService.get_retrieve(search);
		
		model.addAttribute("list", list);
		
		//총건수
		int totalCnt = 0;
		if(null!=list && list.size()>0) {
			totalCnt = list.get(0).getTotalCnt();
			model.addAttribute("totalCnt", totalCnt);
		}
		
		
		return viewListNm;
	}
	
	@RequestMapping(value = "product/get_selectOne.do", method = RequestMethod.GET)
	public String get_selectOne(Product product, Model model) {
		
		LOG.debug("================================");
		LOG.debug("Product:"+product);
		LOG.debug("================================");
		
		if(product.getpNum().equals("") || product.getpNum()==null) {
			throw new IllegalArgumentException("글이 존재하지 않습니다.");
		}
		//상품테이블
		Product productVO = (Product)productService.get_selectOne(product);
		
		//Search Word 설정
		Search searchOne = new Search();
		searchOne.setSearchWord(productVO.getpNum());
		
		//옵션테이블
		List<Opt> optList = (List<Opt>)optService.get_pNumList(searchOne);
		
		//파람 전달
		model.addAttribute("productVO", productVO);
		model.addAttribute("optList", optList);
		
		return VIEW_DETAIL;
	}
	
}
