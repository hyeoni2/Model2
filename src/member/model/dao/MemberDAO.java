package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import member.model.dto.MemberDTO;

public class MemberDAO {

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
	
	public ArrayList<MemberDTO> getSelectAll(){
	
		getConn();
		
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setAbility(rs.getString("ability"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddr(rs.getString("addr"));
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
	public int setInsert(MemberDTO dto){
		getConn();
		int result = 0;
		try {
			
			String sql  = "";
				   sql += " insert into member values(";
				   sql += " seq_member.nextval, ?, ?, ?, ?, ?, sysdate, ?";
				   sql += " )";
				   
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getAddr());
			pstmt.setString(6, dto.getAbility());
			
			 result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return result;
	}
	
	//뷰
	public MemberDTO getSelectOne(MemberDTO dto){
		
		getConn();
		
		try {
			String sql = "select * from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegi_date(rs.getDate("regi_date"));
				dto.setAbility(rs.getString("ability"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return dto;
	}
	
		//수정
		public int setUpdate(MemberDTO dto){
			int result = 0;
			getConn();
			try {
				
				String sql  = "";
					   sql += " update member set ";
					   sql += " name = ?, phone = ?, addr = ?, ability = ? ";
					   sql += " where no = ?";
					   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPhone());
				pstmt.setString(3, dto.getAddr());
				pstmt.setString(4, dto.getAbility());
				pstmt.setInt(5, dto.getNo());
				result = pstmt.executeUpdate();
				 
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			
			return result;
		}

		//삭제
		public int delete(MemberDTO dto){
			getConn();
			int result = 0;
			try {
				
				String sql  = "";
				sql += " delete member ";
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
		
		//로그인
		public MemberDTO getLogin(MemberDTO dto){
			getConn();
			MemberDTO dto2 = new MemberDTO();
			try {
				
				String sql  = " select no, id, name , ability from member ";
					   sql += " where (id = ? and passwd = ?)";
							   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPasswd());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto2.setNo(rs.getInt("no"));
					dto2.setId(rs.getString("id"));
					dto2.setName(rs.getString("name"));
					dto2.setAbility(rs.getString("ability"));
				}				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			
			return dto2;
		}
	
}
