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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 데이터
				request.setCharacterEncoding("UTF-8"); // 한글 처리 요청에대한 한글처리 -post방식일때만
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");						

				// DB
				MemberDAO memberDAO = MemberDAO.getInstance(); // 싱글톤 - 메모리에 1번 생성해서 계속 사용한다
				String name = memberDAO.login(id, pwd);// 호출

				// 응답
				response.setContentType("text/html;charset=UTF-8"); // 응답에 대한 한글처리
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("</head>");
				out.println("<body>");
				if(name == null)
					out.println("아이디 또는 비밀번호가 맞지 않습니다");
				else
					out.println(name+"님 로그인");
				out.println("</body>");
				out.println("</html>");
			}

}
