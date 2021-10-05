package guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import guestbook.model.dto.GuestbookDTO;

public class GuestbookDAO {
	
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		
		public void getConn() {
			
			try {
				conn = DB.dbConn();
				System.out.println("--오라클 접속 성공--");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("--오라클 접속 실패--");
			}
			
		}
	
		
		public void getConnClose() {
			
			DB.dbConnClose(rs, pstmt, conn);
		}
		
		
		
		public ArrayList<GuestbookDTO> getSelectAll(){
			
			getConn();
			ArrayList<GuestbookDTO> list = new ArrayList<>();
			
			try {
				
				String sql = "select * from guestbook";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					GuestbookDTO dto = new GuestbookDTO();
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					dto.setEmail(rs.getString("email"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setContent(rs.getString("content"));
					dto.setRegi_date(rs.getDate("regi_date"));
					list.add(dto);
				}
						
								
			} catch (Exception e) {
				e.printStackTrace();				
			}finally {
				
			}
			return list;	
		}
		
		public GuestbookDTO getSelectOne(GuestbookDTO dto){
			
			getConn();
			
			try {
				String sql = "select * from guestbook where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					dto.setEmail(rs.getString("email"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setContent(rs.getString("content"));
					dto.setRegi_date(rs.getDate("regi_date"));
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();				
			}finally {
				
			}
			return dto;	
		}
		
		public int setInsert(GuestbookDTO dto){
			
			getConn();
			int result = 0;
			
			try {
				String sql  = "";
					   sql += " insert into guestbook values(";
					   sql += " seq_guestbook.nextval, ?, ?, ?, ?, sysdate";
					   sql += "  )";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getEmail());
				pstmt.setString(3, dto.getPasswd());
				pstmt.setString(4, dto.getContent());
				result = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();				
			}finally {
				
			}
			return result;	
		}
		
		
		public int setUpdate(GuestbookDTO dto){
			
			getConn();
			int result = 0;
			
			try {
				String sql  = "";
				sql += " update guestbook set ";
				sql += " name = ?, content = ? where (no = ? and passwd = ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getNo());
				pstmt.setString(4, dto.getPasswd());
				result = pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();				
			}finally {
				
			}
			return result;	
		}
		
	
		public int delete(GuestbookDTO dto){
			
			getConn();
			int result = 0;
			
			try {
				String sql  = "";
				sql += " delete guestbook";
				sql += " where (no = ? and passwd = ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				pstmt.setString(2, dto.getPasswd());
				result = pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();				
			}finally {
				
			}
			return result;	
		}
		
				


}
