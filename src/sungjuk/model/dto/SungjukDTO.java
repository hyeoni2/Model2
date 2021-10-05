package sungjuk.model.dto;

import java.sql.Date;

public class SungjukDTO {
	
	private int no;
	private String name;
	private String sihum_name;
	private int kor;
	private int eng;
	private int mat;
	private int sci;
	private int his;
	private int tot;
	private double avg;
	private String grade;
	private Date regi_date;
	private String id;
	
	
	public SungjukDTO() {
		// TODO Auto-generated constructor stub
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSihum_name() {
		return sihum_name;
	}


	public void setSihum_name(String sihum_name) {
		this.sihum_name = sihum_name;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getMat() {
		return mat;
	}


	public void setMat(int mat) {
		this.mat = mat;
	}


	public int getSci() {
		return sci;
	}


	public void setSci(int sci) {
		this.sci = sci;
	}


	public int getHis() {
		return his;
	}


	public void setHis(int his) {
		this.his = his;
	}


	public int getTot() {
		return tot;
	}


	public void setTot(int tot) {
		this.tot = tot;
	}


	public double getAvg() {
		return avg;
	}


	public void setAvg(double avg) {
		this.avg = avg;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public Date getRegi_date() {
		return regi_date;
	}


	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
