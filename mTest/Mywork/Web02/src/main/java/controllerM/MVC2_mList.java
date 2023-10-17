package controllerM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mlist")
public class MVC2_mList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MVC2_mList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석 & Service 처리
		MemberService service = new MemberService(); 
		List<MemberDTO> list = service.selectList();
		
		// 2. View 로 처리결과 전달
		// => 처리결과를 JSP 가 출력(인식)할 수 있게 준비(setAttribute)
		// => 역할 전달 ( forward or redirect )
		request.setAttribute("banana", list);
		request.getRequestDispatcher("member/memberList.jsp").forward(request, response);
		
	}

}
