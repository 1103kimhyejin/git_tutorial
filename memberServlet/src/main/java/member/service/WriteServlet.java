package member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터
				request.setCharacterEncoding("UTF-8"); // 한글 처리 요청에대한 한글처리 -post방식일때만

				String name = request.getParameter("name"); // name속성가져옴, id는 스타일,js적용할 때 쓰는 것!
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String gender = request.getParameter("gender");
				String email1 = request.getParameter("email1");
				String email2 = request.getParameter("email2");
				String tel1 = request.getParameter("tel1");
				String tel2 = request.getParameter("tel2");
				String tel3 = request.getParameter("tel3");
				String zipcode = request.getParameter("zipcode");
				String addr1 = request.getParameter("addr1");
				String addr2 = request.getParameter("addr2");

				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setName(name);
				memberDTO.setId(id);
				memberDTO.setPwd(pwd);
				memberDTO.setGender(gender);
				memberDTO.setEmail1(email1);
				memberDTO.setEmail2(email2);
				memberDTO.setTel1(tel1);
				memberDTO.setTel2(tel2);
				memberDTO.setTel3(tel3);
				memberDTO.setZipcode(zipcode);
				memberDTO.setAddr1(addr1);
				memberDTO.setAddr2(addr2);

				// DB
				MemberDAO memberDAO = MemberDAO.getInstance(); // 싱글톤 - 메모리에 1번 생성해서 계속 사용한다
				memberDAO.write(memberDTO);// 호출

				// 응답
				response.setContentType("text/html;charset=UTF-8"); // 응답에 대한 한글처리
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("</head>");
				out.println("<body>");
				out.println("회원가입을 축하합니다<br><br>");
				//서블릿엔 쌍따옴표가 안되넹...
				//원래 html처럼 onclick="location.href='주소'"일케 따옴표에 스는게 맞긴한데
				//서블릿은 이미 큰따움표가 있고 그안에서 작은따옴표 또 쓰고 그안에 또 따옴표쓰려면 잘 안먹혀서
				//onclick뒤의 따옴표같은건 생략해버리고 url은 꼭   따옴표묶어주는거 필수라서 못생략하고..url은 절대 따옴표생략이 안된대
				out.println("<input type='button' value='로그인' onclick=location.href='/memberServlet/member/loginForm.html';>");
				//http://localhost:8080 생략 가능 주소는 반드시 ' '써야한다
				out.println("</body>");
				out.println("</html>");
			}
	}


