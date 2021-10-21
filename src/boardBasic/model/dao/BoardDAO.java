package boardBasic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boardBasic.model.dto.BoardDTO;
import config.DB;
import member.model.dto.MemberDTO;

public class BoardDAO {
	
	
	
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
	
	/*******************************************************************************/
	
		//추가
		public int setParentInsert(BoardDTO dto){
			getConn();
			int result = 0;
			try {
				String sql  = "";
					   sql += " insert into boardBasic values(";
					   sql += "  seq_boardBasic.nextval";
					   sql += " ,(select nvl(max(ref), 0) + 1 from boardBasic)";
					   sql += " ,?";//작성자
					   sql += " ,?";//주제
					   sql += " ,?";//컨텐츠
					   sql += " ,?";//이메일
					   sql += " ,?";//패스워드
					   sql += " ,(select nvl(max(ref), 0) + 1 from boardBasic)";//ref
					   sql += " ,1";//re_step
					   sql += " ,1";
					   sql += " ,0 ";
					   sql += " ,sysdate ";
					   sql += " )";
					   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getSubject());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getEmail());
				pstmt.setString(5, dto.getPasswd());				
				result = pstmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return result;
		}
		
		//부모리스트
		public ArrayList<BoardDTO> getParentAll(){
			getConn();
			ArrayList<BoardDTO> list = new ArrayList<>();
			try {
				String sql  = "";
					   sql += "select * from boardBasic";
					   sql += " order by ref desc, re_level asc";
		
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNo(rs.getInt("no"));
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setEmail(rs.getString("email"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));
					dto.setHit(rs.getInt("hit"));
					dto.setRegi_date(rs.getDate("regi_date"));
					list.add(dto);
				}
	
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return list;
		}


		//부모글 하나만 보기
		public  BoardDTO getOne(BoardDTO dto){
			
			getConn();
			try {
				String sql  = "";
				sql += " select * from boardBasic where no = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					dto.setNo(rs.getInt("no"));
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setEmail(rs.getString("email"));
					dto.setPasswd(rs.getString("passwd"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));
					dto.setHit(rs.getInt("hit"));
					dto.setRegi_date(rs.getDate("regi_date"));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return dto;
		}
		
		//댓글추가
		public int setChildInsert(BoardDTO dto){
			getConn();
			int result = 0;
			try {
				String sql  = "";
					   sql += " insert into boardBasic values(";
					   sql += "  seq_boardBasic.nextval"; //no
					   sql += " ,(select ref from boardBasic where no = ?)"; //num
					   sql += " ,?";//작성자
					   sql += " ,?";//주제
					   sql += " ,?";//컨텐츠
					   sql += " ,?";//이메일
					   sql += " ,?";//패스워드
					   sql += " ,?";//ref
					   sql += " ,?";//re_step				
					   sql += " ,? ";//re_level
					   sql += " ,0 ";
					   sql += " ,sysdate ";
					   sql += " )";
					   
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				pstmt.setString(2, dto.getWriter());
				pstmt.setString(3, dto.getSubject());
				pstmt.setString(4, dto.getContent());
				pstmt.setString(5, dto.getEmail());
				pstmt.setString(6, dto.getPasswd());	
				pstmt.setInt(7, dto.getRef());
				pstmt.setInt(8, dto.getRe_step());
				pstmt.setInt(9, dto.getRe_level());					
				
				result = pstmt.executeUpdate();
				
				if(result > 0) {
					System.out.println("성공?");
					conn.commit();
				}else {
					conn.rollback();
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return result;
		}
		
		//re_levelupdate
		public void setParentUpdate(BoardDTO dto){
			getConn(); 
			try {
				//부모의 no가 아닌것들 중에 부모의 레벨보다 크고 부모와 ref가 같다면 +1을 해라
				String  sql  = "";
						sql += "  update boardBasic set re_level = (re_level + 1)";
						sql += "  where (ref = ? and re_level > ?)";
						
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getRef());
				pstmt.setInt(2, dto.getRe_level());
				pstmt.executeUpdate();
				
				System.out.println("오류?");
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}

		}
}
