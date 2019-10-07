package kr.co.redbull.opt.service;

import kr.co.redbull.cmn.DTO;

public class OptFile extends DTO {
	private String oName   ;//옵션명
	private String oPrice  ;//옵션가격
	private String pNum    ;//상품번호
	private String iNum    ;//이미지번호
	private String refNum  ;//참조번호
	private String oFile  ;//원본파일명
	
	public OptFile(){};
	
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoPrice() {
		return oPrice;
	}
	public void setoPrice(String oPrice) {
		this.oPrice = oPrice;
	}
	public String getpNum() {
		return pNum;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
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
	public String getoFile() {
		return oFile;
	}
	public void setoFile(String oFile) {
		this.oFile = oFile;
	}
	@Override
	public String toString() {
		return "OptFile [oName=" + oName + ", oPrice=" + oPrice + ", pNum=" + pNum + ", iNum=" + iNum + ", refNum="
				+ refNum + ", oFile=" + oFile + ", toString()=" + super.toString() + "]";
	}
	
	
}
