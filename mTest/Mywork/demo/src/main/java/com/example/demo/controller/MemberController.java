package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

// ** MemberController
// => SpringBoot, JSP사용, 계층적 uri 적용

@Controller
@RequestMapping("/member")
@Log4j2  //@Log4j -> Boot 에서는 2015년 이후 지원중단
@AllArgsConstructor // 모든 맴버변수 생성자 주입하므로 각각 @Autowired 할 필요없음
public class MemberController {
	
	MemberService service;
	PasswordEncoder passwordEncoder;
	
	
	// ** Axios_MemberList
	@GetMapping("/axMemberList")
	public String axMemberList(Model model) {
		model.addAttribute("banana", service.selectList());
		log.info("** axMemberList 성공 **");
		return "axTest/axMemberList";
	}
	
	// ** File Download **********************************************
	// => 전달받은 path 와 파일명으로 File 객체를 만들어 찾아서 response에 담아주면,
	//    클라이언트의 웹브라우져로 전달됨.
	@GetMapping("/download")
	public String download(HttpServletRequest request, Model model,
					@RequestParam("dnfile") String dnfile) {
					// => 동일표현 String dnfile = request.getParameter("dnfile");
		// 1) 파일 & path 확인
		String realPath = request.getRealPath("/"); //deprecated Method
		String fileName = dnfile.substring(dnfile.lastIndexOf("/")+1);
		// => dnfile: resources/uploadImages/robot.png
		
		// => realpath 확인, 개발중인지, 배포했는지 에 따라 결정
		// => 해당화일 File 찾기위함
		if ( realPath.contains(".eclipse.") )  // 개발중 (배포전: eclipse 개발환경) 
			 realPath="C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\uploadImages\\";
		else realPath+="resources\\uploadImages\\";
		
		realPath+=fileName;  // ~~~~~\\resources\\uploadImages\\robot.png -> path 완성
		
		// 2) 해당 화일 (path+fileName) File Type 으로 객체화
		File file = new File(realPath);
		model.addAttribute("downloadFile", file);

		// 3) response 처리 (response의 body 에 담아줌) 
		// => Java File 객체 -> File(내용) 정보를 response 에 전달
		// => 이부분은 추후 해결
		return "";
	} //download
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// ** ID 중복확인
	@GetMapping("/idDupCheck")
	public String idDupCheck(MemberDTO dto, Model model) {
		// 1) newID 확인
		if ( service.selectOne(dto) !=null ) {
			// => 존재 : 사용불가
			model.addAttribute("idUse", "F");
		}else {
			// => 없으면: 사용가능
			model.addAttribute("idUse", "T");
		}
		return "member/idDupCheck";
	}
	
	// ** PasswordUpdate
	// => passwordUpdate_Form 출력
	@GetMapping(value="/pUpdateForm")
	public void pUpdateForm() {
		// viewName 생략
	} //pUpdateForm
	
	// => password 만 수정
	@PostMapping(value="/passwordUpdate")
	public String passwordUpdate(HttpServletRequest request, Model model, MemberDTO dto) {
		// ** password Update
		// => 로그인 확인: session 에서 id get 
		// => passwordEncode (암호화) 처리 	
		// => Service
		// 	-> 성공: 재로그인 유도 -> session 무효화, member/loginForm 으로 
		//	-> 실패: 재수정 유도 -> pUpdateForm 
	
		String id =(String)request.getSession().getAttribute("loginID");
		// ** id 가 존재하지 않는 경우 -> 로그인유도, 메서드종료
		if (id==null) {
			model.addAttribute("message", "~~ 로그인 정보가 없으니 로그인 후 하세요 ~~");
			return "member/loginForm";
		}
		// ** id 가 존재하는 경우 수정
		dto.setId(id);
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		String uri="member/loginForm";
		if ( service.update(dto)>0 ) {
			// password 수정성공, session 무효화, loginForm 으로
			request.getSession().invalidate(); 
			model.addAttribute("message", "~~ password 수정 성공, 재로그인 하세요 ~~");
		}else {
			// password 수정실패
			model.addAttribute("message", "~~ password 수정 실패 , 다시 하세요 ~~");
			uri="member/pUpdateForm";
		}
		// 3) View 처리
		return uri;
	} //passwordUpdate
	
	// ** MemberList
	@GetMapping("/memberList")
	public void memberList(Model model) {
		model.addAttribute("banana", service.selectList());
		log.info("** MemberList 성공 **");
	}
	
