package guestbook.controller;

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
import javax.servlet.http.HttpSession;

import guestbook.model.dao.GuestbookDAO;
import guestbook.model.dto.GuestbookDTO;

@WebServlet("/guestbook_servlet/*")
public class GuestbookController extends HttpServlet {
	
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
		
		PrintWriter out = response.getWriter();
		
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
			  
			  out.print("<script>");
			  out.print("alert('로그인 하신 후 이용하세요');");
			  out.print("location.href = '"+path+"';"); 
			  out.print("</script>");
			  return;
			  
			  }
		  }
		
		
		//리스트
		if(url.contains("guestbookList.do")) {
			
			GuestbookDAO dao = new GuestbookDAO();
			List<GuestbookDTO> list = new ArrayList<>();
			list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/guestbook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		//뷰	
		}else if(url.contains("guestbookView.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			GuestbookDAO dao = new GuestbookDAO();
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			List<GuestbookDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/guestbook/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//추가
		}else if(url.contains("guestbookChuga.do")) {
			
			String page = "/WEB-INF/guestbook/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//추가설정
		}else if(url.contains("guestbookChugaProc.do")) {
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			String passwd = request.getParameter("passwd");			
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setName(name);
			dto.setEmail(email);
			dto.setContent(content);
			dto.setPasswd(passwd);
			
			GuestbookDAO dao = new GuestbookDAO();
			
			int result = dao.setInsert(dto);
			
			String msg = "";
			String link = "";
			if(result > 0) {
			/*	
				msg = "방명록을 등록하였습니다.";
			   link = "${path}/guestbook_servlet/guestbookList.do";
			*/
				response.sendRedirect(path + "/guestbook_servlet/guestbookList.do");
			}else {
			 /*
				msg = "방명록을 등록하는 과정에서 오류가 발생하였습니다..";
				link = "${path}/guestbook_servlet/guestbookChuga.do";				
			*/	
				response.sendRedirect(path + "/guestbook_servlet/guestbookError.do?error_code=errChu");
			}
			
			
			
			/*	
				out.print("<script>");
				out.print("alert('"+msg+"');");
				out.print("location.href='"+link+"';");
				out.print("</script>");
			*/
			
		//수정
		}else if(url.contains("guestbookSujung.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			GuestbookDAO dao = new GuestbookDAO();
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			List<GuestbookDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/guestbook/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//수정설정
		}else if(url.contains("guestbookSujungProc.do")) {
			
			String tmp = request.getParameter("no");
			int	no = Integer.parseInt(tmp);
			
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String passwd = request.getParameter("passwd");		
			
			GuestbookDAO dao = new GuestbookDAO();
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(no);
			dto.setName(name);
			dto.setContent(content);
			dto.setPasswd(passwd);

			int result = dao.setUpdate(dto);
			System.out.println(result);
			
			String msg = "";
			String link = "";
			if(result > 0) {
				/*
				msg = "방명록을 수정하였습니다.";
			   	link = "${path}/guestbook_servlet/guestbookView.do?no="+no;
				*/
				response.sendRedirect(path + "/guestbook_servlet/guestbookView.do?no="+no);
			}else {
				/*	
				msg = "방명록을 수정하는 과정에서 오류가 발생하였습니다.";
				link = "${path}/guestbook_servlet/guestbookSujung.do?no="+no;				
				*/
				response.sendRedirect(path + "/guestbook_servlet/guestbookError.do?error_code=errSue");
			}
			
	
		/*	
			out.print("<script>");
			out.print("alert('"+msg+"');");
			out.print("location.href='"+link+"';");
			out.print("</script>");
		*/
			
		//삭제	
		}else if(url.contains("guestbookSakjae.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			GuestbookDAO dao = new GuestbookDAO();
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			List<GuestbookDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			
			String page = "/WEB-INF/guestbook/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		//삭제설정	
		}else if(url.contains("guestbookSakjaeProc.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			GuestbookDAO dao = new GuestbookDAO();
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNo(no);
			

			int result = dao.delete(dto);
			System.out.println(result);
			
			String msg = "";
			String link = "";
			if(result > 0) {
				/*	
				msg = "방명록을 삭제하였습니다.";
			    link = "${path}/guestbook_servlet/guestbookList.do";
			   */
			   response.sendRedirect("${path}/guestbook_servlet/guestbookList.do");
			}else {
				/*
				msg = "방명록을 삭제하는 과정에서 오류가 발생하였습니다.";
				link = "${path}/guestbook_servlet/guestbookSakjae.do?no="+no;				
				*/
				response.sendRedirect(path + "/guestbook_servlet/guestbookError.do?error_code=errSak");
			}
			
			
			
			/*	
				out.print("<script>");
				out.print("alert('"+msg+"');");
				out.print("location.href='"+link+"';");
				out.print("</script>");
			*/
			
		}else if(url.contains("guestbookError.do.do")) {
			
			String error_code = request.getParameter("error_code");
			
			String memo = "";
			if(error_code.equals("errorChu")) {
				memo = "추가하는 과정에 오류가 발생했습니다.";
			}else if(error_code.equals("errorShu")) {
				memo = "수정하는 과정에 오류가 발생했습니다.";
			}else if(error_code.equals("errorSak")) {	
				memo = "삭제하는 과정에 오류가 발생했습니다.";
			}
			
			request.setAttribute("memo", memo);
			
			String page = "/WEB-INF/guestbook/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}	
		
		
		
	
		
		
		
	
	}

}
