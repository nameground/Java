package com.ncs.green;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.BoardDTO;
import domain.JoDTO;
//import domain.MemberDTO;
import lombok.AllArgsConstructor;
import service.JoService;

// @Log4j
@AllArgsConstructor // @Autowired 를 사용하지 않아도 됨
@RequestMapping(value = "/jo") // "/member" 로 시작하는 모든 요청을 처리
@Controller
public class JoController {

	JoService service;
	// JoList
	@GetMapping(value = "/joList")
	public void jolist(Model model) {
		model.addAttribute("banana", service.selectList());
	}

	// ===============================================================

	// ** JoDetail
	@GetMapping(value = "/jdetail")
	public String jdetail(HttpServletRequest request, Model model, JoDTO dto) {
		
		model.addAttribute("apple", service.selectOne(dto));
		
		// => 글 수정화면 요청인경우 구분
		model.addAttribute("apple", service.selectOne(dto));
		if( "U".equals(request.getParameter("jCode")) ) {
			return "jo/joUpdate";
		}else {
			return "jo/joDetail";
		}
	}

	// ===============================================================

	// Insert 기능 (새글 등록)
	@GetMapping(value = "/joInsert")
	public void JoInsert() {
		// viewName 생략 -> 요청명이 viewName 이 됨
	}

	// ===============================================================

	// => Insert Service 처리 : POST
	@PostMapping(value = "/jinsert")
	public String jinsert(JoDTO dto, Model model, RedirectAttributes rttr) {
		// 1. 요청분석 & Service
		String uri = "redirect:joList"; // 성공 값

		// 2. Service 처리
		if(service.insert(dto) > 0) {
			rttr.addFlashAttribute("message" , "새조 등록 성공");
		}else {
			rttr.addFlashAttribute("message" , "새조 등록 실패 다시 하세요.");
			uri = "jo/joInsert";
		}

		// 3. View
		return uri;
	}

	// board Update
	// => 성공 : boardDetail
	// => 실패 : boardUpdate
	@PostMapping(value = "/jupdate")
	public String jupdate(JoDTO dto, Model model) {

		// => 처리결과에 따른 화면 출력 위해서 dto 의 값을 Attribute 에 보관
		model.addAttribute("apple", dto);
		String uri="jo/joDetail";

		// => Service 처리
		if(service.update(dto) > 0) {
			model.addAttribute("message", "조 수정 성공");
		}else {
			model.addAttribute("message", "조 수정 실패, 다시하세요.");
			uri="board/boardUpdate";
		}
		return uri;
	}

	// ===============================================================

	// Jo Delete : 탈퇴
	@GetMapping(value = "/jdelete")
	public String jdelete(JoDTO dto, Model model, RedirectAttributes rttr) {
		
		String uri = "redirect:joList";
		
		if( service.delete(dto) > 0) {
			rttr.addFlashAttribute("message", "조 삭제 성공");
		}else {
			rttr.addFlashAttribute("message", "조 삭제 실패");
		}
		return uri;
	}

	// ===============================================================
}