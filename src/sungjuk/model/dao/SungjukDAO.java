package sungjuk.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import sungjuk.model.dto.SungjukDTO;

public class SungjukDAO {
	
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
	
	/**********************************************************************/
	
	public ArrayList<SungjukDTO> getSelectAll(){
		
		getConn();
		ArrayList<SungjukDTO> list = new ArrayList<>();
		try {
			String sql = "select * from sungjuk";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SungjukDTO dto = new SungjukDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setSihum_name(rs.getString("sihum_name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setSci(rs.getInt("sci"));
				dto.setHis(rs.getInt("his"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getDouble("avg"));
				dto.setGrade(rs.getString("grade"));
				dto.setRegi_date(rs.getDate("regi_date"));
				dto.setId(rs.getString("id"));
				list.add(dto);		
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose();
		}

		return list;
	}

	
	public SungjukDTO getSelectOne(SungjukDTO dto){
		
		getConn();		
		try {
			String sql = "select * from sungjuk where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setSihum_name(rs.getString("sihum_name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setSci(rs.getInt("sci"));
				dto.setHis(rs.getInt("his"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getInt("avg"));
				dto.setGrade(rs.getString("grade"));
				dto.setRegi_date(rs.getDate("regi_date"));
				dto.setId(rs.getString("id"));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose();
		}
		
		return dto;
	}

	//추가
	public int setInsert(SungjukDTO dto){
		getConn();
		int result = 0;
		try {
			String sql  = "";
				   sql += " insert into sungjuk values(";
				   sql += " seq_sungjuk.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?";
				   sql += " )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSihum_name());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHis());
			pstmt.setInt(8, dto.getTot());
			pstmt.setDouble(9, dto.getAvg());
			pstmt.setString(10, dto.getGrade());
			pstmt.setString(11, dto.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose();
		}
		
		return result;
	}

	//수정
	public int setUpdate(SungjukDTO dto){
		getConn();
		int result = 0;
		try {
			String sql  = "";
			sql += " update sungjuk set ";
			sql += "   name = ?";
			sql += " , sihum_name = ?";
			sql += " ,kor = ?";
			sql += " ,eng = ?";
			sql += " ,mat = ?";
			sql += " ,sci = ?";
			sql += " ,his = ?";
			sql += " ,tot = ?";
			sql += " ,avg = ?";
			sql += " ,grade = ?";
			sql += " where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSihum_name());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHis());
			pstmt.setInt(8, dto.getTot());
			pstmt.setDouble(9, dto.getAvg());
			pstmt.setString(10, dto.getGrade());
			pstmt.setInt(11, dto.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose();
		}
		
		return result;
	}

	//삭제
	public int delete(SungjukDTO dto){
		getConn();
		int result = 0;
		try {
			String sql  = "delete sungjuk where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose();
		}
		
		return result;
	}

}
