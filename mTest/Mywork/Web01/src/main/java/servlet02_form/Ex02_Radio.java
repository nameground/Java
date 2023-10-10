package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex01_Adder
 */
@WebServlet("/radio")
public class Ex02_Radio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_Radio() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청분석
		// => request Parameter 처리
		String gender = request.getParameter("gender");
		String mailcheck = request.getParameter("mailcheck");
		String content = request.getParameter("content");
		
		
		// 2) Service 처리
		if (mailcheck.equals("Yes")) mailcheck = "수신동의";
		else mailcheck = "수신거절";


		// 3) 결과 (View) 처리
		// => 한글, 출력객체 생성 & 출력
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Radio, TextArea Test **</h2>");
		out.print("<h3> gender => " + gender  +"</h3>");
		out.print("<h3> 메일수신 => " + mailcheck  +"</h3>" );
		out.print("<h3> 가입인사 => " + content + "</h3>");
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기</a></h2><br>");
		out.print("</body></html>");

	}

}
