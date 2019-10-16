package kr.co.redbull.board.service;

import kr.co.redbull.cmn.DTO;

public class Board extends DTO {

	private int bNum        ; //질문번호
	private String title    ; //제목
	private String contents ; //내용
	private int readCnt     ; //조회수
	private int tNum        ; //테이블번호
	private String category ; //카테고리
	private String regId    ; //등록자
	private String regDt    ; //등록일
	private String modId    ; //수정자
	private String modDt    ; //수정일
	
	public Board() {}

	public Board(int bNum, String title, String contents, int readCnt, int tNum, String category, String regId,
			String regDt, String modId, String modDt) {
		super();
		this.bNum = bNum;
		this.title = title;
		this.contents = contents;
		this.readCnt = readCnt;
		this.tNum = tNum;
		this.category = category;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int gettNum() {
		return tNum;
	}

	public void settNum(int tNum) {
		this.tNum = tNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		return "Board [bNum=" + bNum + ", title=" + title + ", contents=" + contents + ", readCnt=" + readCnt
				+ ", tNum=" + tNum + ", category=" + category + ", regId=" + regId + ", regDt=" + regDt + ", modId="
				+ modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}
	
	
}
