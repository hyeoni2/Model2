package product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import product.model.dto.ProductDTO;

public class ProductDAO {

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	public void getConn() {
		
		try {
			conn = DB.dbConn();
			System.err.println("--- 오라클 접속 성공 ---");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("--- 오라클 접속 실패 ---");
		}
		
	}
	
	public void getClose() {
		
		DB.dbConnClose(rs, pstmt, conn);
	
	}
	
	//******************************************************//
	
	public ArrayList<ProductDTO> getSelectAll(){
	
		getConn();
		
		ArrayList<ProductDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select * from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_img_original(rs.getString("product_img_original"));
				dto.setRegi_date(rs.getDate("regi_date"));
				list.add(dto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return list;
	}
	
	//추가
	public int setInsert(ProductDTO dto){
		getConn();
		int result = 0;
		try {
			
			String sql  = "";
				   sql += " insert into Product values(";
				   sql += " seq_Product.nextval, ?, ?, ?, ?, ?, sysdate";
				   sql += " )";
				   
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getProduct_img());
			pstmt.setString(5, dto.getProduct_img_original());
			
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return result;
	}
	
	//뷰
	public ProductDTO getSelectOne(ProductDTO dto){
		
		getConn();
		
		try {
			String sql = "select * from product where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_img_original(rs.getString("product_img_original"));
				dto.setRegi_date(rs.getDate("regi_date"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return dto;
	}
	
		//수정
		public int setUpdate(ProductDTO dto){
			int result = 0;
			getConn();
			try {
				
				String sql  = "";
					   sql += " update product set ";
					   sql += " name = ?, price = ?, description = ?, product_img = ?, product_img_original = ? ";
					   sql += " where no = ?";
					   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getName());
				pstmt.setInt(2, dto.getPrice());
				pstmt.setString(3, dto.getDescription());
				pstmt.setString(4, dto.getProduct_img());
				pstmt.setString(5, dto.getProduct_img_original());			
				pstmt.setInt(6, dto.getNo());
				result = pstmt.executeUpdate();
				 
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			
			return result;
		}

		//삭제
		public int delete(product.model.dto.ProductDTO dto){
			getConn();
			int result = 0;
			try {
				
				String sql  = "";
				sql += " delete Product ";
				sql += " where no = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				result = pstmt.executeUpdate();
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			
			return result;
		}
	
}
