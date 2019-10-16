
package kr.co.redbull.opt.service;

import java.util.List;

import kr.co.redbull.cmn.DTO;
import kr.co.redbull.image.service.Image;

public class Opt extends Image {
	private String oNum    ;
	private String oName   ;
	private int    oPrice  ;
	private String pNum    ;
	private String iNum    ;
	private List<Opt> optList ;
	
	
	
	public Opt() {}

	public Opt(String oNum, String oName, int oPrice, String pNum, String iNum) {
		super();
		this.oNum = oNum;
		this.oName = oName;
		this.oPrice = oPrice;
		this.pNum = pNum;
		this.iNum = iNum;
	}	
	

	public List<Opt> getOptList() {
		return optList;
	}

	public void setOptList(List<Opt> optList) {
		this.optList = optList;
	}

	public String getoNum() {
		return oNum;
	}

	public void setoNum(String oNum) {
		this.oNum = oNum;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public int getoPrice() {
		return oPrice;
	}

	public void setoPrice(int oPrice) {
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

	@Override
	public String toString() {
		return "Opt [oNum=" + oNum + ", oName=" + oName + ", oPrice=" + oPrice + ", pNum=" + pNum + ", iNum=" + iNum
				+ ", optList=" + optList + "]";
	}
	
}
