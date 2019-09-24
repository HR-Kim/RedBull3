package kr.co.redbull.file.service;

import kr.co.redbull.cmn.DTO;

public class FileVO extends DTO {
	private String orgFileNm  ;//원본파일명
	private String saveFileNm ;//저장파일명
	private long   fileSize   ;//파일사이즈
	private String ext        ;//확장자
	
	public FileVO() {}

	public FileVO(String orgFileNm, String saveFileNm, long fileSize, String ext) {
		super();
		this.orgFileNm = orgFileNm;
		this.saveFileNm = saveFileNm;
		this.fileSize = fileSize;
		this.ext = ext;
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

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "File [orgFileNm=" + orgFileNm + ", saveFileNm=" + saveFileNm + ", fileSize=" + fileSize + ", ext=" + ext
				+ "]";
	}
}
