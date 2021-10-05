package member.model.dto;

import java.sql.Date;

public class MemberDTO {
	
	private int no;
	private String id;
	private String passwd;
	private String name;
	private String phone;
	private String addr;
	private Date regi_date;
	
	private String juso1;
	private String juso2;
	private String juso3;
	private String juso4;
	
	private String ability;
	
	public String getAbility() {
		return ability;
	}
	
	public void setAbility(String ability) {
		this.ability = ability;
	}

	
	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	
	/**////////////////////////////////////////////////////**/
	public String getJuso1() {
		return juso1;
	}

	public void setJuso1(String juso1) {
		this.juso1 = juso1;
	}

	public String getJuso2() {
		return juso2;
	}

	public void setJuso2(String juso2) {
		this.juso2 = juso2;
	}

	public String getJuso3() {
		return juso3;
	}

	public void setJuso3(String juso3) {
		this.juso3 = juso3;
	}

	public String getJuso4() {
		return juso4;
	}

	public void setJuso4(String juso4) {
		this.juso4 = juso4;
	}

	
	
	
}