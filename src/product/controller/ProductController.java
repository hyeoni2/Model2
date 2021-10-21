package product.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.tika.Tika;

import com.itextpdf.text.List;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import oracle.sql.ARRAY;
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
			  
			  if(url.contains("Login.do")) {				  
			  
			  }else if(url.contains("LoginProc.do")) {				  
			
			  }else if(url.contains("Logout.do")) {				  
		
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
		if(url.contains("List.do") == true) {
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
		}else if(url.contains("View.do") == true) {
			
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
		}else if(url.contains("Chuga.do") == true) {
			
			page = "/WEB-INF/product/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		//추가 설정	
		}else if(url.contains("ChugaProc.do") == true) {
			
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
			
			//변수 담기
			String tmp = multi.getParameter("count");
			int count = Integer.parseInt(tmp);//3
			
			
			String name = 	multi.getParameter("name");
			
			tmp = multi.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			String description = multi.getParameter("description");
			description = description.replace("<", "&lt;");
			description = description.replace(">", "&gt;");
			description = description.replace(";", "&sc;");
			
			String[] array = new String[count];
			String product_img = ""; 
			String product_img_original = ""; 
			String upload = "";
			
		
			
			//3만큼
			for(int i = 0; i < count; i++) {
				
				String formTagName =""+i;
				//기본파일
				String fileName = multi.getFilesystemName(formTagName);
				//오리지널파일
				String fileOrigin= multi.getOriginalFileName(formTagName);
				String fileType = multi.getContentType(formTagName);
				
				long fileSize = 0;
				String mineType = null;
				
				File file = multi.getFile(formTagName);
				
				if(file != null) {
					fileSize = file.length();
					
					Tika tika = new Tika();
					mineType = tika.detect(file);
				}

				String imsi = "";
				if(fileSize > 0 && mineType != null && fileType.equals(mineType)) {
					//바나나.jpg, 바나나11.jpg,100,imeage
					imsi = fileOrigin + "," +  fileName  + "," + fileSize + "," + mineType;
					product_img += ","+fileName;
					product_img_original += ","+fileOrigin;
				}else {
					product_img += ",-";
					product_img_original += ",-";
					imsi = "-";
				}
				
				
				int num = Integer.parseInt(formTagName);
				array[num] = imsi;
			}
			
			upload = "";
			for(int i = 0; i < array.length; i++) {
				upload += "|" + array[i];
			}		
			
			upload = upload.substring(1);
			product_img = product_img.substring(1);
			product_img_original = product_img_original.substring(1);
			
			
			ProductDTO dto = new ProductDTO();
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			dto.setProduct_img(product_img);
			dto.setProduct_img_original(product_img_original);
			dto.setProductImgInfo(upload);
			
			ProductDAO dao = new ProductDAO();
			int result = dao.setInsert(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "상품이 등록되었습니다.";
				link = path + "/product_servlet/List.do";	
			}else {
				msg = "상품을 등록하는 과정에서 오류가 발생되었습니다.";
				link = path + "/product_servlet/Chuga.do";	
			}
			System.out.println(msg);
			System.out.println(link);
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
		
		//수정
		}else if(url.contains("Sujung.do") == true) {
			
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
			
		}else if(url.contains("SujungProc.do") == true) {
			
			ProductDAO dao = new ProductDAO();
			
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
	
			String tmp = multi.getParameter("no");
			int no = Integer.parseInt(tmp);
			
			tmp = multi.getParameter("count");
			int count = Integer.parseInt(tmp);
			
			String name = 	multi.getParameter("name");
			
			tmp = multi.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			String description = multi.getParameter("description");
			
			String product_img = "";
			String product_img_original = "";
			String fileType = "";
			String upload = "";

			//새배열
			String[] newarray = new String[count];
	
			//db값
			ProductDTO orign = new ProductDTO();
			orign.setNo(no);
			orign = dao.getSelectOne(orign);
			
			String basic = orign.getProductImgInfo();
			String[] basicimg = basic.split("[|]");
			
			//,로 나눈 오리지널배열의 값을 새로운 값에 넣음
			for(int i = 0; i < newarray.length; i++) {
				newarray[i] = basicimg[i];
			}
			
			
			for(int i = 0; i < count; i++) {
				
				String formTagName = ""+i;
				String fileName = multi.getFilesystemName(formTagName);
				String fileOrigin = multi.getOriginalFileName(formTagName);
				fileType = multi.getContentType(formTagName);

				long fileSize = 0;
				String mineType = null;
				
				File file = multi.getFile(formTagName);
				
				if(file != null) {
					fileSize = file.length();
					
					Tika tika = new Tika();
					mineType = tika.detect(file);
				}
				
				String imsi = "";
				
				String fromarray[] = newarray[i].split(",");
				//있을때
				if(fileSize > 0 && mineType != null && fileType.equals(mineType)) {
					
					//파일을 만들고
					File f1 = new File(upload_path + "/" + fromarray[1]);
					f1.delete();				
					//삭제시키고 진행
					imsi = fileOrigin + "," +  fileName  + "," + fileSize + "," + mineType;
					product_img += ","+fileName;
					product_img_original += ","+fileOrigin;
					
				}else {
					
					if(newarray[i].contains("-")) {						
						product_img += ",-";
						product_img_original += ",-";
						imsi = "-";
					}else {
						String sebu[] = basicimg[i].split(",");
						product_img += ","+ sebu[1];
						product_img_original += ","+sebu[0];
						imsi = basicimg[i];
					}
					
				}
				
				newarray[i] = imsi;
		
			}
			for(int n = 0; n< newarray.length; n++) {
				upload += "|"+ newarray[n];
			}
			
			upload = upload.substring(1);
			product_img = product_img.substring(1);
			product_img_original = product_img_original.substring(1);
			
			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);		
			dto.setProduct_img(product_img);
			dto.setProduct_img_original(product_img_original);
			dto.setProductImgInfo(upload);

			int result = dao.setUpdate(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "상품이 수정되었습니다.";
				link = path + "/product_servlet/List.do";	
			}else {
				msg = "상품을 수정하는 과정에서 오류가 발생되었습니다.";
				link = path + "/product_servlet/Sujung.do?no="+no;	
			}
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('"+ msg +"');");
			out.print("location.href = '"+ link +"';");
			out.print("</script>");
		
		//삭제
		}else if(url.contains("Sakjae.do") == true) {
			
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
		}else if(url.contains("SakjaeProc.do") == true) {
			
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
			
			String tmp = multi.getParameter("no");
			int no = Integer.parseInt(tmp);

			ProductDTO dto = new ProductDTO();
			dto.setNo(no);
	
			ProductDAO dao = new ProductDAO();
			dto = dao.getSelectOne(dto);
			
			
			String[] original = dto.getProductImgInfo().split("[|]");

			for(int i = 0; i < original.length; i++) {
				String[] delete= original[i].split(",");	
				
				if(!original[i].contains("-")) {
					File f1 = new File(upload_path +"/" + delete[1]);	
					f1.delete();	
				}
			}

			int result = dao.delete(dto);
			
			String link = "";
			String msg = "";
			if(result > 0) {
				msg = "상품이 삭제되었습니다.";
				link = path + "/product_servlet/List.do";	
			}else {
				msg = "상품을 삭제하는 과정에서 오류가 발생되었습니다.";
				link = path + "/product_servlet/Sakjae.do?no="+no;	
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