	// ** MemberDetail
	@GetMapping(value ="/mdetail")
	public String mdetail(HttpServletRequest request, Model model, MemberDTO dto) {
		model.addAttribute("apple", service.selectOne(dto));
		
		if ( "U".equals(request.getParameter("jCode")) )
			 return "member/memberUpdate";
		else return "member/memberDetail";
	} //mdetail

	// ** Member Login & Logout
	// => LoginForm : Get
	// => 계층적 url 적용 
	@GetMapping(value="/loginForm")
	public void loginForm() {
		// viewName 생략 -> 요청명이 viewName 이 됨
		// => 단, viewName 을 return 하는 경우에는 정확한 경로를 지정해야함
		//    폴더명 생략시 "/WEB-INF/views/loginForm.jsp" 가 만들어짐 
	}
	
	// => Login 처리 : Post
	@PostMapping(value="/login")
	public String login(HttpSession session, Model model, MemberDTO dto) {
		// ** 로그인 Service 처리
		// 1. 요청분석
		String password = dto.getPassword();
		String uri="redirect:/home"; 
		// "home" -> home.jsp (성공)
		// "redirect:home" -> home 을 재요청, 그러므로 HomeController 의 home 메서드로
		
		// 2. 서비스 처리
		dto=service.selectOne(dto);
		
		if ( dto!=null && 
			 passwordEncoder.matches(password, dto.getPassword()) ) {	
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		}else {
			uri="member/loginForm";
			model.addAttribute("message", "로그인 실패! 다시 하세요 ~~");
		}
		return uri;
	} //login_Post
	
	// => Logout
	// => session 무효화, home으로 
	@GetMapping(value="/logout")
	public String logout(HttpSession session, Model model, RedirectAttributes rttr) {
		
		session.invalidate();
		rttr.addFlashAttribute("message", "~~ 로그아웃 성공 ~~");
		return "redirect:/home";
	} //logout

	// ** Join 기능
	// => JoinForm: GET
	@GetMapping(value="/memberJoin")
	public void memberJoin() {
		// viewName 생략 -> 요청명이 viewName 이 됨
	}
	
	// => Join Service 처리: POST
	@PostMapping(value="/join")
	public String join(HttpServletRequest request, 
					MemberDTO dto, Model model) throws IOException  {
		// 1. 요청분석 & Service
		// => 성공: 로그인유도 (loginForm 으로, member/loginForm.jsp)
		// => 실패: 재가입유도 (joinForm 으로, member/memberJoin.jsp)
		String uri="member/loginForm";
		
		// ** PasswordEncoder (암호화 적용)
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		// ** MultipartFile ***********************
		String realPath = request.getRealPath("/");
		System.out.println("** ralPath => "+realPath);
		
		// 1.2) 위의 값(realPath) 을 이용해서 실제저장위치 확인 
		// => 개발중인지, 배포했는지에 따라 결정
		if ( realPath.contains(".eclipse.") ) // 개발중 (배포전: eclipse 개발환경) 
			 realPath = "C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\uploadImages\\";
		else realPath += "resources\\uploadImages\\";
			//=> 필요한경로: D:\MTest\IDESet\apache-tomcat-9.0.41\webapps\Spring02\resources\~ploadImages\
		
		// ** 폴더 만들기 (File 클래스활용)
		File f1 = new File(realPath);
		if ( !f1.exists() ) { 
			f1.mkdir();
			// => realPath 가 존재하지 않으면 생성
		}
		
		// => 기본이미지(basicman4.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
		// => IO 발생: Checked Exception 처리
		f1 = new File(realPath+"basicman4.png"); // uploadImages 폴더에 화일존재 확인을 위함
		if ( !f1.isFile() ) { // 존재하지않는 경우
			String basicImagePath 
					= "C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\images\\basicman4.png";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(f1); 
			// => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성  
			FileCopyUtils.copy(fi, fo);
		}
		// => 기본 이미지 지정하기
		String file1, file2="resources/uploadImages/basicman4.png";
		
		// ** MultipartFile
		// => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
		
		// 1.3) 저장경로 완성
		MultipartFile uploadfilef = dto.getUploadfilef();
		if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
			// => image_File 을 선택함 -> 저장 (저장경로: relaPath+화일명)
			// 1.3.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); //저장경로 완성 
			uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
			
			// 1.3.2) Table 저장경로 완성 (file2)
			file2 = "resources/uploadImages/" + uploadfilef.getOriginalFilename();
		} // Image 선택한 경우
		
