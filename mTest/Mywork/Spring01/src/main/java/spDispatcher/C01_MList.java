package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

public class C01_MList implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// MemverService
		MemberService service = new MemberService();
		
		ModelAndView mv = new ModelAndView();
		// => Model 과 view 로 전달하는 객체
		mv.addObject("banana",service.selectList());
		mv.setViewName("member/memberList");
		return mv;
	}
	
}