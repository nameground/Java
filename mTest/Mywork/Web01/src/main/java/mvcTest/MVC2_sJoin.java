package mvcTest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class MVC2_sJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MVC2_sJoin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석 % Service
		// => Post Test 도 할 것이므로 한글처리 필수
		// => request Parameter 처리
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int age = Integer.parseInt( request.getParameter("age"));
		int jno = Integer.parseInt( request.getParameter("jno"));
		String info = request.getParameter("info");
		double point = Double.parseDouble(request.getParameter("point"));
		String birthday = request.getParameter("birthday");
		
		// Service
		StudentService service = new StudentService();
		StudentDTO dto = new StudentDTO();
		dto.setName(name);
		dto.setAge(age);
		dto.setJno(jno);
		dto.setInfo(info);
		dto.setPoint(point);
		dto.setBirthday(birthday);
		
		// ===================================================================
		
		// 2. 결과처리
		// => 성공 : 로그인유도(loginForm 으로 servletTestForm/flowEx04_LoginForm.jsp)
		// => 실패 : 재가입유도(joinForm 으로 jsp99_mvcTest/mvc2_sJoin.jsp)
		String uri="servletTestForm/flowEx04_LoginForm.jsp";
		if(service.insert(dto) > 0) {
			// 성공
			request.setAttribute("message", "가입 성공 로그인 후 이용하세요.");
		}else {
			// 실패
			uri="jsp99_mvcTest/mvc2_sJoin.jsp";
			request.setAttribute("message", "가입 실패 재시도 하세요.");
		}
		
		request.getRequestDispatcher(uri).forward(request, response);
		
	}

	// =================================================================================
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
