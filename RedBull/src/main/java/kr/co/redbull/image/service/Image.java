package kr.co.redbull.image.service;

import kr.co.redbull.cmn.DTO;

public class Image extends DTO {
	private String iNum       ;//이미지번호
	private String refNum     ;//참조번호
	private String orgFileNm  ;//원본파일명
	private String saveFileNm ;//저장파일명
	private String fileSize   ;//사이즈
	private String extNm      ;//확장자
	
	public Image() {}

	public Image(String iNum, String refNum, String orgFileNm, String saveFileNm, String fileSize, String extNm) {
		super();
		this.iNum = iNum;
		this.refNum = refNum;
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.fileSize = fileSize;
		this.extNm = extNm;
	}

	public String getiNum() {
		return iNum;
	}

	public void setiNum(String iNum) {
		this.iNum = iNum;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getOrgFileNm() {
		return orgFileNm;
	}

	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}

	public String getSaveFileNm() {
		return saveFileNm;
	}

	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getExtNm() {
		return extNm;
	}

	public void setExtNm(String extNm) {
		this.extNm = extNm;
	}

	@Override
	public String toString() {
		return "Image [iNum=" + iNum + ", refNum=" + refNum + ", orgFileNm=" + orgFileNm + ", saveFileNm=" + saveFileNm
				+ ", fileSize=" + fileSize + ", extNm=" + extNm + ", toString()=" + super.toString() + "]";
	}
	
}