package kr.co.redbull.mypage.service;

import kr.co.redbull.cmn.DTO;

public class MyGood extends DTO {
	/** 좋아요 번호 */
	private int gNum;
	
	/** 이미지경로 */
	private String saveFileNm;
	
	/** 상품 이름 */
	private String pName;
	
	/** 좋아요 링크 */
	private int bPrice;
	
	/** 할인율 */
	private double discount;
	
	/** 배달 비 */
	private int dPrice;
	
	/** 등록자(사용자) */
	private String regId;
	
	/** 등록일 */
	private String regDt;
	
	public int getgNum() {
		return gNum;
	}

	public void setgNum(int gNum) {
		this.gNum = gNum;
	}

	public String getSaveFileNm() {
		return saveFileNm;
	}

	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getbPrice() {
		return bPrice;
	}

	public void setbPrice(int bPrice) {
		this.bPrice = bPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getdPrice() {
		return dPrice;
	}

	public void setdPrice(int dPrice) {
		this.dPrice = dPrice;
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



	public MyGood() {}

	public MyGood(int gNum, String saveFileNm, String pName, int bPrice, long discount, int dPrice, String regId,
			String regDt) {
		super();
		this.gNum = gNum;
		this.saveFileNm = saveFileNm;
		this.pName = pName;
		this.bPrice = bPrice;
		this.discount = discount;
		this.dPrice = dPrice;
		this.regId = regId;
		this.regDt = regDt;
	}

	
}
