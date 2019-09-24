package kr.co.redbull.cmn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


public class DownloadView extends AbstractView {
	private final Logger LOG = LoggerFactory.getLogger(DownloadView.class);
	
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			setResponseContentType(request, response);
			File downloadFile= (File) model.get("downloadFile");
			String orgFileNm = (String) model.get("orgFileNm");
			
			LOG.debug("1.===============================");
			LOG.debug("1.renderMergedOutputModel=");
			LOG.debug("1.downloadFile="+downloadFile.getName());
			LOG.debug("1.downloadFile="+downloadFile.length());
			LOG.debug("1.orgFileNm="+orgFileNm);
			LOG.debug("1.===============================");
			
			this.setDownloadFileName(orgFileNm, request, response);
			response.setContentLength( (int)(downloadFile.length()));
			this.downloadFile(downloadFile, request, response);
		}catch(Exception e) {
			throw e;
		}		
	}

	/**
	 * 저장파일명 지정
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void setDownloadFileName(String fileName
			,HttpServletRequest request
			,HttpServletResponse response) throws UnsupportedEncodingException{
		
		String userAgent = request.getHeader("User-Agent");//브라우저 정보
		LOG.debug("2=================================");
		LOG.debug("2=setDownloadFileName=");
		LOG.debug("2=userAgent="+userAgent);
		LOG.debug("2=fileName="+fileName);
		LOG.debug("2=================================");
		
		boolean idIe = (userAgent.indexOf("MSIE") !=-1);
		LOG.debug("2=idIe="+idIe);
		
		if(idIe == true) {
			fileName = URLEncoder.encode(fileName,"utf-8");
			LOG.debug("2=idIe true ="+fileName);
		}else {
			String docName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			fileName = new String(docName.getBytes("UTF-8"));
			LOG.debug("2=idIe false ="+fileName);
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"; ");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
	}
	
	/**
	 * 파일 다운로드 
	 * @param downloadFile
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void downloadFile(File downloadFile,HttpServletRequest request
			,HttpServletResponse response) throws Exception{
		
		FileInputStream in =new FileInputStream(downloadFile);
		OutputStream out   =response.getOutputStream();
		try {
			FileCopyUtils.copy(in, out);
			out.flush();
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(null !=in)in.close();
			}catch(IOException e) {
				throw e;
			}
			
			try {
				if(null !=out)out.close();
			}catch(IOException e) {
				throw e;
			}			
		}		
	}
	

}
