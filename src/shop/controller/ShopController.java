package shop.controller;

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
import javax.websocket.Session;

import org.jfree.chart.axis.DateTickMarkPosition;

import product.model.dao.ProductDAO;
import product.model.dto.ProductDTO;
import shop.model.dao.CartDAO;
import shop.model.dto.CartDTO;

@WebServlet("/shop_servlet/*")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		
	

		if(url.contains("List.do") == true) {
			
			ProductDAO dao = new ProductDAO();
			
			ProductDTO dto = new ProductDTO();
			ArrayList<ProductDTO> list = dao.getSelectAll();

			request.setAttribute("list", list);

			String page = "/WEB-INF/shop/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("View.do") == true) {	
			
			String tmp =  request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			ProductDAO dao = new ProductDAO();
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			ArrayList<ProductDTO> list = new ArrayList<>();
			list.add(dto);

			request.setAttribute("list", list);
			
			String page = "/WEB-INF/shop/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}else if(url.contains("ViewProc.do") == true) {
			int cookno = 0;
			if(session.getAttribute("cookNor") != null) {
				cookno = (int)session.getAttribute("cookNor");
			}
			
			if(cookno == 0) {
				response.sendRedirect("${path}/shop_servlet/Error.do?code=login");
			}
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			tmp = request.getParameter("amount");
			int amount = Integer.parseInt(tmp);
			

			String page = "";
			
			System.out.println("no"+no);
			System.out.println("cookno"+cookno);
			System.out.println("amount"+amount);
			
			CartDTO dto = new CartDTO();
			dto.setNo(no);
			dto.setMemberNo(cookno);
			dto.setAmount(amount);
	
			CartDAO dao = new CartDAO();
			
			int result = dao.setInsert(dto);

			if(result == 0) {
				page = "${path}/shop_servlet/Error.do?code=chugaErr";
			}else {			
				page = "${path}/shop_servlet/Buy.do?no="+no;
			}
			
			response.sendRedirect(page);
			
			
		}else if(url.contains("Buy.do") == true) {	
			int cookno = 0;
			if(session.getAttribute("cookNor") != null) {
				cookno = (int)session.getAttribute("cookNor");
			}
			
			if(cookno == 0) {
				response.sendRedirect("${path}/shop_servlet/Error.do?code=login");
			}
			
			System.out.println(cookno);
			
			CartDTO dto = new CartDTO();
			dto.setMemberNo(cookno);

			CartDAO dao = new CartDAO();
			ArrayList<CartDTO> list = dao.getSelectName(dto);
			
			int sum = 0;
			for(int i = 0; i < list.size(); i++) {
				CartDTO dto2 = list.get(i);
				
				int price = dto2.getPrice();	
				int amount = dto2.getAmount();	
			
				sum += price * amount;					
			}
			
			
			System.out.println(sum);
			
			request.setAttribute("list", list);
			request.setAttribute("sum", sum);
			
			String page = "/WEB-INF/shop/buy.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("cartClearOne.do") == true) {		
			
			int cookno = 0;
			if(session.getAttribute("cookNor") != null) {
				cookno = (int)session.getAttribute("cookNor");
			}
			
			if(cookno == 0) {
				response.sendRedirect("${path}/shop_servlet/Error.do?code=login");
			}

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			CartDAO dao = new CartDAO();
			CartDTO dto = new CartDTO();
			dto.setNo(no);

			int result = dao.delete(dto);

			String page = "";
			if(result == 0) {
				page = "${path}/shop_servlet/Error.do?code=sakjaeErr";
			}else {			
				page = "${path}/shop_servlet/Buy.do?no="+cookno;
			}
			
			response.sendRedirect(page);
	
		}else if(url.contains("cartClear.do") == true) {		
			
			int cookno = 0;
			if(session.getAttribute("cookNor") != null) {
				cookno = (int)session.getAttribute("cookNor");
			}
			
			if(cookno == 0) {
				response.sendRedirect("${path}/shop_servlet/Error.do?code=login");
			}
			
			
			String[] tmp = request.getParameterValues("no");			
			
			CartDAO dao = new CartDAO();
			CartDTO dto = new CartDTO();

			int result = 0;
			for(int i = 0; i < tmp.length; i++) {
				
				int no = Integer.parseInt(tmp[i]);
				System.out.println(no);
				dto.setNo(no);		

				result = dao.delete(dto);
			}
			
			String page = "";
			if(result == 0) {
				page = "${path}/shop_servlet/Error.do?code=sakjaeErr";
			}else {			
				page = "${path}/shop_servlet/Buy.do?no="+cookno;
			}

			response.sendRedirect(page);
	
			
		}else if(url.contains("Error.do") == true) {	
			
			String code = request.getParameter("code");

			request.setAttribute("code", code);
			
			
			String page = "/WEB-INF/shop/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}else {
			System.out.println(url);
		}
	
	}

}
