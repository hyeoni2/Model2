package survey.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import member.model.dto.MemberDTO;
import oracle.net.aso.r;
import sungjuk.model.dto.SungjukDTO;
import survey.model.dto.SurveyDTO;
import survey.model.dto.Survey_answerDTO;

public class SurveyDAO {
	
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
	public ArrayList<SurveyDTO> getSelectAll(){
		
		getConn();
		ArrayList<SurveyDTO> list = new ArrayList<>();
		try {
			String sql  = "";
				   sql += " select survey.*";
				   sql += " ,(select count(*) from survey_answer where survey.no = survey_answer.no)count";
				   sql += "  from survey";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SurveyDTO dto = new SurveyDTO();
				dto.setNo(rs.getInt("no"));
				dto.setQuestion(rs.getString("question"));
				dto.setAns1(rs.getString("ans1"));
				dto.setAns2(rs.getString("ans2"));
				dto.setAns3(rs.getString("ans3"));
				dto.setAns4(rs.getString("ans4"));
				dto.setStatus(rs.getString("status"));
				dto.setStart_date(rs.getDate("start_date"));
				dto.setRegi_date(rs.getDate("regi_date"));
				dto.setLast_date(rs.getDate("last_date"));
				dto.setCount(rs.getInt("count"));
				list.add(dto);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose();
		}

		return list;
	}
	
	//추가
		public int setInsert(SurveyDTO dto){
			getConn();
			int result = 0;
			try {
				
				String sql  = "";
					   sql += " insert into survey values(";
					   sql += " (select nvl(max(no),0)+1 from survey), ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?";
					   sql += " )";
					   
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getQuestion());
				pstmt.setString(2, dto.getAns1());
				pstmt.setString(3, dto.getAns2());
				pstmt.setString(4, dto.getAns3());
				pstmt.setString(5, dto.getAns4());
				pstmt.setString(6, dto.getStatus());
				pstmt.setDate(7, dto.getStart_date());				
				pstmt.setDate(8, dto.getLast_date());				
				pstmt.setString(9, dto.getReal_answer());				
				result = pstmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return result;
		}
		
		//뷰
		public SurveyDTO getSelectOne(SurveyDTO dto){
			
			getConn();		
			try {
				String sql = "";
					   sql += " select survey.* ";
					   sql += " ,(select count(*) from survey_answer where survey.no = survey_answer.no)count ";
					   sql += " from survey where no = ?";
					
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					dto.setQuestion(rs.getString("question"));
					dto.setAns1(rs.getString("ans1"));
					dto.setAns2(rs.getString("ans2"));
					dto.setAns3(rs.getString("ans3"));
					dto.setAns4(rs.getString("ans4"));
					dto.setStatus(rs.getString("status"));
					dto.setStart_date(rs.getDate("start_date"));
					dto.setLast_date(rs.getDate("last_date"));
					dto.setRegi_date(rs.getDate("regi_date"));
					dto.setCount(rs.getInt("count"));
				}		
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return dto;
		}
		
		//설문조사 체크
		public int checkedAnswer(Survey_answerDTO dto) {
			getConn();		
			int result = 0;
			try {
				String sql = "";
					   sql += " insert into survey_answer values";
					   sql += "(";
					   sql += "  seq_survey_answer.nextval ";
					   sql += "  ,? ";
					   sql += "  ,? ";
					   sql += "  ,sysdate ";
					   sql += ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				pstmt.setInt(2, dto.getAnswer());
				result = pstmt.executeUpdate();
					
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				getConnClose();
			}
			
			return result;
			}
		
		
		
		//삭제
		public int delete(SurveyDTO dto){
			getConn();
			int result = 0;
			try {
				String sql  = "delete survey where no = ?";
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
