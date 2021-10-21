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

public class Survey_answerDAO {
	
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
	
		//설문조사 체크
		public int setInsert(Survey_answerDTO dto) {
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
		
}
