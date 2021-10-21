package memo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.model.dao.memoDAO;
import memo.model.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {

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
		
		if(url.contains("List.do")) {
			
			memoDAO dao = new memoDAO();
			List<MemoDTO> list = new ArrayList<>();
			
			list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/memo/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		}else if(url.contains("View.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			memoDAO dao = new memoDAO();
			
			MemoDTO dto = new MemoDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			List<MemoDTO> list = new ArrayList<>();
			list.add(dto);
			request.setAttribute("list", list);		
			
			
			String page = "/WEB-INF/memo/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("Chuga.do")) {
		
			String page = "/WEB-INF/memo/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("ChugaProc.do")) {
			
			
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			memoDAO dao = new memoDAO();
			
			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);
			
			int result = dao.setInsert(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "메모가 등록되었습니다.";
				link = path + "/memo_servlet/List.do";	
			}else {
				msg = "메모를 등록하는 과정에서 오류가 발생되었습니다.";
				link = path + "/memo_servlet/Chuga.do";	
			}
			System.out.println(msg);
			System.out.println(link);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
			
			
		}else if(url.contains("Sujung.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			memoDAO dao = new memoDAO();
			
			MemoDTO dto = new MemoDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			List<MemoDTO> list = new ArrayList<>();
			list.add(dto);
			request.setAttribute("list", list);		
			
			String page = "/WEB-INF/memo/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("SujungProc.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			memoDAO dao = new memoDAO();
			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);
			dto.setNo(no);
			
			int result = dao.setUpdate(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "메모가 수정되었습니다.";
				link = path + "/memo_servlet/View.do?no="+no;	
			}else {
				msg = "메모를 수정하는 과정에서 오류가 발생되었습니다.";
				link = path + "/memo_servlet/Sujung.do?no="+no;	
			}
			System.out.println(msg);
			System.out.println(link);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
			

		}else if(url.contains("Sakjae.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			memoDAO dao = new memoDAO();
			
			MemoDTO dto = new MemoDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			List<MemoDTO> list = new ArrayList<>();
			list.add(dto);
			request.setAttribute("list", list);	
			
			String page = "/WEB-INF/memo/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("SakjaeProc.do")) {	
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
		
			
			memoDAO dao = new memoDAO();
			MemoDTO dto = new MemoDTO();
			dto.setNo(no);
			
			int result = dao.delete(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "메모가 삭제되었습니다.";
				link = path + "/memo_servlet/List.do";	
			}else {
				msg = "메모를 삭제하는 과정에서 오류가 발생되었습니다.";
				link = path + "/memo_servlet/Sakjae.do?no="+no;	
			}
			System.out.println(msg);
			System.out.println(link);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
			
		}else {
			System.out.println("memo");
		}
	}	

}
