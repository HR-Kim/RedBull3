package kr.co.redbull.opt.service;

import kr.co.redbull.cmn.DTO;

public class Opt extends DTO {
	private String ONUM    ;
	private String ONAME   ;
	private int    OPRICE  ;
	private String PNUM    ;
	private String INUM    ;
	
	public Opt() {}

	public Opt(String oNUM, String oNAME, int oPRICE, String pNUM, String iNUM) {
		super();
		ONUM = oNUM;
		ONAME = oNAME;
		OPRICE = oPRICE;
		PNUM = pNUM;
		INUM = iNUM;
	}

	public String getONUM() {
		return ONUM;
	}

	public void setONUM(String oNUM) {
		ONUM = oNUM;
	}

	public String getONAME() {
		return ONAME;
	}

	public void setONAME(String oNAME) {
		ONAME = oNAME;
	}

	public int getOPRICE() {
		return OPRICE;
	}

	public void setOPRICE(int oPRICE) {
		OPRICE = oPRICE;
	}

	public String getPNUM() {
		return PNUM;
	}

	public void setPNUM(String pNUM) {
		PNUM = pNUM;
	}

	public String getINUM() {
		return INUM;
	}

	public void setINUM(String iNUM) {
		INUM = iNUM;
	}

	@Override
	public String toString() {
		return "Opt [ONUM=" + ONUM + ", ONAME=" + ONAME + ", OPRICE=" + OPRICE + ", PNUM=" + PNUM + ", INUM=" + INUM
				+ ", toString()=" + super.toString() + "]";
	}
}
