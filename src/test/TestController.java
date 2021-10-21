 	package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.jfree.chart.axis.Tick;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import test.test07.model.dto.Test07DTO;
import test.test09.model.dto.Test09DTO;



@WebServlet("/test_servlet/*")
public class TestController extends HttpServlet {
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
		
		System.out.println("path : "+path);
		System.out.println("url : "+url);
			
	/*
		indexof, lastindexof, split, contain으로 분류가능
		
		int imsi = url.indexOf("test01.do");
		boolean imsi2 = url.contains("test01");
	*/
	
		if(url.contains("test01.do") == true) {
			
			String page = "/WEB-INF/test/test01/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test01Proc.do") == true) {	
			
			String name = request.getParameter("name");
			String jumin = request.getParameter("jumin");
		
		/*	
			request.setAttribute("name", name);
			request.setAttribute("jumin", jumin);
			
			String page = "/WEB-INF/test/test01/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		*/
		/*
			String page = path +"/test_servlet/test03.do";
			response.sendRedirect(page);
		*/	
			
		}else if(url.contains("test02.do") == true) {
			
			String page = "/WEB-INF/test/test02/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test02Proc.do") == true) {
			
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String jumin = request.getParameter("jumin");
			
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("jumin", jumin);
			
			String page = "/WEB-INF/test/test02/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}else if(url.contains("test03.do") == true) {
			
			String page = "/WEB-INF/test/test03/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test03Proc.do") == true) {

			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String jumin = request.getParameter("jumin");
			String age = request.getParameter("age");
			
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("jumin", jumin);
			request.setAttribute("age", age);
		
			String page = "/WEB-INF/test/test03/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		}else if(url.contains("test04.do") == true) {
			
			String page = "/WEB-INF/test/test04/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test04Proc.do") == true) {
			
			String name = request.getParameter("name");
			String jumin = request.getParameter("jumin");
			
			String birth = jumin.substring(0,2);
			int age =  Integer.parseInt(birth);
			
			String gender = jumin.substring(7, 8);
			
			if(gender.equals("1") || gender.equals("2")) {
				age = 1900 + age;
			}else {
				age = 2000 + age;				
			}
			
			if(gender.equals("1") || gender.equals("3")) {
				gender = "남자";
			}else {
				gender = "여자";
			}
			
			age = 2021 - age;
			
			request.setAttribute("name", name);
			request.setAttribute("jumin", jumin);
			request.setAttribute("gender", gender);
			request.setAttribute("age", age);
			
			String page = "/WEB-INF/test/test04/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test05.do") == true) {
			
			String page = "/WEB-INF/test/test05/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		}else if(url.contains("test05Proc.do") == true) {

			String name = request.getParameter("name");
			
			String tmp = "";
			tmp = request.getParameter("kor");
			int kor = Integer.parseInt(tmp);

			tmp = request.getParameter("eng");
			int eng = Integer.parseInt(tmp);

			tmp = request.getParameter("mat");
			int mat = Integer.parseInt(tmp);
			
			tmp = request.getParameter("soc");
			int soc = Integer.parseInt(tmp);
			
			tmp = request.getParameter("sci");
			int sci = Integer.parseInt(tmp);
			
			tmp = request.getParameter("his");
			int his = Integer.parseInt(tmp);
			
			int tot = kor + eng + mat + soc + sci + his;
			double avg = (double)tot / 6.0;
			avg = Math.round(avg);
			
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
			
			request.setAttribute("name", name);
			request.setAttribute("kor", kor);
			request.setAttribute("eng", eng);
			request.setAttribute("mat", mat);
			request.setAttribute("soc", soc);
			request.setAttribute("sci", sci);
			request.setAttribute("his", his);
			request.setAttribute("tot", tot);
			request.setAttribute("avg", avg);
			request.setAttribute("grade", grade);
			
			String page = "/WEB-INF/test/test05/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test06.do") == true) {
			
			String page = "/WEB-INF/test/test06/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test06Proc.do") == true) {
			
			String gubun = request.getParameter("gubun");
			String name = request.getParameter("name");
			
			String tmp = request.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			tmp = request.getParameter("dc");
			int dc = Integer.parseInt(tmp);

			tmp = request.getParameter("dcamount");
			int dcamount = Integer.parseInt(tmp);
			
			String brand = request.getParameter("brand");
			
			request.setAttribute("gubun", gubun);
			request.setAttribute("name", name);
			request.setAttribute("price", price);
			request.setAttribute("dc", dc);
			request.setAttribute("dcamount", dcamount);
			request.setAttribute("brand", brand);
			
			String page = "/WEB-INF/test/test06/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}else if(url.contains("test07.do") == true) {
			
			
			String page = "/WEB-INF/test/test07/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test07Proc.do") == true) {
			
			String gubun = request.getParameter("gubun");
			String name = request.getParameter("name");
			
			String tmp = request.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			tmp = request.getParameter("dc");
			int dc = Integer.parseInt(tmp);

			tmp = request.getParameter("dcamount");
			int dcamount = Integer.parseInt(tmp);
			
			String brand = request.getParameter("brand");
			
			Test07DTO dto = new Test07DTO();
			dto.setGubun(gubun);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDc(dc);
			dto.setDcamount(dcamount);
			dto.setBrand(brand);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/test/test07/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test08.do") == true) {

			String page = "/WEB-INF/test/test08/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test08Proc.do") == true) {
			
			String gubun = request.getParameter("gubun");
			String name = request.getParameter("name");
			
			String tmp = request.getParameter("price");
			int price = Integer.parseInt(tmp);
			
			tmp = request.getParameter("dc");
			int dc = Integer.parseInt(tmp);

			tmp = request.getParameter("dcamount");
			int dcamount = Integer.parseInt(tmp);
			
			String brand = request.getParameter("brand");
			
			Map<String , String> map = new HashMap<>();
			map.put("gubun", gubun);
			map.put("name", name);
			map.put("price", price+"");
			map.put("dc", dc+"");
			map.put("dcamount", dcamount+"");
			map.put("brand", brand+"");

			request.setAttribute("map", map);
			
			String page = "/WEB-INF/test/test08/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test09.do") == true) {

			String page = "/WEB-INF/test/test09/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if(url.contains("test09Proc.do") == true) {
			
			String name = request.getParameter("name");
			
			String tmp = "";
			tmp = request.getParameter("kor");
			int kor = Integer.parseInt(tmp);

			tmp = request.getParameter("eng");
			int eng = Integer.parseInt(tmp);

			tmp = request.getParameter("mat");
			int mat = Integer.parseInt(tmp);
			
			tmp = request.getParameter("soc");
			int soc = Integer.parseInt(tmp);
			
			tmp = request.getParameter("sci");
			int sci = Integer.parseInt(tmp);
			
			tmp = request.getParameter("his");
			int his = Integer.parseInt(tmp);
			
			int tot = kor + eng + mat + soc + sci + his;
			double avg = (double)tot / 6.0;
			avg = Math.round(avg);
			
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
			
			Test09DTO dto = new Test09DTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSoc(soc);
			dto.setSci(sci);
			dto.setHis(his);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			
			Map<String, Test09DTO> map = new HashMap<>();
			map.put("dto", dto);
			
			request.setAttribute("map", map);
			
			String page = "/WEB-INF/test/test09/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test10.do") == true) {	
		
			String page = "/WEB-INF/test/test10/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test10Proc.do") == true) {
			
			String name = request.getParameter("name");
			
			String tmp = "";
			tmp = request.getParameter("kor");
			int kor = Integer.parseInt(tmp);

			tmp = request.getParameter("eng");
			int eng = Integer.parseInt(tmp);

			tmp = request.getParameter("mat");
			int mat = Integer.parseInt(tmp);
			
			tmp = request.getParameter("soc");
			int soc = Integer.parseInt(tmp);
			
			tmp = request.getParameter("sci");
			int sci = Integer.parseInt(tmp);
			
			tmp = request.getParameter("his");
			int his = Integer.parseInt(tmp);
			
			int tot = kor + eng + mat + soc + sci + his;
			double avg = (double)tot / 6.0;
			avg = Math.round(avg);
			
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
			
			Map<String, Object> map = new HashMap<>();
			map.put("name", name);
			map.put("kor", kor);
			map.put("eng", eng);
			map.put("mat", mat);
			map.put("soc", soc);
			map.put("sci", sci);
			map.put("his", his);
			map.put("tot", tot);
			map.put("avg", avg);
			map.put("grade", grade);

			request.setAttribute("map", map);
			
			String page = "/WEB-INF/test/test10/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test11.do") == true) {	
			
			String page = "/WEB-INF/test/test11/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		
		
		//하나하나 다 추가
		}else if(url.contains("test11Proc1.do") == true) {	
			String attach_path = "C:/sh/attach";
			String upload_path = attach_path + "/product_img";//첨부파일 저장위치
			int max_upload = 1 * 1024 * 1024;//10m 최대 업로드 10m로 제한
			
			java.io.File isDir = new java.io.File(upload_path);
			if(!isDir.exists()) {
				try {
					//System.out.println("디렉토리가 존재하지 않습니다.");
					isDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				//System.out.println("디렉토리가 존재합니다.");
				
			}
			
			MultipartRequest multi = new MultipartRequest(
					request,
					upload_path,
					max_upload,
					"utf-8",
					new DefaultFileRenamePolicy());
	
		
		 //	하나하나 다 추가하는 방법
			String filename1 = multi.getFilesystemName("file1");
			String fileType1 = multi.getContentType("file1");
			String fileOrgNmae1 = multi.getOriginalFileName("file1");
			java.io.File file1 = multi.getFile("file1");
			
			long fileSize1 = 0;
			if(file1 != null) {
				fileSize1 = file1.length();
				Tika tika = new Tika();
				String tika1 = tika.detect(file1);				
			}
		

			String name = multi.getParameter("name");
			String phone = multi.getParameter("phone");
			String tmp = multi.getParameter("upload_count");
			int upload_count = Integer.parseInt(tmp);		
			
			String[] array = new String[upload_count];
			
			//파일들을 불러와서 처리하는 방법
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String formTagName = (String)files.nextElement();
				String fileOrgName = multi.getOriginalFileName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);

				if(formTagName == null || fileOrgName == null) {
					System.out.println("첨부파일이 없습니다.");
				}

				long fileSize = 0;
				String mineType = "";
				java.io.File file = multi.getFile(formTagName);
						if(file != null) {
							fileSize = file.length();
							Tika tika = new Tika();
							mineType = tika.detect(file);				
						}
			
				if(fileSize > 0 && mineType != null && mineType.equals(fileType)) {
					String upload_info = "|";
							upload_info +=  fileOrgName;
							upload_info += ","+ fileName;
							upload_info += ","+ fileType;
							upload_info += ","+ fileSize;	
				}}
				
			//***********************************************************//

		//배열로 처리	
		}else if(url.contains("test11Proc2.do") == true) {	

			String attach_path = "C:/sh/attach";
			String upload_path = attach_path + "/product_img";//첨부파일 저장위치
			int max_upload = 1 * 1024 * 1024;//10m 최대 업로드 10m로 제한
			
			java.io.File isDir = new java.io.File(upload_path);
			if(!isDir.exists()) {
				try {
					//System.out.println("디렉토리가 존재하지 않습니다.");
					isDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				//System.out.println("디렉토리가 존재합니다.");
				
			}
			
			MultipartRequest multi = new MultipartRequest(
					request,
					upload_path,
					max_upload,
					"utf-8",
					new DefaultFileRenamePolicy());


			String name = multi.getParameter("name");
			String phone = multi.getParameter("phone");
			String tmp = multi.getParameter("upload_count");
			int upload_count = Integer.parseInt(tmp);		
			
			String[] array = new String[upload_count];
			
			//파일들을 불러와서 처리하는 방법
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String formTagName = (String)files.nextElement();
				String fileOrgName = multi.getOriginalFileName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);

				if(formTagName == null || fileOrgName == null) {
					System.out.println("첨부파일이 없습니다.");
				}

				long fileSize = 0;
				String mineType = "";
				java.io.File file = multi.getFile(formTagName);
						if(file != null) {
							fileSize = file.length();
							Tika tika = new Tika();
							mineType = tika.detect(file);				
						}
			
				if(fileSize > 0 && mineType != null && mineType.equals(fileType)) {
	
					int temp = Integer.parseInt(formTagName);
					array[temp] = fileOrgName + "," + fileName;
						}
							
			}
			for(int i = 0; i < array.length; i++) {
				System.out.println("array["+i+"] : "+array[i]);
			}

		
			
			//***********************************************************//
		
		//for문 돌려서 확인
		}else if(url.contains("test11Proc3.do") == true) {	
		
			String attach_path = "C:/sh/attach";
			String upload_path = attach_path + "/product_img";//첨부파일 저장위치
			int max_upload = 1 * 1024 * 1024;//10m 최대 업로드 10m로 제한
			
			java.io.File isDir = new java.io.File(upload_path);
			if(!isDir.exists()) {
				try {
					//System.out.println("디렉토리가 존재하지 않습니다.");
					isDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				//System.out.println("디렉토리가 존재합니다.");
				
			}
			
			MultipartRequest multi = new MultipartRequest(
					request,
					upload_path,
					max_upload,
					"utf-8",
					new DefaultFileRenamePolicy());
			
			String name = multi.getParameter("name");
			String phone = multi.getParameter("phone");
			String tmp = multi.getParameter("upload_count");
			int upload_count = Integer.parseInt(tmp);	
			
			for(int i = 0; i < upload_count; i ++) {
				String formTagName = ""+i;
				
				String fileOrgName = multi.getOriginalFileName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);

				long fileSize = 0;
				String mineType = "";
				java.io.File file = multi.getFile(formTagName);
						if(file != null) {
							fileSize = file.length();
							Tika tika = new Tika();
							mineType = tika.detect(file);				
						}
				
			}
		
		
		}else if(url.contains("test12.do") == true) {	
			
			String attach_path = "C:/sh/attach";
			String upload_path = attach_path+"/product_img";
			
			java.io.File f1 = new java.io.File(upload_path);
			java.io.File[] array = f1.listFiles();
			

			List<String> list = new ArrayList<>();
			for(int i = 0; i < array.length; i ++) {
				if(array[i].isFile()) {
					//System.out.println("파일"+array[i].getPath());
					
				}else {
					//System.out.println("폴더"+array[i].getPath());
				}
				list.add(array[i].getPath());
			}
			

			request.setAttribute("list", list);
			
			String page = "/WEB-INF/test/test12/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test12Proc.do") == true) {	
			
			String filePath = request.getParameter("filePath");
			System.out.println(filePath);
			
			java.io.File f1 = new java.io.File(filePath);
			f1.delete();
			
			response.sendRedirect(path + "/test_servlet/test12.do");
			
			
		}else {
			return;
		}
	
		
	
	}

}
