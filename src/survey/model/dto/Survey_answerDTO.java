package survey.model.dto;

import java.sql.Date;

public class Survey_answerDTO {
	
	private int answer_no;
	private int no;
	private int answer;
	private Date regi_date;

	
	


	public Survey_answerDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public int getAnswer_no() {
		return answer_no;
	}

	public void setAnswer_no(int answer_no) {
		this.answer_no = answer_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public Date getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	


}
