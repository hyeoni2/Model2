package boardBasic.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardBasic.model.dao.BoardDAO;
import boardBasic.model.dto.BoardDTO;
import boardBasicMybatis.model.dao.BoardBasicMybatisDAO;

@WebServlet("/board_servlet/*")
public class Board_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "";
		
		if(url.contains("List.do")) {
			
			BoardDAO dao = new BoardDAO();
			
			ArrayList<BoardDTO> list = dao.getParentAll();
			request.setAttribute("list", list);
			
			page = "/WEB-INF/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("View.do")) {

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();
			dto.setNo(no);
			
			dto = dao.getOne(dto);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("Chuga.do")) {

			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();

			String tmp = request.getParameter("no");
		
			if(tmp != null) {
				int no = Integer.parseInt(tmp);
				System.out.println(no);
				dto.setNo(no);
				
				dto = dao.getOne(dto);
			}
			
			request.setAttribute("dto", dto);


			page = "/WEB-INF/board/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("ChugaProc.do")) {
			
			String tmp = request.getParameter("no");
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();

			int result = 0;
			
			if(tmp == null || tmp.trim().equals("0")) {
				dto.setWriter(writer);
				dto.setSubject(subject);
				dto.setContent(content);
				dto.setEmail(email);
				dto.setPasswd(passwd);
				result = dao.setParentInsert(dto);	
			}else {
				
				BoardDTO origin = new BoardDTO();
				int no = Integer.parseInt(tmp);
				origin.setNo(no);
				
				origin = dao.getOne(origin);
				
				int ref = origin.getRef(); //1
				int re_step = origin.getRe_step(); //1
				int re_level = origin.getRe_level();//1
				origin.setRef(ref);
				origin.setRe_level(re_level);
				dao.setParentUpdate(origin);
				
				System.out.println(ref);
				System.out.println(re_step);
				System.out.println(re_level);

				re_step = re_step + 1;
				re_level = re_level + 1;
				
			
				BoardDTO dto2 = new BoardDTO();
				dto2.setNo(no);
				dto2.setWriter(writer);
				dto2.setSubject(subject);
				dto2.setContent(content);
				dto2.setEmail(email);
				dto2.setPasswd(passwd);
				dto2.setRef(ref);
				dto2.setRe_step(re_step);
				dto2.setRe_level(re_level);
				result = dao.setChildInsert(dto2);
			}
			
			
			if(result > 0) {
				page = "${path}/board_servlet/List.do";
			}else {				
				page = "${path}/board_servlet/Error.do?code=chugaEr";
			}
			
			response.sendRedirect(page);
			
		}else if(url.contains("Sujung.do")) {
		
		}else if(url.contains("Sakjae.do")) {
		
		}else if(url.contains("Error.do")) {
		
			String code = request.getParameter("code");
			
			page = "/WEB-INF/board/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

}
