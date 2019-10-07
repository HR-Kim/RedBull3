package kr.co.redbull.product.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	private final String VIEW_DETAIL  ="product/product_detail";
	private final String VIEW_MNG_NM  ="product/product_mng";
	private final String VIEW_OPT_NM  ="product/product_option";
	
	//등록 
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "product/do_save_option.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String do_save_option(HttpServletRequest request, @RequestBody String paramData){
		LOG.debug("================================");
		LOG.debug("do_save_option");
		LOG.debug("================================");
		
		//JSONArray jsonArray = JSONArray.fromObject(paramData);
		 
	    List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
	    resultMap = JSONArray.fromObject(paramData);
	         
	    for (Map<String, Object> map : resultMap) {
	    	LOG.debug("oName : " +map.get("oName") +", oPrice : "+map.get("oPrice")+", "+
				      "pNum : "  +map.get("pNum")  +", iNum : "  +map.get("iNum")  +", "+
				      "refNum : "+map.get("refNum")+", oFile : "+ map.get("oFile")
	        		);
	        //oName : 1, oPrice : 10, pNum : 371, iNum : 0, refNum : 0, oFile : null
	        //oName : 2, oPrice : 20, pNum : 371, iNum : 63, refNum : 42, oFile : {}
	    }


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
	
	//등록 테스트2
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String do_save_option2(@RequestParam("oName") String oName, @RequestParam("oPrice") String oPrice, MultipartHttpServletRequest mReg ){
		LOG.debug("================================");
		LOG.debug("do_save_option");
		LOG.debug("================================");
		
		LOG.debug("oName : "+oName);
		LOG.debug("oPrice : "+oPrice);
		
		String[] oNames = oName.split(",");
		String[] oPrices = oPrice.split(",");
		Opt tmpOpt = (Opt)optService.get_nextOnum();
		int nextOnum = Integer.parseInt(tmpOpt.getoNum());
		String nextPnum = mReg.getParameter("nextPnum");
		
		List<Opt> optList = new ArrayList<Opt>();
		for(int i=0; i<oNames.length; i++) {
			Opt opt = new Opt();
			opt.setoNum(String.valueOf(nextOnum));
			opt.setoName(oNames[i]);
			opt.setoPrice(Integer.parseInt(oPrices[i]));
			opt.setpNum(nextPnum);
			nextOnum++;
			
			optList.add(opt);
		}
		LOG.debug("optList : "+optList);
		
		return VIEW_MNG_NM;
	}
	
	//옵션 추가 취소
	@RequestMapping(value = "product/do_cancel_option.do", method = RequestMethod.GET)
	public String do_cancel_option(@RequestBody Opt optList, MultipartHttpServletRequest mReg, Model model, HttpSession session) throws IllegalStateException, IOException {
		LOG.debug("================================");
		LOG.debug("do_cancel_option");
		LOG.debug("================================");
		
		//Next Pnum
		String nextPnum = mReg.getParameter("nextPnum");
		
		//OPTION VO 설정 - iNum은 첨부된 파일이 있을 때 생성
		Opt opt = new Opt();
		opt.setoName(mReg.getParameter("oName"));
		opt.setoPrice(Integer.parseInt(StringUtil.nvl(mReg.getParameter("oPrice"),"0")));
		opt.setpNum(nextPnum);
		
		//Upload파일 정보: 원본,저장,사이즈,확장자 List
		List<Image> imgList = new ArrayList<Image>();
		
		//root_path 전달
		String UPLOAD_ROOT = StringUtil.nvl(mReg.getParameter("root_path"));
		
		if(UPLOAD_ROOT.equals("")) {
			throw new ArithmeticException("root_path 예외 오류.");
		}
		
		//01.동적으로 UPLOAD_ROOT 디렉토리 생성
		File  fileRootDir = new File(UPLOAD_ROOT);
		if(fileRootDir.isDirectory() ==false) {  
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag="+flag);
		}
		
		//02.년월 디렉토리 생성:D:\\HR_FILE\2019\09
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy="+yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller mm="+mm);
		String datePath = UPLOAD_ROOT+File.separator+yyyy+File.separator+mm;
		LOG.debug("=@Controller datePath="+datePath);
		
		File  fileYearMM = new File(datePath);  
		
		if(fileYearMM.isDirectory()==false) {
			boolean flag = fileYearMM.mkdirs();  
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		//01.파일 Read      
		Iterator<String> files = mReg.getFileNames();
		while(files.hasNext()) {
			Image img = new Image();
			
			String orgFileNm  = "";      //원본파일명
			String saveFileNm = "";      //저장파일명
			long   fileSize   = 0L;      //파일사이즈
			String ext        = "";      //확장자
			String iNum	      = "";      //이미지번호
			
			String uploadFileNm = files.next();//file01
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNm = mFile.getOriginalFilename();
			
			//파일이 있을 때 실행
			if(null==orgFileNm || orgFileNm.equals(""))continue;
			LOG.debug("=@Controller orgFileNm="+orgFileNm);
			
			//파일사이즈 - byte
			fileSize = mFile.getSize();
			if(orgFileNm.indexOf(".")>-1) {
				//확장자
				ext = orgFileNm.substring(orgFileNm.indexOf(".")+1);
			}
			LOG.debug("=@Controller fileSize="+fileSize);
			LOG.debug("=@Controller ext="+ext);
			
			//파일 생성 준비
			File orgFileCheck = new File(datePath,orgFileNm);
			String newFile = orgFileCheck.getAbsolutePath();
			
			//저장파일명 중복 확인 및 이름바꾸기: README -> README1~9999
			if(orgFileCheck.exists()==true) {
				newFile = StringUtil.fileRename(orgFileCheck);
			}
			
			img.setOrgFileNm(orgFileNm);
			img.setSaveFileNm(newFile);
			img.setFileSize(fileSize);
			img.setExtNm(ext);
			//참조번호 = 새상품번호
			img.setRefNum(nextPnum);
			//이미지번호
			Image newImage = (Image) imageService.get_nextInum();
			iNum = newImage.getiNum();
			img.setiNum(iNum);
			
			imgList.add(img);
		}
		
		//옵션,이미지 세션 추가
		session.setAttribute("opt", opt);
		session.setAttribute("imgList", imgList);
		
		return VIEW_MNG_NM;
	}
	
	//글쓰기 취소
	@RequestMapping(value = "product/do_cancel_write.do", method = RequestMethod.GET)
	public String do_cancel_write(Model model, HttpServletRequest request, HttpSession session) {
		LOG.debug("================================");
		LOG.debug("do_cancel_write");
		LOG.debug("================================");
		
		//상품 추가 세션 삭제
		session.removeAttribute("newProduct");
		
		return VIEW_LIST_NM;
	}
	
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
		
		
		return VIEW_LIST_NM;
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
