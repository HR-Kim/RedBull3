package kr.co.redbull.product.service;

import kr.co.redbull.cmn.DTO;

public class Product extends DTO {
	private String pNum     ;//상품번호
	private String pName    ;//상품명
	private int    bPrice   ;//기본가격
	private String detail   ;//상세정보
	private double discount ;//할인율
	private int    dPrice   ;//배송비
	private String pCategory;//카테고리
	private String regId    ;//등록자
	private String regDt    ;//등록일
	private String modId    ;//수정자
	private String modDt    ;//수정일
	
	public Product() {}

	

	public String getpNum() {
		return pNum;
	}



	public void setpNum(String pNum) {
		this.pNum = pNum;
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



	public String getDetail() {
		return detail;
	}



	public void setDetail(String detail) {
		this.detail = detail;
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



	public String getpCategory() {
		return pCategory;
	}



	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
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



	public String getModId() {
		return modId;
	}



	public void setModId(String modId) {
		this.modId = modId;
	}



	public String getModDt() {
		return modDt;
	}



	public void setModDt(String modDt) {
		this.modDt = modDt;
	}



	public Product(String pNum, String pName, int bPrice, String detail, double discount, int dPrice, String pCategory,
			String regId, String regDt, String modId, String modDt) {
		super();
		this.pNum = pNum;
		this.pName = pName;
		this.bPrice = bPrice;
		this.detail = detail;
		this.discount = discount;
		this.dPrice = dPrice;
		this.pCategory = pCategory;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}



	@Override
	public String toString() {
		return "Product [pNum=" + pNum + ", pName=" + pName + ", bPrice=" + bPrice + ", detail=" + detail
				+ ", discount=" + discount + ", dPrice=" + dPrice + ", pCategory=" + pCategory + ", regId=" + regId
				+ ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()=" + super.toString()
				+ "]";
	}	
}
