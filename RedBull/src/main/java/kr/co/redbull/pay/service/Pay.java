package kr.co.redbull.pay.service;

import java.util.List;

import kr.co.redbull.cart.service.Cart;

public class Pay extends Cart {
	
	private String orderNum; 	//주문번호 20191019random
	private int amount; 			//총 결제금액
	private String dName; 		//수령인
	private int dPostNum; 		//우편번호
	private String dAddr; 		//주소1
	private String dDetAddr; 	//주소2
	private String dPhone; 		//휴대전화
	private String dMemo; 		//배송메모
	private String payMethod; 	//결제수단
	private String payId; 		//결제자 
	private String payDt; 		//결제일
	private List<Pay> list;
	
//	private int payDetailNum; //결제상세번호
//	private String orderDeNum; //주문번호 20191019random
//	private String oNum; //옵션번호
//	private int cartCnt; //옵션 수량(카트)
	
	
	private String pName; //상품명
	private int bPrice;	//상품금액
	private double discount; //할인금액
	private int dPrice; //배송비
	
	private int oPrice; //옵션금액
	
	private int cartCnt; //수량_detail
	private String oNum; //옵션번호 _detail
	
	
	//private String iNum; //이미지 정보    왜 있는지 모르겠음 추후 삭제
	
	public Pay() {}

	//주문
	public Pay(String orderNum, int amount, String dName, int dPostNum, String dAddr, String dDetAddr, String dPhone,
			String dMemo, String payMethod, String payId, String payDt, List<Pay> list) {
		super();
		this.orderNum = orderNum;
		this.amount = amount;
		this.dName = dName;
		this.dPostNum = dPostNum;
		this.dAddr = dAddr;
		this.dDetAddr = dDetAddr;
		this.dPhone = dPhone;
		this.dMemo = dMemo;
		this.payMethod = payMethod;
		this.payId = payId;
		this.payDt = payDt;
		this.list = list;
	}
	
	//바로결제
	public Pay(int bPrice, double discount, int dPrice, int cartCnt, int oPrice) {
		super();
		this.bPrice = bPrice;
		this.discount = discount;
		this.dPrice = dPrice;
		this.cartCnt = cartCnt;
		this.oPrice = oPrice;
	}

	
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public int getdPostNum() {
		return dPostNum;
	}

	public void setdPostNum(int dPostNum) {
		this.dPostNum = dPostNum;
	}

	public String getdAddr() {
		return dAddr;
	}

	public void setdAddr(String dAddr) {
		this.dAddr = dAddr;
	}

	public String getdDetAddr() {
		return dDetAddr;
	}

	public void setdDetAddr(String dDetAddr) {
		this.dDetAddr = dDetAddr;
	}

	public String getdPhone() {
		return dPhone;
	}

	public void setdPhone(String dPhone) {
		this.dPhone = dPhone;
	}

	public String getdMemo() {
		return dMemo;
	}

	public void setdMemo(String dMemo) {
		this.dMemo = dMemo;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPayDt() {
		return payDt;
	}

	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}

//	public List<Pay> getList() {
//		return list;
//	}
//
//	public void setList(List<Pay> list) {
//		this.list = list;
//	}
	
	
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

	public int getCartCnt() {
		return cartCnt;
	}

	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
	}

	public int getoPrice() {
		return oPrice;
	}

	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}

	@Override
	public String toString() {
		return "Pay [orderNum=" + orderNum + ", amount=" + amount + ", dName=" + dName + ", dPostNum=" + dPostNum
				+ ", dAddr=" + dAddr + ", dDetAddr=" + dDetAddr + ", dPhone=" + dPhone + ", dMemo=" + dMemo
				+ ", payMethod=" + payMethod + ", payId=" + payId + ", payDt=" + payDt + ", list=" + list + ", bPrice="
				+ bPrice + ", discount=" + discount + ", dPrice=" + dPrice + ", cartCnt=" + cartCnt + ", oPrice="
				+ oPrice + ", toString()=" + super.toString() + "]";
	}


}