package shop.model.dto;

import java.sql.Date;

public class CartDTO {

	private int no;
	private int memberNo;
	private int productNo;
	private int amount;
	private Date regi_date;
	
	//필드에 없는
	private String product_img;
	private String product_name;
	private String memberName;
	private int price;
	
	
	

	
	public CartDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	
	
	
	//필드에 없는
	public String getProduct_img() {
		return product_img;
	}


	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	
}
