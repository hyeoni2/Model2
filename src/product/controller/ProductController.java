package product.controller;

import java.io.File;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.model.dao.ProductDAO;
import product.model.dto.ProductDTO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
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
		//url은 뒤에 오는것을 구분하기 위해 사용
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
		  
		String page = "";  
		//리스트
		if(url.contains("productList.do") == true) {
			//DB처리
			ProductDAO dao = new ProductDAO();
			
			ArrayList<ProductDTO> list = new ArrayList<>();
					
			list = dao.getSelectAll();
			System.out.println(list.size());
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/product/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		//뷰	
		}else if(url.contains("productView.do") == true) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();
			dao.getSelectOne(dto);
			
			ArrayList<ProductDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/product/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//추가
		}else if(url.contains("productChuga.do") == true) {
			
			page = "/WEB-INF/product/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//추가 설정	
		}else if(url.contains("productChugaProc.do") == true) {
			
			String attch_path = "C:/sh/attach";
			String upload_path = attch_path + "/product_img"; 
			int max_upload = 1* 1024* 1024;
			
			File isDir = new File(upload_path);
			if(!isDir.exists()) {
				try {
					System.out.println("디렉토리가 존재하지 않습니다.");
					isDir.mkdirs();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			MultipartRequest multi = new MultipartRequest(
					request,
					upload_path,
					max_upload,
					"UTF-8",
					new DefaultFileRenamePolicy());
			
			String product_img = multi.getFilesystemName("product_img");
			String product_img_original = multi.getOriginalFileName("product_img");
			

			if(product_img == null || product_img_original == null) {
				
				product_img = "-";
				product_img_original = "-";
			}
			
			String name = 	multi.getParameter("name");
			
			String tmp = multi.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			String description = multi.getParameter("description");
			
			ProductDTO dto = new ProductDTO();
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			dto.setProduct_img(product_img);
			dto.setProduct_img_original(product_img_original);
			
			ProductDAO dao = new ProductDAO();
			int result = dao.setInsert(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "상품이 등록되었습니다.";
				link = path + "/product_servlet/productList.do";	
			}else {
				msg = "상품을 등록하는 과정에서 오류가 발생되었습니다.";
				link = path + "/product_servlet/productChuga.do";	
			}
			System.out.println(msg);
			System.out.println(link);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
		
		//수정
		}else if(url.contains("productSujung.do") == true) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();
			dao.getSelectOne(dto);
			
			ArrayList<ProductDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/product/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("productSujungProc.do") == true) {
			

			String attch_path = "C:/sh/attach";
			String upload_path = attch_path + "/product_img"; 
			int max_upload = 1* 1024* 1024;
			
			File isDir = new File(upload_path);
			if(!isDir.exists()) {
				try {
					System.out.println("디렉토리가 존재하지 않습니다.");
					isDir.mkdirs();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			MultipartRequest multi = new MultipartRequest(
					request,
					upload_path,
					max_upload,
					"UTF-8",
					new DefaultFileRenamePolicy());
	
			
			String product_img = "";
			String product_img_original = "";
			product_img = multi.getFilesystemName("product_img");
			product_img_original = multi.getOriginalFileName("product_img");
			
			
			String tmp = multi.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			String name = 	multi.getParameter("name");
			
			tmp = multi.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			String description = multi.getParameter("description");
			
			ProductDAO dao = new ProductDAO();
			
			//원조를 불러옴
			ProductDTO origin = new ProductDTO();
			origin.setNo(no);
			
			origin = dao.getSelectOne(origin);
			
			String orif = origin.getProduct_img();
			String ori = origin.getProduct_img_original();
			
			//원조가 값이 있는데 input file을 하지 않았을 경우 동일하게
			if(origin.getProduct_img() != null && product_img == null ) {
				product_img = orif;
				product_img_original = ori;
			//반대일 경우 input file에 넣은것으로	
			}else {
				product_img = multi.getFilesystemName("product_img");
				product_img_original = multi.getOriginalFileName("product_img");
			}
			

			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);		
			dto.setProduct_img(product_img);
			dto.setProduct_img_original(product_img_original);
			
			
			
		
			int result = dao.setUpdate(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "상품이 수정되었습니다.";
				link = path + "/product_servlet/productList.do";	
			}else {
				msg = "상품을 수정하는 과정에서 오류가 발생되었습니다.";
				link = path + "/product_servlet/productSujung.do?no="+no;	
			}
			System.out.println(msg);
			System.out.println(link);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
		
		//삭제
		}else if(url.contains("productSakjae.do") == true) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			
			ProductDAO dao = new ProductDAO();
			dao.getSelectOne(dto);
			
			ArrayList<ProductDTO> list = new ArrayList<>();
			list.add(dto);
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/product/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//삭제 설정
		}else if(url.contains("productSakjaeProc.do") == true) {
			
			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);

			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
	
			
			
			ProductDAO dao = new ProductDAO();
			int result = dao.delete(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "상품이 삭제되었습니다.";
				link = path + "/product_servlet/productList.do";	
			}else {
				msg = "상품을 삭제하는 과정에서 오류가 발생되었습니다.";
				link = path + "/product_servlet/productSakjae.do?no="+no;	
			}

			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
			
		
		}else {
			System.out.println("product");
		}
		
		
		

}

//end	
}
