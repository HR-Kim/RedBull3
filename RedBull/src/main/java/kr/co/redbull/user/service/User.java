package kr.co.redbull.user.service;

import kr.co.redbull.cmn.DTO;

public class User extends DTO {
	
	/**멤버변수*/
	private String rid;    // 아이디(NN)
	private String passwd; // 비밀번호(NN)
	private String uname;  // 이름(NN)
	private String birth;  // 생년월일(NN)
	private String phone;  // 휴대폰번호(NN)
	private int postnum;   // 우편번호(NN)
	private String address;// 주소(NN)
	private String detadd; // 자세한주소
	private int lvl;       // 레벨
	private int upoint;    // 포인트
	private int inum;      // 이미지번호
	private String regdt;  // 등록일(SYSDATE)
	// totalCnt, num 상속받음
	
	
	/**setter/getter*/
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetadd() {
		return detadd;
	}
	public void setDetadd(String detadd) {
		this.detadd = detadd;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	
	/**toString 재정의*/
	@Override
	public String toString() {
		return "User [rid=" + rid + ", passwd=" + passwd + ", uname=" + uname + ", birth=" + birth + ", phone=" + phone
				+ ", postnum=" + postnum + ", address=" + address + ", detadd=" + detadd + ", lvl=" + lvl + ", upoint="
				+ upoint + ", inum=" + inum + ", regdt=" + regdt + ", toString()=" + super.toString() + "]";
	}
	

}//--class
