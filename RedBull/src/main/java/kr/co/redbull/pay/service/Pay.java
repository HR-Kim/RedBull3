package kr.co.redbull.pay.service;

import java.util.List;

import kr.co.redbull.cart.service.Cart;
import kr.co.redbull.cmn.DTO;

public class Pay extends Cart {
	
	private String orderNum; 	//주문번호 20191019random
	private int amount; 		//총 결제금액
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
	
	private int productCnt;  //수량
	private int productPrice; //상품가격
	private double discount; //할인가
	private int delivery; //배송비
	private int option; //옵션비
	

	public Pay() {}


	public Pay(String orderNum, int amount, String dName, int dPostNum, String dAddr, String dDetAddr, String dPhone,
			String dMemo, String payMethod, String payId, String payDt, List<Pay> list, int productCnt,
			int productPrice, double discount, int delivery, int option) {
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
		this.productCnt = productCnt;
		this.productPrice = productPrice;
		this.discount = discount;
		this.delivery = delivery;
		this.option = option;
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
//
//	public void setList(List<Pay> list) {
//		this.list = list;
//	}


	public int getProductCnt() {
		return productCnt;
	}


	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
	}


	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public int getDelivery() {
		return delivery;
	}


	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}


	public int getOption() {
		return option;
	}


	public void setOption(int option) {
		this.option = option;
	}


	@Override
	public String toString() {
		return "Pay [orderNum=" + orderNum + ", amount=" + amount + ", dName=" + dName + ", dPostNum=" + dPostNum
				+ ", dAddr=" + dAddr + ", dDetAddr=" + dDetAddr + ", dPhone=" + dPhone + ", dMemo=" + dMemo
				+ ", payMethod=" + payMethod + ", payId=" + payId + ", payDt=" + payDt + ", list=" + list
				+ ", productCnt=" + productCnt + ", productPrice=" + productPrice + ", discount=" + discount
				+ ", delivery=" + delivery + ", option=" + option + ", toString()=" + super.toString() + "]";
	}


}