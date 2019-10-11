package kr.co.redbull.file.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.file.service.FileVO;
import kr.co.redbull.image.service.Image;
import kr.co.redbull.product.service.Product;
import kr.co.redbull.product.service.ProductService;

@Controller
public class FileController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	private static final String VIEW_NAME   = "file/file";
	private static final String VIEW_PR_MNG_NM   = "product/product_mng";
	
	@Resource(name="downloadView")
	private View download;
	
	@Autowired
	ProductService productService;
	
	//----------------------------------------------------
	//			download.do
	//	file.jsp  ->  FileController.java
	//       				-download()  -> View(downloadView) 
	//		                 		 -> DownloadView.java
	//		                 		 	-renderMergedOutputModel()
	//		                 		 	-setDownloadFileName
	//		                 		 	-downloadFile
	//----------------------------------------------------
	@RequestMapping(value="file/download.do",method = RequestMethod.POST)
	public ModelAndView download(HttpServletRequest req, ModelAndView mView) {
		String orgFileNm  = req.getParameter("orgFileNm");// 원본파일명
		String saveFileNm = req.getParameter("saveFileNm");// 저장파일명 
		LOG.debug("===============================");
		LOG.debug("=@Controller orgFileNm="+orgFileNm);
		LOG.debug("=@Controller saveFileNm="+saveFileNm);
		LOG.debug("===============================");	
		//File downloadFile = (File)model.get("downloadFile");
		//String orgFileNm = (String)model.get("orgFileNm");
		mView.setView(download);
		
		File  downloadFile=new File(saveFileNm);
		mView.addObject("downloadFile", downloadFile);
		mView.addObject("orgFileNm", orgFileNm);
		return mView;
	}
	
	//http://localhost:8080/ehr/file/uploadfileview.do
	@RequestMapping(value="file/uploadfileview.do")
	public String uploadFileView() {
		LOG.debug("===============================");
		LOG.debug("=@Controller uploadFileView=");
		LOG.debug("===============================");
		return VIEW_NAME;
	}
	
	//root_path를 넘겨야 사용가능.(StringUtil에서 UPLOAD_ROOT 사용 안함)
	//ModelAndView : Model + View
	@RequestMapping(value="file/do_img_save",method = RequestMethod.POST, consumes ={"multipart/form-data"})
	public ModelAndView do_img_save(MultipartHttpServletRequest mReg, HttpSession session) throws IllegalStateException, IOException {
		LOG.debug("===============================");
		LOG.debug("=@Controller do_img_save=");
		LOG.debug("===============================");
		
		//VO 설정
		Product product = new Product();
		product.setpCategory(mReg.getParameter("pCategory"));
		product.setpName(mReg.getParameter("pName"));
		product.setbPrice(Integer.parseInt(StringUtil.nvl(mReg.getParameter("bPrice"),"0")));
		product.setDiscount(Double.parseDouble(StringUtil.nvl(mReg.getParameter("discount"),"0")));
		product.setdPrice(Integer.parseInt(StringUtil.nvl(mReg.getParameter("dPrice"),"0")));
		product.setDetail(mReg.getParameter("detail"));
		
		//입력 중인 값 세션에 저장
		session.setAttribute("newProduct", product);
		
		//세션에서 ImageList 가져오기
		List<Image> newImageList = (List<Image>) session.getAttribute("newImageList");
		if(newImageList == null) {
			newImageList = new ArrayList<Image>();
		}
		//Upload파일 정보 : 참조번호(상품번호),원본파일명,저장파일명,사이즈,확장자
		Image newImage = new Image();
		
		//root_path 전달
		String UPLOAD_ROOT = StringUtil.nvl(mReg.getParameter("root_path"));
		String SAVE_ROOT = "resources"+File.separator+"img"+File.separator+"product";
		//pNum
		String pNum = StringUtil.nvl(mReg.getParameter("pNum"));
		if(pNum.equals("") || pNum == null) {
			Product tmpProduct = (Product) productService.get_nextPnum();
			pNum = tmpProduct.getpNum();
		}
		//--------------------------------------------
		//-예외처리
		//--------------------------------------------
		if(UPLOAD_ROOT.equals("")) {
			throw new ArithmeticException("0으로 나눌수 없습니다.");
		}
	
		LOG.debug("=@Controller UPLOAD_ROOT="+UPLOAD_ROOT);
		
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
		SAVE_ROOT = SAVE_ROOT+File.separator+yyyy+File.separator+mm;
		LOG.debug("=@Controller datePath="+datePath);
		LOG.debug("=@Controller SAVE_ROOT="+SAVE_ROOT);
		
		File  fileYearMM = new File(datePath);  
		
		if(fileYearMM.isDirectory()==false) {
			boolean flag = fileYearMM.mkdirs();  
			LOG.debug("=@Controller fileYearMM flag="+flag);
		}
		
		//01.파일 Read      
		Iterator<String> files = mReg.getFileNames();
		while(files.hasNext()) {
			String orgFileNm  = "";//원본파일명
			String saveFileNm = "";//저장파일명
			long   fileSize   = 0L;//파일사이즈
			String ext        = "";//확장자
			
			String uploadFileNm = files.next();//file01
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNm = mFile.getOriginalFilename();
			//file선택이 않되면 continue
			if(null==orgFileNm || orgFileNm.equals(""))continue;
			
			
			LOG.debug("=@Controller uploadFileNm="+uploadFileNm);
			LOG.debug("=@Controller orgFileNm="+orgFileNm);
			fileSize = mFile.getSize();//file size byte
			
			if(orgFileNm.indexOf(".")>-1) {
				ext = orgFileNm.substring(orgFileNm.indexOf(".")+1);
			}
			LOG.debug("=@Controller fileSize="+fileSize);
			LOG.debug("=@Controller ext="+ext);
			File orgFileCheck = new File(datePath,orgFileNm);
			
			String newFile = orgFileCheck.getAbsolutePath();
			String saveFile = SAVE_ROOT+File.separator+orgFileNm;
			//04.파일 rename: README -> README1~9999
			if(orgFileCheck.exists()==true) {
				newFile = StringUtil.fileRename(orgFileCheck);
				saveFile = SAVE_ROOT+File.separator+StringUtil.fileRenameShort(orgFileCheck);
				LOG.debug(saveFile);
			}
			newImage.setRefNum(pNum);
			newImage.setOrgFileNm(orgFileNm);
			newImage.setSaveFileNm(saveFile);
			newImage.setFileSize(fileSize);
			newImage.setExtNm(ext);
			
			mFile.transferTo(new File(newFile));
		}
		ModelAndView model = new ModelAndView();
		Map resultMap = new HashMap();
		resultMap.put("newImage", newImage);
		
		//세션값 업데이트
		newImageList.add(newImage);
		session.setAttribute("newImageList", newImageList);
		
		
		model.addAllObjects(resultMap);
		model.setViewName("jsonView");
		
		return model;
	}
	
	//root_path를 넘겨야 사용가능.(StringUtil에서 UPLOAD_ROOT 사용 안함)
	//ModelAndView : Model + View
	@RequestMapping(value="file/do_save.do",method = RequestMethod.POST)
	public ModelAndView do_save(MultipartHttpServletRequest mReg
			   , ModelAndView model) throws IllegalStateException, IOException {
		LOG.debug("===============================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("===============================");
		//Upload파일 정보: 원본,저장,사이즈,확장자 List
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		//root_path 전달
		String contxt = StringUtil.nvl(mReg.getParameter("root_path"));
		//--------------------------------------------
		//-예외처리
		//--------------------------------------------
		if(contxt.equals("")) {
			throw new ArithmeticException("0으로 나눌수 없습니다.");
		}
	
		LOG.debug("=@Controller contxt="+contxt);
		String UPLOAD_ROOT = contxt;
		
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
			FileVO fileVO=new FileVO();
			String orgFileNm  = "";//원본파일명
			String saveFileNm = "";//저장파일명
			long   fileSize   = 0L;//파일사이즈
			String ext        = "";//확장자
			
			String uploadFileNm = files.next();//file01
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNm = mFile.getOriginalFilename();
			//file선택이 않되면 continue
			if(null==orgFileNm || orgFileNm.equals(""))continue;
			
			
			LOG.debug("=@Controller uploadFileNm="+uploadFileNm);
			LOG.debug("=@Controller orgFileNm="+orgFileNm);
			fileSize = mFile.getSize();//file size byte
			
			if(orgFileNm.indexOf(".")>-1) {
				ext = orgFileNm.substring(orgFileNm.indexOf(".")+1);
			}
			LOG.debug("=@Controller fileSize="+fileSize);
			LOG.debug("=@Controller ext="+ext);
			File orgFileCheck = new File(datePath,orgFileNm);
			
			String newFile = orgFileCheck.getAbsolutePath();
			//04.파일 rename: README -> README1~9999
			if(orgFileCheck.exists()==true) {
				newFile = StringUtil.fileRename(orgFileCheck);
			}
			
			fileVO.setOrgFileNm(orgFileNm);
			fileVO.setSaveFileNm(newFile);
			fileVO.setFileSize(fileSize);
			fileVO.setExt(ext);
			fileList.add(fileVO);
			mFile.transferTo(new File(newFile));
		}
	
		model.addObject("fileList", fileList);
	
		     
		model.setViewName(VIEW_NAME);
		return model;
	}
}
