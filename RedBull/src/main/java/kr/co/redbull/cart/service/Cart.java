package kr.co.redbull.cart.service;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.product.service.Product;

public class Cart extends Product {
	private int cartNum    ;//장바구니번호
	private String pNum       ;//상품번호
	private int cartCnt    ;//수량
	private String regId   ;//등록자
	
	
	public Cart() {}


	public Cart(int cartNum, String pNum, int cartCnt, String regId) {
		super();
		this.cartNum = cartNum;
		this.pNum = pNum;
		this.cartCnt = cartCnt;
		this.regId = regId;
	}


	public int getCartNum() {
		return cartNum;
	}


	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}


	public String getpNum() {
		return pNum;
	}


	public void setpNum(String pNum) {
		this.pNum = pNum;
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
		return "Cart [cartNum=" + cartNum + ", pNum=" + pNum + ", cartCnt=" + cartCnt + ", regId=" + regId
				+ ", toString()=" + super.toString() + "]";
	}
	
}
