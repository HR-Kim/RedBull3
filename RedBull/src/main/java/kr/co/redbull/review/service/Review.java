package kr.co.redbull.review.service;

import kr.co.redbull.cmn.DTO;

public class Review extends DTO {
	private String rNum      ;//리뷰번호
	private String pNum      ;//상품번호
	private String score     ;//별점
	private String contents  ;//내용
	private String regId     ;//등록자
	private String regDt     ;//등록일
	private String modId     ;//수정자
	private String modDt     ;//수정일
	
	public Review() {}

	public Review(String rNum, String pNum, String score, String contents, String regId, String regDt, String modId,
			String modDt) {
		super();
		this.rNum = rNum;
		this.pNum = pNum;
		this.score = score;
		this.contents = contents;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getrNum() {
		return rNum;
	}

	public void setrNum(String rNum) {
		this.rNum = rNum;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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
		return "Review [rNum=" + rNum + ", pNum=" + pNum + ", score=" + score + ", contents=" + contents + ", regId="
				+ regId + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()="
				+ super.toString() + "]";
	}	
}
