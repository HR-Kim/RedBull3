package kr.co.redbull.cmn;

public class DTO {
	
	/**locale*/
	private String lang;
	/** 총 글 수  */
	private int totalCnt; 
	/** 글 번호 */
	private int num; 
	
	
	public DTO(){}


	public int getTotalCnt() {
		return totalCnt;
	}


	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}

	
	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	@Override
	public String toString() {
		return "DTO [lang=" + lang + ", totalCnt=" + totalCnt + ", num=" + num + ", toString()=" + super.toString()
				+ "]";
	}

}
