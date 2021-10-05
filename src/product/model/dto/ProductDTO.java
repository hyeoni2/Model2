package product.model.dto;

import java.io.File;
import java.sql.Date;

public class ProductDTO {
	
	private int no;
	private String name;
	private int price;
	private String description;
	private String product_img;
	private String product_img_original;
	


	private Date regi_date;
	
	public ProductDTO() {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	
	public String getProduct_img_original() {
		return product_img_original;
	}

	public void setProduct_img_original(String product_img_original) {
		this.product_img_original = product_img_original;
	}

	public Date getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	
	

	
	


}
