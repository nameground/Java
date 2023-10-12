package servlet03_flow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		// => 한글, request의 Parameter
		request.setCharacterEncoding("UTF-8");
		int sno =Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("name");
		
		// 2) 서비스처리
		// => Service, DTO 객체 생성
		// => sno 로 확인 (selectOne 메서드)
		// => 존재하면 name 확인 ( DTO 의 name과 parameter로 전달된 name 과 비교)
		StudentService service = new StudentService();
		StudentDTO dto = new StudentDTO();
		dto.setSno(sno);
		String uri="";
		if(service.selectOne(dto) != null && dto.getName().equals(name) ) {
			uri="index.jsp";
		}else {
			uri="servletTestForm/flowEx04_LoginForm.jsp";
		}
		
		
		// 3) View (Response)처리
		// => 성공 : index.jsp
		// => 실패 : LoginForm (재로그인 유도)
		response.sendRedirect(uri);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 실행");
		doGet(request, response);
	}

}
