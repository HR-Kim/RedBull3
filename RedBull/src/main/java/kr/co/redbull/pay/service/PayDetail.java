package kr.co.redbull.pay.service;

import kr.co.redbull.cart.service.Cart;

public class PayDetail extends Cart {
	
	private int payDetailNum; //결제상세번호
	private String orderNum; //주문번호 20191019random
	private String oNum; //옵션번호
	private int cartCnt; //옵션 수량(카트)
	
	public PayDetail() {}

	public PayDetail(int payDetailNum, String orderNum, String oNum, int cartCnt) {
		super();
		this.payDetailNum = payDetailNum;
		this.orderNum = orderNum;
		this.oNum = oNum;
		this.cartCnt = cartCnt;
	}

	public int getPayDetailNum() {
		return payDetailNum;
	}

	public void setPayDetailNum(int payDetailNum) {
		this.payDetailNum = payDetailNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getoNum() {
		return oNum;
	}

	public void setoNum(String oNum) {
		this.oNum = oNum;
	}

	public int getCartCnt() {
		return cartCnt;
	}

	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
	}

	@Override
	public String toString() {
		return "PayDetail [payDetailNum=" + payDetailNum + ", orderNum=" + orderNum + ", oNum=" + oNum + ", cartCnt="
				+ cartCnt + ", toString()=" + super.toString() + "]";
	}
	
}
