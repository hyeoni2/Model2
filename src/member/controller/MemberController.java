package member.controller;

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

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		
		String path = request.getContextPath();
		// url은 뒤에 오는것을 구분하기 위해 사용
		String url = request.getRequestURL().toString();

		HttpSession session = request.getSession();

		  int cookNo = 0;
		  String abli = "";
		  if(session.getAttribute("cookNor") != null) {
			  cookNo = (int)session.getAttribute("cookNor");
			  abli = (String)session.getAttribute("cookAbility");
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
		  
		 

		// 리스트
		if (url.contains("memberList.do") == true) {
			// DB처리
			MemberDAO dao = new MemberDAO();

			ArrayList<MemberDTO> list = new ArrayList<>();

			list = dao.getSelectAll();
			System.out.println(list.size());

			request.setAttribute("list", list);

			String page = "/WEB-INF/member/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

			// 뷰
		} else if (url.contains("memberView.do") == true) {

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);

			MemberDTO dto = new MemberDTO();
			dto.setNo(no);

			MemberDAO dao = new MemberDAO();
			dao.getSelectOne(dto);

			ArrayList<MemberDTO> list = new ArrayList<>();
			list.add(dto);

			request.setAttribute("list", list);

			String page = "/WEB-INF/member/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

			// 추가
		} else if (url.contains("memberChuga.do") == true) {

			String page = "/WEB-INF/member/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

			// 추가 설정
		} else if (url.contains("memberChugaProc.do") == true) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");

			String ph1 = request.getParameter("ph1");
			String ph2 = request.getParameter("ph2");
			String ph3 = request.getParameter("ph3");

			String phone = ph1 + "-" + ph2 + "-" + ph3;

			String juso1 = request.getParameter("sample6_postcode");
			String juso2 = request.getParameter("sample6_address");
			String juso3 = request.getParameter("sample6_detailAddress");
			String juso4 = request.getParameter("sample6_extraAddress");
			String addr = juso1 + "," + juso2 + "," + juso3 + "," + juso4;
			
			
			String ability = request.getParameter("ability");
			System.out.println(ability);

			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPasswd(passwd);
			dto.setName(name);
			dto.setPhone(phone);
			dto.setAddr(addr);
			dto.setAbility(ability);

			MemberDAO dao = new MemberDAO();
			int result = dao.setInsert(dto);

			String link = "";
			String msg = "";
			if (result > 0) {
				msg = "회원이 등록되었습니다.";
				link = path + "/member_servlet/memberList.do";
			} else {
				msg = "회원을 등록하는 과정에서 오류가 발생되었습니다.";
				link = path + "/member_servlet/memberChuga.do";
			}

			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("location.href = '" + link + "';");
			out.print("</script>");

			// 수정
		} else if (url.contains("memberSujung.do") == true) {

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);

			MemberDTO dto = new MemberDTO();
			dto.setNo(no);

			MemberDAO dao = new MemberDAO();
			dao.getSelectOne(dto);

			String addr = dto.getAddr();
			String ability = dto.getAbility();
			
			System.out.println(ability);

			String juso[] = addr.trim().split(",");
			dto.setJuso1(juso[0]);
			dto.setJuso2(juso[1]);
			dto.setJuso3(juso[2]);
			dto.setJuso4(juso[3]);
			dto.setAbility(ability);

			ArrayList<MemberDTO> list = new ArrayList<>();
			list.add(dto);

			request.setAttribute("list", list);

			String page = "/WEB-INF/member/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("memberSujungProc.do") == true) {

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");

			String juso1 = request.getParameter("sample6_postcode");
			String juso2 = request.getParameter("sample6_address");
			String juso3 = request.getParameter("sample6_detailAddress");
			String juso4 = request.getParameter("sample6_extraAddress");
			String addr = juso1 + ", " + juso2 + ", " + juso3 + ", " + juso4;
			String ability = request.getParameter("ability");
			
			System.out.println(ability);

			MemberDTO dto = new MemberDTO();
			dto.setNo(no);
			dto.setName(name);
			dto.setPhone(phone);
			dto.setAddr(addr);
			dto.setJuso1(juso1);
			dto.setJuso2(juso1);
			dto.setJuso3(juso1);
			dto.setJuso4(juso1);
			dto.setAbility(ability);

			MemberDAO dao = new MemberDAO();
			int result = dao.setUpdate(dto);

			String link = "";
			String msg = "";
			if (result > 0) {
				msg = "회원이 수정되었습니다.";
				link = path + "/member_servlet/memberList.do";
			} else {
				msg = "회원을 수정하는 과정에서 오류가 발생되었습니다.";
				link = path + "/member_servlet/memberSujung.do?no=" + no;
			}
			System.out.println(msg);
			System.out.println(link);

			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("location.href = '" + link + "';");
			out.print("</script>");

			// 삭제
		} else if (url.contains("memberSakjae.do") == true) {

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);

			MemberDTO dto = new MemberDTO();
			dto.setNo(no);

			MemberDAO dao = new MemberDAO();
			dao.getSelectOne(dto);

			ArrayList<MemberDTO> list = new ArrayList<>();
			list.add(dto);

			request.setAttribute("list", list);

			String page = "/WEB-INF/member/sakjae.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

			// 삭제 설정
		} else if (url.contains("memberSakjaeProc.do") == true) {

			String tmp = request.getParameter("no");
			int no = Integer.parseInt(tmp);

			MemberDTO dto = new MemberDTO();
			dto.setNo(no);

			MemberDAO dao = new MemberDAO();
			int result = dao.delete(dto);

			String link = "";
			String msg = "";
			if (result > 0) {
				msg = "회원이 삭제되었습니다.";
				link = path + "/member_servlet/memberList.do";
			} else {
				msg = "회원을 삭제하는 과정에서 오류가 발생되었습니다.";
				link = path + "/member_servlet/memberSakjae.do?no=" + no;
			}

			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("location.href = '" + link + "';");
			out.print("</script>");

		} else if (url.contains("memberLogin.do") == true) {

			String page = "/WEB-INF/member/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("memberLoginProc.do") == true) {

			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");

			MemberDTO dto2 = new MemberDTO();
			dto2.setId(id);
			dto2.setPasswd(passwd);

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getLogin(dto2);

			int cookNor = dto.getNo();
			String cookId = dto.getId();
			String cookName = dto.getName();
			String cookAbility = dto.getAbility();
			
			System.out.println(cookAbility);

			String link = "";
			String msg = "";

			if (cookNor > 0) {
				
				session.setAttribute("cookNor", cookNor);
				session.setAttribute("cookId", cookId);
				session.setAttribute("cookName", cookName);
				session.setAttribute("cookAbility", cookAbility);

				msg = "정상적으로 로그인되었습니다.";
				link = path;
			} else {
				msg = "아이디나 비밀번호를 확인해주세요.";
				link = path + "/member_servlet/memberLogin.do";
			}

			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("location.href = '" + link + "';");
			out.print("</script>");
			

		} else if (url.contains("memberLogout.do") == true) {

			session.invalidate();
			
			PrintWriter out = response.getWriter(); 
			
			out.print("<script>");
			out.print("alert('로그아웃 되었습니다.');");
			out.print("location.href = '"+path+"';"); 
			out.print("</script>");
			

		}

	}

//end	
}
