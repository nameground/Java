package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/delete")
public class MVC2_sDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MVC2_sDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석 & Service 처리
		// => 삭제할 대상(queryString, request 의 Parameter 로 전될됨)
		String id = request.getParameter("id");
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		
		
		// 2. 결과
		// => 삭제 성공/실패 -> List 출력 ( "list2" 로 Forward )
		// => message 출력
		if(service.delete(dto) > 0) {
			// 성공
			request.setAttribute("message", id + " 님 삭제 성공");
		}else {
			// 실패
			request.setAttribute("message", id + " 님 삭제 실패");
		}
		
		//  List 출력 ( "list2" 로 Forward ) 부분
		request.getRequestDispatcher("list2").forward(request, response);
		
	}


}
