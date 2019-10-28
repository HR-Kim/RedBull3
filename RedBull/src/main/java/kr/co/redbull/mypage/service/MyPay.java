package kr.co.redbull.mypage.service;

import kr.co.redbull.cmn.DTO;

public class MyPay extends DTO {
	/** 좋아요 번호 */
	private String orderNum;
	
	/** 이미지경로 */
	private String saveFileNm;
	
	/** 상품 이름 */
	private String pName;
	
	/** 옵션 이름 */
	private String oName;
	
	/** 상품 가격 */
	private int bPrice;
	
	/** 옵션 가격 */
	private int oPrice;
	
	/** 할인율 */
	private double discount;
	
	/** 배달 비 */
	private int dPrice;
	
	/** 총액 */
	private int afterPrice;
	

	public MyPay() {}


	public MyPay(String orderNum, String saveFileNm, String pName, String oName, int bPrice, int oPrice, double discount,
			int dPrice, int afterPrice) {
		super();
		this.orderNum = orderNum;
		this.saveFileNm = saveFileNm;
		this.pName = pName;
		this.oName = oName;
		this.bPrice = bPrice;
		this.oPrice = oPrice;
		this.discount = discount;
		this.dPrice = dPrice;
		this.afterPrice = afterPrice;
	}


	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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


	public String getoName() {
		return oName;
	}


	public void setoName(String oName) {
		this.oName = oName;
	}


	public int getbPrice() {
		return bPrice;
	}


	public void setbPrice(int bPrice) {
		this.bPrice = bPrice;
	}


	public int getoPrice() {
		return oPrice;
	}


	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
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


	public int getAfterPrice() {
		return afterPrice;
	}


	public void setAfterPrice(int afterPrice) {
		this.afterPrice = afterPrice;
	}

	
	
}
