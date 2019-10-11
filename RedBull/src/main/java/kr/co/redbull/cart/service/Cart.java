package kr.co.redbull.cart.service;

import kr.co.redbull.cmn.DTO;

public class Cart extends DTO {
	private int cartNum    ;//장바구니번호
	private int oNum       ;//옵션번호
	private int cartCnt    ;//수량
	private String regId   ;//등록자
	
	
	public Cart() {}


	public Cart(int cartNum, int oNum, int cartCnt, String regId) {
		super();
		this.cartNum = cartNum;
		this.oNum = oNum;
		this.cartCnt = cartCnt;
		this.regId = regId;
	}


	public int getCartNum() {
		return cartNum;
	}


	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}


	public int getoNum() {
		return oNum;
	}


	public void setoNum(int oNum) {
		this.oNum = oNum;
	}


	public int getCartCnt() {
		return cartCnt;
	}


	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
	}


	@Override
	public String toString() {
		return "Cart [cartNum=" + cartNum + ", oNum=" + oNum + ", cartCnt=" + cartCnt + ", regId=" + regId
				+ ", toString()=" + super.toString() + "]";
	}

}
