package kr.co.redbull.file.service;

import kr.co.redbull.cmn.DTO;

public class FileVO extends DTO {
	/**파일ID   */
	private String fileId		;    
	
	/**원본파일명  */
	private String orgFileNm	;	 
	
	/**새 컬럼   */
	private String division		;    
	
	/**저장파일명  */
	private String saveFileNm	;    
	
	/**사이즈    */
	private double   fileSize	;    
	
	/**확장자    */
	private String extNm	    ;    
	
	/**등록자ID  */
	private String regId		;    
	
	/**등록일    */
	private String regDt		;    
	
	
	public FileVO() {}

	
	public FileVO(String fileId, String orgFileNm, String division, String saveFileNm, double fileSize,
			String extNm, String regId, String regDt) {
		super();
		this.fileId = fileId;
		this.orgFileNm = orgFileNm;
		this.division = division;
		this.saveFileNm = saveFileNm;
		this.fileSize = fileSize;
		this.extNm = extNm;
		this.regId = regId;
		this.regDt = regDt;
	}


	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getOrgFileNm() {
		return orgFileNm;
	}


	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getSaveFileNm() {
		return saveFileNm;
	}


	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public double getFileSize() {
		return fileSize;
	}


	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}


	public String getExtNm() {
		return extNm;
	}


	public void setExtNm(String extNm) {
		this.extNm = extNm;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	@Override
	public String toString() {
		return "FileVO [fileId=" + fileId + ", orgFileNm=" + orgFileNm + ", division=" + division + ", saveFileNm="
				+ saveFileNm + ", fileSize=" + fileSize + ", extNm=" + extNm + ", regId=" + regId + ", regDt=" + regDt
				+ ", getFileId()=" + getFileId() + ", getOrgFileNm()=" + getOrgFileNm() + ", getDivision()="
				+ getDivision() + ", getSaveFileNm()=" + getSaveFileNm() + ", getFileSize()=" + getFileSize()
				+ ", getExtNm()=" + getExtNm() + ", getRegId()=" + getRegId() + ", getRegDt()=" + getRegDt()
				+ ", toString()=" + super.toString() + "]";
	}


}
