package kr.co.redbull.file.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import kr.co.redbull.cmn.StringUtil;
import kr.co.redbull.file.service.FileVO;

@Controller
public class FileController {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	private static final String VIEW_NAME   = "file/file";

	@Resource(name="downloadView")
	private View download;
	
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
	@RequestMapping(value="file/do_cksave.do",method = RequestMethod.POST)
	public void communityImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
		OutputStream out = null;
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
 
        try{
 
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();
            String uploadPath = "D:\\\\HR_FILE\\" + fileName;//저장경로
 
            out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            String callback = request.getParameter("CKEditorFuncNum");
 
            printWriter = response.getWriter();
            String fileUrl = request.getContextPath()+"/" + fileName;//url경로
 
            printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + fileUrl
                    + "','이미지를 업로드 하였습니다.'"
                    + ")</script>");
            printWriter.flush();
 
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        return;
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
