package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import product.model.dto.ProductDTO;
import shop.model.dto.CartDTO;

public class CartDAO {
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
	
	public void getConnClose() {
		
		DB.dbConnClose(rs, pstmt, conn);
	
	}
	
	//******************************************************//
	
	//추가
		public int setInsert(CartDTO dto){
			getConn();
			int result = 0;
			try {
				
				String sql  = "";
					   sql += " insert into cart values(";
					   sql += " seq_cart.nextval, ?, ?, ?, sysdate";
					   sql += " )";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getMemberNo());
				pstmt.setInt(2, dto.getNo());
				pstmt.setInt(3, dto.getAmount());	
				
				result = pstmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return result;
		}

		//이름에 따라
		public ArrayList<CartDTO> getSelectName(CartDTO dto2){
			getConn();
			
			ArrayList<CartDTO> list = new ArrayList<>();
			try {
				
				String sql  = "";
				sql += " select  ";
				sql += "  c.no no, c.memberNo memberNo, c.productNo productNo, c.amount amount, c.regi_date regi_date";
				sql += " ,p.name product_name, p.product_img product_img, p.price price ";
				sql += " ,m.name memberName";
				sql += " from cart c, product p, member m";
				sql += " where c.memberNo = m.no";
				sql += " and p.no = c.productNo ";
				sql += " and c.memberNo = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto2.getMemberNo());
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					CartDTO dto = new CartDTO();
					
					dto.setNo(rs.getInt("no"));
					dto.setMemberNo(rs.getInt("memberNo"));
					dto.setProduct_img(rs.getString("productNo"));
					dto.setAmount(rs.getInt("amount"));
					dto.setRegi_date(rs.getDate("regi_date"));
		
					dto.setProduct_name(rs.getString("product_name"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setPrice(rs.getInt("price"));
					
					dto.setMemberName(rs.getString("memberName"));
					list.add(dto);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return list;	
	
		}

		//이름에 따라
		public ArrayList<CartDTO> getSelectAll(){
			getConn();
			
			ArrayList<CartDTO> list = new ArrayList<>();
			try {
				
				String sql  = "";
				sql += " select  ";
				sql += "  c.no no, c.memberNo memberNo, c.amount amount";
				sql += " ,p.name product_name, p.price price ";
				sql += " ,m.name memberName";
				sql += " from cart c, product p, member m";
				sql += " where c.memberNo = m.no";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					CartDTO dto = new CartDTO();		
					dto.setNo(rs.getInt("no"));
					dto.setMemberNo(rs.getInt("memberNo"));
					dto.setAmount(rs.getInt("amount"));

					dto.setProduct_name(rs.getString("product_name"));
					dto.setPrice(rs.getInt("price"));
					
					dto.setMemberName(rs.getString("memberName"));
					list.add(dto);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return list;	
			
		}
		
		public int delete(CartDTO dto) {
			getConn();
			
			int result = 0;
			try {
				
				String sql  = "";
				       sql  = "delete cart where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				
				result = pstmt.executeUpdate();
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return result;	
		}
		

}