		// 1.4) 완성된 경로를 dto 에 set
		dto.setUploadfile(file2);
		
		
		// 2. Service 처리
		if ( service.insert(dto) > 0 ) {  // Transaction_Test, insert2 
			model.addAttribute("message", "~~ 회원가입 성공!! 로그인후 이용하세요 ~~");
		}else {
			model.addAttribute("message", "~~ 회원가입 실패!! 다시 하세요 ~~");
			uri="member/memberJoin";
		}
		
		// 3. View 
		return uri;
	} // Join_Post
	
	// ** Member Update
	// => 요청: home 에서 내정보수정 -> 내정보수정Form (memberUpdate.jsp) 출력
	// => 수정후 submit -> 수정 Service 
	//		-> 성공: detail
	//		-> 실패: 재시도 유도 (memberUpdate.jsp)
	@PostMapping(value="/mupdate")
	public String memberUpdte(HttpServletRequest request, 
							  MemberDTO dto, Model model) throws IOException {
		
		// => 처리결과에 따른 화면 출력을 위해서 dto 의 값을 Attribute에 보관
		model.addAttribute("apple", dto);
		String uri="member/memberDetail";
		
		// ** password 수정과 나머지 컬럼 수정을 분리
		// => mapper 에서 이것을 구분 하할 수 있도록 password 값을 null 로 
		dto.setPassword(null);
		
		// *** ImageUpload 처리 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// => newImage 선택한경우: MultipartFile 처리
		// => newImage 선택하지 않은 경우: 이전값 그대로사용 
		//	  ( form 에 hidden으로 보관해놓았으므로 dto에 담겨짐 ) 
		// => MemberMapper.xml 의 SQL 구문 확인
		
		MultipartFile uploadfilef = dto.getUploadfilef(); 
		// => new Image 를 선택한 경우에만 처리하면 됨 
		if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
			// => Image 재선택 MultipartFile 처리
			String realPath = request.getRealPath("/");
		
			// => 개발중인지, 배포했는지에 따라 실제저장위치 결정
			if ( realPath.contains(".eclipse.") )  // 개발중 (배포전: eclipse 개발환경) 
				realPath="C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\uploadImages\\";
			else realPath+="resources\\uploadImages\\";
			
			// => 물리적위치에 저장 (file1)
			String file1 = realPath + uploadfilef.getOriginalFilename(); //저장경로 완성
			uploadfilef.transferTo(new File(file1)); // IO 발생: Checked Exception 처리  
			
			// => Table 저장경로 완성 (file2)
			String file2="resources/uploadImages/" + uploadfilef.getOriginalFilename();
			dto.setUploadfile(file2);
		} // Image 선택 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		// => Service 처리
		if ( service.update(dto) > 0 ) {
			model.addAttribute("message", "~~ 회원정보 수정 성공 ~~");
		}else {
			model.addAttribute("message", "~~ 회원정보 수정 실패 !! 다시 하세요 ~~");
			uri="member/memberUpdate";
		}
		return uri;
	} //memberUpdte
	
	// ** Member Delete: 회원탈퇴
	@GetMapping(value="/mdelete")
	public String mdelete(HttpSession session, MemberDTO dto, Model model, RedirectAttributes rttr) {
		
		// 1) 본인탈퇴
		// 결과 : message(삭제 성공/실패), home.jsp, session 무효화 
		
		// 2) 관리자에 의한 강제탈퇴
		// 결과 : message(삭제 성공/실패), memberList.jsp
		
		// => 본인탈퇴 or 관리자에 의한 강제탈퇴 구분이 필요
		//	  dto 의 id 와 session 의 loginID 와 같으면 본인탈퇴,
		//    다르면서 session 의 loginID 값이 "admin" 이면 강제탈퇴
		String uri = "redirect:/home";
		
		if ( service.delete(dto) > 0 ) {
			 rttr.addFlashAttribute("message", "~~ 탈퇴 성공!! 1개월후 재가입 가능 합니다 ~~") ;	
			 if ( ((String)session.getAttribute("loginID")).equals("admin") ) {
				 // => 관리자에 의한 강제탈퇴
				 uri="redirect:memberList";
			 }else {
				 // => 본인탈퇴
				 session.invalidate();
			 }
		}else {
			rttr.addFlashAttribute("message", "~~ 탈퇴 실패 ~~");
		}
		return uri;
	} // mdelete

} //class
