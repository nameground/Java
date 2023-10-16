package mvcTest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class MVC2_sDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MVC2_sDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석 & Service 
		// => 검색 대상의 id(sno) 필요(찾기) (로그인시 보관해둠)
		// => session 에서 getAttribute
		int sno = (Integer)request.getSession().getAttribute("loginID");
		
		// Service 처리
		StudentService service = new StudentService();
		StudentDTO dto = new StudentDTO();
		dto.setSno(sno);
		dto = service.selectOne(dto);
		
		// 2. View 준비
		// => 결과를 View 가 인식(출력)할 수 있게 준비(setAttribute)
		// => 역할 전달 (Forward or redirect)
		request.setAttribute("apple", dto);
		request.getRequestDispatcher("jsp99_mvcTest/mvc2_sDetail.jsp").forward(request, response);
		
		
	}


}
