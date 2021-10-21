package survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import survey.model.dao.SurveyDAO;
import survey.model.dao.Survey_answerDAO;
import survey.model.dto.SurveyDTO;
import survey.model.dto.Survey_answerDTO;

@WebServlet("/survey_servlet/*")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		

		/*
		  int cookNo = 0;
		  if(session.getAttribute("cookNor") != null) {
			  cookNo = (int)session.getAttribute("cookNor");
		  }
	
		  if(cookNo == 0) {
			  
			  if(url.contains("memberLogin.do")) {				  
			  
			  }else if(url.contains("memberLoginProc.do")) {				  
			
			  }else if(url.contains("memberLogout.do")) {				  
		
			  }else {
			  
			  PrintWriter out = response.getWriter(); out.print("<script>");
			  out.print("alert('로그인 하신 후 이용하세요');");
			  out.print("location.href = '"+path+"';"); 
			  out.print("</script>");
			  return;
			  
			  }
			 } 
		 */ 
		String page = "";
		if(url.contains("List.do")) {
			
			SurveyDAO dao = new SurveyDAO();
			ArrayList<SurveyDTO> list = dao.getSelectAll();
			
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/survey/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		
		}else if(url.contains("View.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SurveyDAO dao = new SurveyDAO();
			
			Survey_answerDAO answerdao = new Survey_answerDAO();
			
			Survey_answerDTO answerdto = new Survey_answerDTO();
			answerdto.setNo(no);
			
	
			
			SurveyDTO dto = new SurveyDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			ArrayList<SurveyDTO> list = new ArrayList<>();
			list.add(dto);

			request.setAttribute("list", list);
			
					
			page = "/WEB-INF/survey/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("ViewProc.do")) {	
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			tmp = request.getParameter("input_answer");
			int answer = Integer.parseInt(tmp);

			Survey_answerDTO dto = new Survey_answerDTO();
			dto.setNo(no);
			dto.setAnswer(answer);
			
			Survey_answerDAO dao = new Survey_answerDAO();
			int result = dao.setInsert(dto);
			
			if(result > 0) {
				page = "${path}/survey_servlet/List.do";
			}else {
				page = "${path}/survey_servlet/Error.do?code=answerEr";
			}
			
			response.sendRedirect(page);

			
		}else if(url.contains("Chuga.do")) {
			
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int min = cal.get(Calendar.MINUTE);
			int sec = cal.get(Calendar.SECOND);
			
			
			Map<String, Integer> naljaMap = new HashMap<>();
			naljaMap.put("now_y", year);
			naljaMap.put("now_m", month);
			naljaMap.put("now_d", day);
						
			request.setAttribute("naljaMap", naljaMap);
			
			page = "/WEB-INF/survey/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("ChugaProc.do")) {
			
			String question = request.getParameter("question");
			String ans1 = request.getParameter("ans1");
			String ans2 = request.getParameter("ans2");
			String ans3 = request.getParameter("ans3");
			String ans4 = request.getParameter("ans4");
			String real_answer = request.getParameter("real_answer");
			
			String status = request.getParameter("status");

			String syear = request.getParameter("syear");
			String smonth = request.getParameter("smonth");
			String sday = request.getParameter("sday");

			String lyear = request.getParameter("lyear");
			String lmonth = request.getParameter("lmonth");
			String lday = request.getParameter("lday");
			
			Date start_date = Date.valueOf(syear+"-"+smonth+"-"+sday);
			Date last_date = Date.valueOf(lyear+"-"+lmonth+"-"+lday);
			
			SurveyDAO dao = new SurveyDAO();
			
			SurveyDTO dto = new SurveyDTO();
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
			
			int result = dao.setInsert(dto);
			
			if(result > 0) {
				page = "${path}/survey_servlet/List.do";
			}else {
				page = "${path}/survey_servlet/Error.do?code=chugaEr";
			}
			
			response.sendRedirect(page);
			
			
		}else if(url.contains("Sujung.do")) {
		
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SurveyDAO dao = new SurveyDAO();

			SurveyDTO dto = new SurveyDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			ArrayList<SurveyDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
					
			page = "/WEB-INF/survey/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("SujungProc.do")) {	
			
			
		}else if(url.contains("Sakjae.do")) {
		
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SurveyDAO dao = new SurveyDAO();

			SurveyDTO dto = new SurveyDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			ArrayList<SurveyDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
					
			page = "/WEB-INF/survey/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("SakjaeProc.do")) {	
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SurveyDAO dao = new SurveyDAO();
			
			SurveyDTO dto = new SurveyDTO();
			dto.setNo(no);
			
			int result = dao.delete(dto);
			
			if(result > 0) {
				page = "${path}/survey_servlet/List.do";
			}else {
				page = "${path}/survey_servlet/Error.do?code=sakjaeEr";
			}
			
			response.sendRedirect(page);
			
		}else if(url.contains("Munjae.do")) {
			
			SurveyDAO dao = new SurveyDAO();
			ArrayList<SurveyDTO> list = dao.getSelectAll();
			
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/survey/munjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("MunjaeProc.do")) {	
			
			int result = 0;
			String tmp = request.getParameter("count");
			int count = Integer.parseInt(tmp);
		
			String[] sub = new String[count];
			for(int i = 0; i < sub.length; i++) {
				 tmp = request.getParameter("no_"+i);
				int no = Integer.parseInt(tmp);
				
				String imsi = request.getParameter("dab_"+i);
				int dab = Integer.parseInt(imsi);
				
				sub[i] = no + ","+ dab;
				
				Survey_answerDAO dao = new Survey_answerDAO();
				Survey_answerDTO dto = new Survey_answerDTO();
				dto.setNo(no);
				dto.setAnswer(dab);
				result = dao.setInsert(dto);			
		}
			if(result > 0) {
				page = "${path}/survey_servlet/List.do";
			}else {
				page = "${path}/survey_servlet/Error.do?code=chugaEr";
			}
			
			response.sendRedirect(page);
			
			
			
		}else if(url.contains("Error.do")) {
			
			String code = request.getParameter("code");
			
			request.setAttribute("code", code);
			
			page = "/WEB-INF/survey/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else{
			
			
		}
		  
	}

}
