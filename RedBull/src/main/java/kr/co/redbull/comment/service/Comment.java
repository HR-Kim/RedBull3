package kr.co.redbull.comment.service;

import kr.co.redbull.cmn.DTO;

public class Comment extends DTO {

	/**댓글계층*/
	private int cLevel		; 
	/**댓글번호*/
	private int cNum    	; 
	/**테이블번호*/
	private int tNum    	; 
	/**참조번호*/
	private int refNum 	  	; 
	/**내용*/
	private String contents ; 
	/**상위댓글번호*/
	private int hcNum    	; 
	/**등록자*/
	private String regId    ; 
	/**등록일*/
	private String regDt    ; 
	/**수정자*/
	private String modId    ; 
	/**수정일*/
	private String modDt    ; 
	
	public Comment() {}
	
	
	public Comment(int cLevel, int cNum, int tNum, int refNum, String contents, int hcNum, String regId, String regDt,
			String modId, String modDt) {
		super();
		this.cLevel = cLevel;
		this.cNum = cNum;
		this.tNum = tNum;
		this.refNum = refNum;
		this.contents = contents;
		this.hcNum = hcNum;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public int getcLevel() {
		return cLevel;
	}

	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}

	public int getcNum() {
		return cNum;
	}

	public void setcNum(int cNum) {
		this.cNum = cNum;
	}

	public int gettNum() {
		return tNum;
	}

	public void settNum(int tNum) {
		this.tNum = tNum;
	}

	public int getRefNum() {
		return refNum;
	}

	public void setRefNum(int refNum) {
		this.refNum = refNum;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHcNum() {
		return hcNum;
	}

	public void setHcNum(int hcNum) {
		this.hcNum = hcNum;
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


	@Override
	public String toString() {
		return "Comment [cLevel=" + cLevel + ", cNum=" + cNum + ", tNum=" + tNum + ", refNum=" + refNum + ", contents="
				+ contents + ", hcNum=" + hcNum + ", regId=" + regId + ", regDt=" + regDt + ", modId=" + modId
				+ ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}

}
