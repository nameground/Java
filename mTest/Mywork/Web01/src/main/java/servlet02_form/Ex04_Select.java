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
@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Select() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청분석
		// => request Parameter 처리
		request.setCharacterEncoding("UTF-8");
		
		String[] jobs = request.getParameterValues("job");
		String[] interests = request.getParameterValues("interest");
		
		// 2) Service 처리
		// 3) 결과 (View) 처리
		// => 한글, 출력객체 생성 & 출력
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2 style='color:blue;'>** SelectBox Test **</h2>");
		for (String j : jobs) {
			out.print(j+" ");
		}
		out.print("<br>");
		for (String i : interests) {
			out.print("<h2>"+i+"</h2>");
		}
		
		
	}

}