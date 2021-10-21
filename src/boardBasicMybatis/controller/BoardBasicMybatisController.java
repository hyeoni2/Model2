package boardBasicMybatis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardBasic.model.dto.BoardDTO;
import boardBasicMybatis.model.dao.BoardBasicMybatisDAO;
import boardBasicMybatis.model.dto.BoardBasicMybatisDTO;

@WebServlet("/boardBasicMybatis_servlet/*")
public class BoardBasicMybatisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String url = request.getRequestURL().toString();
		String path = request.getContextPath();
		
		String page = "";


		if(url.contains("List.do")) {
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			List<BoardBasicMybatisDTO> list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/boardBasicMybatis/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("View.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/boardBasicMybatis/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
	
			
		}else if(url.contains("Chuga.do")) {
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			
			String tmp = request.getParameter("no");
			
			if(tmp != null) {
				int no = Integer.parseInt(tmp);
				System.out.println(no);
				dto.setNo(no);
				
				dto = dao.getSelectOne(no);
			}
			
			request.setAttribute("dto", dto);
			
			
			page = "/WEB-INF/boardBasicMybatis/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("ChugaProc.do")) {
			
			String tmp = request.getParameter("no");
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			

			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			int num = dao.getMaxNum() + 1;
			int ref = dao.getMaxRef() + 1;
			int re_step = 1;
			int re_level = 1;
			int hit = 0;
			
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNum(num);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setRef(ref);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);
			dto.setHit(hit);
			
			
			int result = dao.setInsert(dto);

			if(result > 0) {
				page = "${path}/boardBasicMybatis_servlet/List.do";
			}else {				
				page = "${path}/boardBasicMybatis_servlet/Error.do?code=chugaEr";
			}

			response.sendRedirect(page);
				
		}else if(url.contains("Sujung.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/boardBasicMybatis/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("SujungProc.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String passwd = request.getParameter("passwd");
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			
			int result = dao.setUpdate(dto);
			if(result > 0) {
				page = "${path}/boardBasicMybatis_servlet/List.do";
			}else {				
				page = "${path}/boardBasicMybatis_servlet/Error.do?code=sujungEr";
			}

			response.sendRedirect(page);
		}else if(url.contains("Sakjae.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/boardBasicMybatis/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("SakjaeProc.do")) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			String passwd = request.getParameter("passwd");
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			dto.setPasswd(passwd);
			
			int result = dao.setDelete(dto);
			if(result > 0) {
				page = "${path}/boardBasicMybatis_servlet/List.do";
			}else {				
				page = "${path}/boardBasicMybatis_servlet/Error.do?code=sakEr";
			}

			response.sendRedirect(page);
		}else if(url.contains("Error.do")) {
		
			String code = request.getParameter("code");
			
			page = "/WEB-INF/board/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}

}
}	
