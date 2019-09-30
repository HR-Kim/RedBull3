package kr.co.redbull.cart.service;

import kr.co.redbull.cmn.DTO;

public class Cart extends DTO {
	private int cartnum    ;//장바구니번호
	private int pnum       ;//상품번호
	private int cartcnt    ;//수량
	private String regid   ;//등록자
	
	
	public Cart() {}
	
	public Cart(int cartnum, int pnum, int cartcnt, String regid) {
		super();
		this.cartnum = cartnum;
		this.pnum = pnum;
		this.cartcnt = cartcnt;
		this.regid = regid;
	}


	public int getCartnum() {
		return cartnum;
	}


	public void setCartnum(int cartnum) {
		this.cartnum = cartnum;
	}


	public int getPnum() {
		return pnum;
	}


	public void setPnum(int pnum) {
		this.pnum = pnum;
	}


	public int getCartcnt() {
		return cartcnt;
	}


	public void setCartcnt(int cartcnt) {
		this.cartcnt = cartcnt;
	}


	public String getRegid() {
		return regid;
	}


	public void setRegid(String regid) {
		this.regid = regid;
	}


	@Override
	public String toString() {
		return "Cart [cartnum=" + cartnum + ", pnum=" + pnum + ", cartcnt=" + cartcnt + ", regid=" + regid
				+ ", toString()=" + super.toString() + "]";
	}
	
}
