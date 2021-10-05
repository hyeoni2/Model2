package memo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import memo.model.dto.MemoDTO;

public class memoDAO {

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
	
	public ArrayList<MemoDTO> getSelectAll(){
	
		getConn();
		
		ArrayList<MemoDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select * from memo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
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
	public int setInsert(MemoDTO dto){
		getConn();
		int result = 0;
		try {
			
			String sql  = "";
				   sql += " insert into memo values(";
				   sql += " seq_memo.nextval, ?, ?, sysdate";
				   sql += " )";
				   
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());

			
			 result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return result;
	}
	
	//뷰
	public MemoDTO getSelectOne(MemoDTO dto){
		
		getConn();
		
		try {
			String sql = "select * from memo where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
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
		public int setUpdate(MemoDTO dto){
			int result = 0;
			getConn();
			try {
				
				String sql  = "";
					   sql += " update memo set ";
					   sql += " writer = ?, content = ? ";
					   sql += " where no = ?";
					   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getNo());
				result = pstmt.executeUpdate();
				 
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			
			return result;
		}

		//삭제
		public int delete(MemoDTO dto){
			getConn();
			int result = 0;
			try {
				
				String sql  = "";
				sql += " delete memo ";
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
