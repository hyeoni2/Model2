package sungjuk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sungjuk.model.dao.SungjukDAO;
import sungjuk.model.dto.SungjukDTO;

@WebServlet("/sungjuk_servlet/*")
public class SungjukController extends HttpServlet {
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
		
		HttpSession session = request.getSession();

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
		
		if(url.contains("List.do")) {
			
			SungjukDAO dao = new SungjukDAO();
			
			ArrayList<SungjukDTO> list = new ArrayList<>();
			list = dao.getSelectAll();
			
			System.out.println(list.size());
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/sungjuk/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		//뷰	
		}else if(url.contains("View.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO dto = new SungjukDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			ArrayList<SungjukDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/sungjuk/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("Chuga.do")) {
			
			String page = "/WEB-INF/sungjuk/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("ChugaProc.do")) {
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String sihum_name = request.getParameter("sihum_name");
			
			String tmp = "";
			tmp = request.getParameter("kor");;
			int  kor = Integer.parseInt(tmp);
			
			tmp = request.getParameter("eng");;
			int  eng = Integer.parseInt(tmp);
			
			tmp = request.getParameter("mat");;
			int  mat = Integer.parseInt(tmp);
			
			tmp = request.getParameter("sci");;
			int  sci = Integer.parseInt(tmp);
			
			tmp = request.getParameter("his");;
			int  his = Integer.parseInt(tmp);
			
			int tot = kor + eng + mat + sci + his;
			double avg = tot / 5.0;
			
			String grade = "가";
			if(avg >= 90) {
				grade = "수";
			}else if(avg >= 80) {
				grade = "우";
			}else if(avg >= 70) {
				grade = "미";
			}else if(avg >= 60) {
				grade = "양";
			}
			
			SungjukDTO dto = new SungjukDTO();
			dto.setId(id);
			dto.setName(name);
			dto.setSihum_name(sihum_name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			
			SungjukDAO dao = new SungjukDAO();
			int result = dao.setInsert(dto);
			
			String msg = "";
			String link = "";
			if(result > 0) {
				msg = "성적등록이 완료되었습니다.";
				link = path+"/sungjuk_servlet/List.do";
			}else {
				msg = "성적을 등록하는 과정에서 오류가 발생되었습니다.";
				link = path+"/sungjuk_servlet/Chuga.do";
			}
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("location.href = '"+link+"';");
			out.print("</script>");
					
		
		}else if(url.contains("Sujung.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO dto = new SungjukDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			ArrayList<SungjukDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/sungjuk/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("SujungProc.do")) {
			
			String name = request.getParameter("name");
			String sihum_name = request.getParameter("sihum_name");
			
			String tmp = "";
			tmp = request.getParameter("no");;
			int  no = Integer.parseInt(tmp);
			
			tmp = request.getParameter("kor");;
			int  kor = Integer.parseInt(tmp);
			
			tmp = request.getParameter("eng");;
			int  eng = Integer.parseInt(tmp);
			
			tmp = request.getParameter("mat");;
			int  mat = Integer.parseInt(tmp);
			
			tmp = request.getParameter("sci");;
			int  sci = Integer.parseInt(tmp);
			
			tmp = request.getParameter("his");;
			int  his = Integer.parseInt(tmp);
			
			int tot = kor + eng + mat + sci + his;
			double avg = tot / 5.0;
			
			String grade = "가";
			if(avg >= 90) {
				grade = "수";
			}else if(avg >= 80) {
				grade = "우";
			}else if(avg >= 70) {
				grade = "미";
			}else if(avg >= 60) {
				grade = "양";
			}
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO dto = new SungjukDTO();
			dto.setName(name);
			dto.setSihum_name(sihum_name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			dto.setNo(no);
			
			int result = dao.setUpdate(dto);
			
			String msg = "";
			String link = "";
			if(result > 0) {
				msg = "성적수정이 완료되었습니다.";
				link = path+"/sungjuk_servlet/View.do?no="+no;
			}else {
				msg = "성적을 수정하는 과정에서 오류가 발생되었습니다.";
				link = path+"/sungjuk_servlet/Sujung.do";
			}
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("location.href = '"+link+"';");
			out.print("</script>");
			
			
		}else if(url.contains("Sakjae.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO dto = new SungjukDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			ArrayList<SungjukDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/sungjuk/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("SakjaeProc.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			SungjukDAO dao = new SungjukDAO();
			SungjukDTO dto = new SungjukDTO();
			dto.setNo(no);
			
			int result = dao.delete(dto);
			String msg = "";
			String link = "";
			if(result > 0) {
				msg = "성적삭제가 완료되었습니다.";
				link = path+"/sungjuk_servlet/List.do";
			}else {
				msg = "성적을 삭제하는 과정에서 오류가 발생되었습니다.";
				link = path+"/sungjuk_servlet/Sakjae.do?no="+no;
			}
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("location.href = '"+link+"';");
			out.print("</script>");
			
		
		}else{
			System.out.println("sungjuk");
		}

}

//end
}