package kr.co.redbull.user.service;

import kr.co.redbull.user.service.Level;
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
	private Level lvl;       // 레벨
	private int intlvl;       // 레벨
	private int upoint;    // 포인트
	private int inum;      // 이미지번호
	private String regdt;  // 등록일(SYSDATE)
	// totalCnt, num 상속받음
	
	/**기본생성자*/
	public User() {

	}
	
	
	public User(String rid, String passwd, String uname, String birth, String phone, int postnum, String address,
			String detadd, int inum) {
		super();
		this.rid = rid;
		this.passwd = passwd;
		this.uname = uname;
		this.birth = birth;
		this.phone = phone;
		this.postnum = postnum;
		this.address = address;
		this.detadd = detadd;
		this.inum = inum;
	}
	
	/**멤버변수 전체를 매개변수로 하는 생성자: intlvl 빼고*/
	public User(String rid, String passwd, String uname, String birth, String phone, int postnum, String address,
			String detadd, Level lvl, int upoint, int inum, String regdt) {
		super();
		this.rid = rid;
		this.passwd = passwd;
		this.uname = uname;
		this.birth = birth;
		this.phone = phone;
		this.postnum = postnum;
		this.address = address;
		this.detadd = detadd;
		this.lvl = lvl;
		this.upoint = upoint;
		this.inum = inum;
		this.regdt = regdt;
	}

	
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

	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	public int getintlvl() {
		return intlvl;
	}

	public void setintlvl(int intlvl) {
		this.intlvl = intlvl;
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

	/**toString() 재정의*/
	@Override
	public String toString() {
		return "User [rid=" + rid + ", passwd=" + passwd + ", uname=" + uname + ", birth=" + birth + ", phone=" + phone
				+ ", postnum=" + postnum + ", address=" + address + ", detadd=" + detadd + ", lvl=" + lvl + ", intlvl="
				+ intlvl + ", upoint=" + upoint + ", inum=" + inum + ", regdt=" + regdt + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((detadd == null) ? 0 : detadd.hashCode());
		result = prime * result + intlvl;
		result = prime * result + inum;
		result = prime * result + ((lvl == null) ? 0 : lvl.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + postnum;
		result = prime * result + ((regdt == null) ? 0 : regdt.hashCode());
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + upoint;
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (detadd == null) {
			if (other.detadd != null)
				return false;
		} else if (!detadd.equals(other.detadd))
			return false;
		if (intlvl != other.intlvl)
			return false;
		if (inum != other.inum)
			return false;
		if (lvl != other.lvl)
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (postnum != other.postnum)
			return false;
		if (regdt == null) {
			if (other.regdt != null)
				return false;
		} else if (!regdt.equals(other.regdt))
			return false;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (upoint != other.upoint)
			return false;
		return true;
	}
	
	/**등업 NextLevel*/
	public void upgradeLevel(){
		
		Level nextLevel = this.lvl.getNextLevel();
		
		if(null == nextLevel) {
			
			throw new IllegalStateException(this.lvl + "은 업그레이드가 불가능합니다.");
		}
		else {
			
			this.inum += 1;
			this.lvl = nextLevel;
		}
		
	}


}//--class
