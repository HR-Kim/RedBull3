package kr.co.redbull.product.web;

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
import kr.co.redbull.cmn.Message;
import kr.co.redbull.cmn.Search;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.image.service.Image;
import kr.co.redbull.image.service.ImageService;
import kr.co.redbull.opt.service.Opt;
import kr.co.redbull.opt.service.OptService;
import kr.co.redbull.product.service.Product;
import kr.co.redbull.product.service.ProductService;
import net.sf.json.JSONArray;

@Controller
public class ProductController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	OptService optService;
	
	private final String VIEW_LIST_NM ="product/category_all";
	private final String VIEW_LIST_NM2 ="product/category_category";
	private final String VIEW_DETAIL  ="product/product_detail";
	private final String VIEW_MNG_NM  ="product/product_mng";
	private final String VIEW_OPT_NM  ="product/product_option";
	
	//상품 수정
	@RequestMapping(value = "product/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_delete(Product product, HttpSession session){
		LOG.debug("=======================");
		LOG.debug("=do_delete=");
		LOG.debug("product : "+product);
		LOG.debug("=======================");
		
		//3가지 삭제
		//newProduct(product VO)가 있어야 함 
		Product newProduct = (Product) productService.get_selectOne(product);
		LOG.debug("=@do_update.do get_selectOne=");
		LOG.debug("newProduct : "+newProduct);
		LOG.debug("=======================");
		
		Search searchPnum = new Search();
		
		//newOptList(List Opt VO)가 있어야 함 
		searchPnum.setSearchWord(newProduct.getpNum());
		List<Opt> newOptList = (List<Opt>) optService.get_pNumList(searchPnum);
		LOG.debug("=@do_update.do get_selectOne=");
		LOG.debug("newOptList : "+newOptList);
		LOG.debug("=======================");
		
		//newImageList(List Image VO)가 있어야 함
		List<Image> newImageList = (List<Image>) imageService.get_refnumList(searchPnum);
		LOG.debug("=@do_update.do get_selectOne=");
		LOG.debug("newImageList : "+newImageList);
		LOG.debug("=======================");
		
		//삭제
		int flag = 0;
		//이미지
		for(Image delImage : newImageList) {
			flag = imageService.do_delete(delImage);
		}
		//옵션
		for(Opt delOpt : newOptList) {
			flag = optService.do_delete(delOpt);
		}
		//상품
		flag = productService.do_delete(newProduct);
		
		//메세지 설정
		Message  message=new Message();
		if(flag==1) {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 되었습니다.");
		}else {
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("삭제 실패.");
		}
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("=gsonStr="+gsonStr);
		LOG.debug("============================");
		
		return gsonStr;
	}
	
	//상품 수정
	@RequestMapping(value = "product/do_update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(Product product, HttpSession session){
		LOG.debug("=======================");
		LOG.debug("=do_update=");
		LOG.debug("product : "+product);
		LOG.debug("=======================");
		
		//세션 설정 3가지
		//newProduct(product VO)가 있어야 함 
		Product newProduct = (Product) productService.get_selectOne(product);
		LOG.debug("=@do_update.do get_selectOne=");
		LOG.debug("newProduct : "+newProduct);
		LOG.debug("=======================");
		
		Search searchPnum = new Search();
		
		//newOptList(List Opt VO)가 있어야 함 
		searchPnum.setSearchWord(newProduct.getpNum());
		List<Opt> newOptList = (List<Opt>) optService.get_pNumList(searchPnum);
		LOG.debug("=@do_update.do get_selectOne=");
		LOG.debug("newOptList : "+newOptList);
		LOG.debug("=======================");
		
		//newImageList(List Image VO)가 있어야 함
		List<Image> newImageList = (List<Image>) imageService.get_refnumList(searchPnum);
		LOG.debug("=@do_update.do get_selectOne=");
		LOG.debug("newImageList : "+newImageList);
		LOG.debug("=======================");
		
		//세션 설정 3가지
		session.setAttribute("newProduct", newProduct);
		session.setAttribute("newOptList", newOptList);
		session.setAttribute("newImageList", newImageList);
		
		//메세지 설정
		Message  message=new Message();
		message.setMsgId(String.valueOf("1"));
		message.setMsgMsg("로딩 되었습니다.");
		
		Gson gson=new Gson();
		String gsonStr = gson.toJson(message);
		
		LOG.debug("=gsonStr="+gsonStr);
		LOG.debug("============================");
		
		return gsonStr;
	}
	
	//글쓰기
	@RequestMapping(value = "product/do_write.do", method = RequestMethod.POST)
	public String do_write(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		//저장할 값 가져오기
		Product newProduct = (Product) session.getAttribute("newProduct");
		
		//Product 저장
		//세션이 없을 때
		if(newProduct == null) {
			//VO 설정
			newProduct = new Product();
			newProduct.setpCategory(request.getParameter("pCategory"));
			newProduct.setpName(request.getParameter("pName"));
			newProduct.setbPrice(Integer.parseInt(StringUtil.nvl(request.getParameter("bPrice"),"0")));
			newProduct.setDiscount(Double.parseDouble(StringUtil.nvl(request.getParameter("discount"),"0")));
			newProduct.setdPrice(Integer.parseInt(StringUtil.nvl(request.getParameter("dPrice"),"0")));
			newProduct.setDetail(request.getParameter("detail"));
		}
		//세션이 있을 때
		productService.do_save(newProduct);
		LOG.debug("new product save completed : "+newProduct);
		
		//세션에서 저장할 값 가져오기
		List<Opt> newOptList = (List<Opt>) session.getAttribute("newOptList");
		List<Image> newImageList = (List<Image>) session.getAttribute("newImageList");
		
		//Opt 저장
		for(Opt newOpt : newOptList) {
			optService.do_save(newOpt);
			LOG.debug("new option save completed : "+newOpt);
		}
		//Image 저장
		for(Image newImage : newImageList) {
			imageService.do_save(newImage);
			LOG.debug("new image save completed : "+newImage);
		}
		//상품 추가 세션 삭제
		session.removeAttribute("newProduct");
		session.removeAttribute("newOptList");
		session.removeAttribute("newImageList");
		
		return VIEW_LIST_NM;
	}
	
	//옵션 등록
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "product/do_save_option.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_save_option(HttpServletRequest request, @RequestBody String paramData, HttpSession session){
		LOG.debug("================================");
		LOG.debug("do_save_option");
		LOG.debug("================================");
		
		//JSONArray jsonArray = JSONArray.fromObject(paramData);
		
	    List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
	    resultMap = JSONArray.fromObject(paramData);
	    List<Opt> list = new ArrayList<Opt>();
	    for (Map<String, Object> map : resultMap) {
	    	//LOG.debug("oName : " +map.get("oName") +", oPrice : "+map.get("oPrice")+", "+"pNum : "  +map.get("pNum"));
	        //oName : 1, oPrice : 10, pNum : 371, iNum : 0
	        //oName : 2, oPrice : 20, pNum : 371, iNum : 63
	    	Opt tmpOpt = new Opt();
	    	tmpOpt.setoName(map.get("oName").toString());
	    	tmpOpt.setoPrice(Integer.parseInt(map.get("oPrice").toString()));
	    	tmpOpt.setpNum(map.get("pNum").toString());
	    	list.add(tmpOpt);
	    }
	    LOG.debug("list : "+list.toString());
	    session.setAttribute("newOptList", list);
	    
		Message msg = new Message();
		msg.setMsgId("10");
		msg.setMsgMsg("옵션 수정 완료");
		//JSON
		Gson gson=new Gson();
		String json = gson.toJson(msg);
		LOG.debug("2=========================");
		LOG.debug("=@Controller=json=="+json);
		LOG.debug("2=========================");
		return json;
	}
	
	//글쓰기 취소
	@RequestMapping(value = "product/do_cancel_write.do", method = RequestMethod.GET)
	public String do_cancel_write(Model model, HttpServletRequest request, HttpSession session) {
		LOG.debug("================================");
		LOG.debug("do_cancel_write");
		LOG.debug("================================");
		
		//상품 추가 세션 삭제
		session.removeAttribute("newProduct");
		session.removeAttribute("newOptList");
		session.removeAttribute("newImageList");
		
		return VIEW_LIST_NM;
	}
	
	//옵션 추가 화면 이동
	@RequestMapping(value = "product/do_product_option.do", method = RequestMethod.POST)
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
		
		//입력 중인 값 세션에 저장
		session.setAttribute("newProduct", product);
		
		//Next VO Num 설정
		//Opt
		Opt tmpOnum = (Opt) optService.get_nextOnum();
		int nextOnum = Integer.parseInt(tmpOnum.getoNum());
		//Image
		Image tmpImage = (Image) imageService.get_nextInum();
		int nextInum = Integer.parseInt(tmpImage.getiNum());
		//Product
		Product tmpProduct = (Product) productService.get_nextPnum();
		int nextPnum = Integer.parseInt(tmpProduct.getpNum());
		
		model.addAttribute("nextOnum", nextOnum);
		model.addAttribute("nextInum", nextInum);
		model.addAttribute("nextPnum", nextPnum);
		
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
		
		//NUll 처리
		if(search.getPageSize()==0) search.setPageSize(9);
		if(search.getSearchDiv()==null) search.setSearchDiv("10");
		if(search.getPageNum()==0)  search.setPageNum(1);
		if(search.getSearchWord()==null) search.setSearchWord("");
		
		
		model.addAttribute("vo", search);
		
		List<Product> tmpList = (List<Product>)productService.get_retrieve(search);
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
		
		
		return VIEW_LIST_NM2;
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
		
		//이미지리스트
		List<Image> imageList = (List<Image>) imageService.get_refnumList(searchOne);
		
		//파람 전달
		model.addAttribute("productVO", productVO);
		model.addAttribute("optList", optList);
		model.addAttribute("imageList", imageList);
		
		return VIEW_DETAIL;
	}
	
}
