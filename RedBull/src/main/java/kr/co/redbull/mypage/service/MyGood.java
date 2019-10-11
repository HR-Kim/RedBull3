package kr.co.redbull.mypage.service;

import kr.co.redbull.cmn.DTO;

public class MyGood extends DTO {
	/** 좋아요 번호 */
	private int gNo;
	
	/** 상품 번호 */
	private String pNum;
	
	/** 좋아요 링크 */
	private String gLink;
	
	/** 등록자(사용자) */
	private String regId;
	
	/** 등록일 */
	private String regDt;

	public MyGood() {}

	public MyGood(int gNo, String pNum, String gLink, String regId, String regDt) {
		super();
		this.gNo = gNo;
		this.pNum = pNum;
		this.gLink = gLink;
		this.regId = regId;
		this.regDt = regDt;
	}

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getgLink() {
		return gLink;
	}

	public void setgLink(String gLink) {
		this.gLink = gLink;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gLink == null) ? 0 : gLink.hashCode());
		result = prime * result + gNo;
		result = prime * result + ((pNum == null) ? 0 : pNum.hashCode());
		result = prime * result + ((regDt == null) ? 0 : regDt.hashCode());
		result = prime * result + ((regId == null) ? 0 : regId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyGood other = (MyGood) obj;
		if (gLink == null) {
			if (other.gLink != null)
				return false;
		} else if (!gLink.equals(other.gLink))
			return false;
		if (gNo != other.gNo)
			return false;
		if (pNum == null) {
			if (other.pNum != null)
				return false;
		} else if (!pNum.equals(other.pNum))
			return false;
		if (regDt == null) {
			if (other.regDt != null)
				return false;
		} else if (!regDt.equals(other.regDt))
			return false;
		if (regId == null) {
			if (other.regId != null)
				return false;
		} else if (!regId.equals(other.regId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyGood [gNo=" + gNo + ", pNum=" + pNum + ", gLink=" + gLink + ", regId=" + regId + ", regDt=" + regDt
				+ "]";
	}
	
	
	
	
	
}
