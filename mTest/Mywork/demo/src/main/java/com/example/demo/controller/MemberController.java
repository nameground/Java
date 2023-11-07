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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

// MemberController
// => SpringBoot, JSP사용, 계층적 uri 적용

@Controller
@RequestMapping("/member")
@Log4j2 //@Log4j -> Boot 에서는 2015년 이후 지원중단
@AllArgsConstructor // 모든 멤버션수 생서자주입하므로 각각 @Autowired 할 필요없음
public class MemberController {
	
	MemberService service;
	PasswordEncoder passwordEncoder;
	
	// ID 중복확인
	@GetMapping("idDupCheck")
	public String idDupCheck(MemberDTO dto, Model model) {
		// 1) newID 확인
		if(service.selectOne(dto) != null) {
			// => 존재 : 사용불가
			model.addAttribute("idUse", "F");
		}else {
			// => 존재하지않으면 : 사용가능
			model.addAttribute("idUse", "T");
		}
		return "member/idDupCheck";
	}
	
	// ===============================================================
	
	@GetMapping("/memberList")
	public void memberList (Model model) {
		model.addAttribute("banana", service.selectList());
		log.info("MemberList 성공");
	}
	
	// ===============================================================
	
	// ** MemberDetail
	// @RequestMapping(value = "/mdetail", method = RequestMethod.GET)
	@GetMapping(value = "/mdetail")
	public String mdetail(HttpServletRequest request, Model model, MemberDTO dto) {
		//dto.setId("검색id");
		model.addAttribute("apple", service.selectOne(dto));
		if( "U".equals(request.getParameter("jCode")) ) {
			return "member/memberUpdate";
		}else {
			return "member/memberDetail";
		}
	}

	// ===============================================================

	// ** Member Login  & Logout
	// => LoginForm : Get
	//   @RequestMapping(value = "/login", method = RequestMethod.GET)
	//   public String loginForm() {
	//	   return "member/loginForm";
	//   }
	// => 계층적 url 적용
	//    home 에서 요청명 : "member/loginForm"
	@GetMapping(value = "/loginForm")
	public void loginForm() {
		// viewName 생략 -> 요청명이 viewName 이 됨
	}

	// ===============================================================

	// => Login 처리 : Post
	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	@PostMapping(value = "/login")
	public String login(HttpSession session, Model model, MemberDTO dto) {
		// 로그인 Service 처리
		// => request 로 전달되는 id, password 처리:
		//    메서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		//   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌)
		String password = dto.getPassword();
		String uri = "redirect:/"; 
		// "home" : home.jsp(성공)
		// "redirect : home -> home 을 재요청, 그러므로 HomeController 의 home 메서드로

		// 2. 서비스처리
		// => id 확인
		// => 존재하면 Password 확인
		// => 성공 : id, name 은 session 에 보관, home.jsp 으로
		// => 실패 : 재로그인 유도
		dto = service.selectOne(dto);
		// if (dto != null && dto.getPassword().equals(password)) {
		
		// PasswordEncoder 적용 
		if(dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			session.setAttribute("loginID",dto.getId());
			session.setAttribute("loginName",dto.getName());
		}else {
			uri = "member/loginForm";
			model.addAttribute("message", "로그인 실패 다시하세요.");
		}

		System.out.println("Login Post 처리 준비중");
		return uri;

	}

	// ===============================================================

	// => LogOut
	// => session 무효화, home으로
	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	@GetMapping(value = "/logout")
	public String logout(HttpSession session, Model model, RedirectAttributes rttr) {
		session.invalidate();
		//model.addAttribute("message", "로그아웃 성공");
		// => 단, request 에 보관한 값들은 사라지므로 위의 메세지 처리를 고려해야함.
		// => session 에 보관 ( 이미 logOut 경우 세션을 무효화 했으므로 500 발생 )
		// 그리고 session 무효화를 하지 않더라도 이 메시지는 사용후 삭제를 해야함
		// session.setAttribute("message", "로그아웃 성공");
		//=> 이렇게 redirect 하는 경우 메시지 처리등을 편리하게
		//   지원해주는 객체가 RedirectAttributes
		rttr.addFlashAttribute("message", "로그아웃 성공");
		return "redirect:/";
	}

	// ===============================================================

	// Join 기능
	// joinform : GET
	//   @RequestMapping(value = "/join", method = RequestMethod.GET)
	//   public String memberJoin() {
	//	   return "member/memberJoin";
	//   }

	// => 계층형 url 적용
	@GetMapping(value = "/memberJoin")
	public void memberJoin() {
		// viewName 생략 -> 요청명이 viewName 이 됨
	}

	// => Join Service 처리 : POST
	//@RequestMapping(value = "/join", method = RequestMethod.POST)
	@PostMapping(value = "/join")
	public String join(HttpServletRequest request, MemberDTO dto, Model model) throws IOException {
		// 1. 요청분석 & Service
		// => 한글처리 필수 : filter 로 처리
		// => request Parameter 처리 : 매개변수로 MemberDTO 정의하면 자동으로 set
		// => 성공: 로그인유도 (loginForm 으로, member/loginForm.jsp)
		// => 실패: 재가입유도 (joinForm 으로, member/memberJoin.jsp)
		String uri = "member/loginForm";
		
		// PasswordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		// ** MultipartFile ***********************
		// => 전달된 UploadFile 정보 전달
		// => MultipartFile 타입의 uploadfilef 의 정보에서 
		//    upload된 image 화일과 화일명을 get 처리,
		// => upload된 image 화일은 서버의 정해진 폴더 (물리적위치)에 저장하고, -> file1

		// => 이 위치에 대한 정보를 table에 저장 (vo의 UploadFile 에 set) -> file2
		//      resources/uploadImage/bbb.gif -> Table 의 저장위치

		// ** image 화일명 중복시 : 나중 이미지로 update 됨. 

		// ** Image 물리적위치 저장위치 확인하기
		// 1.1) 현재 웹어플리케이션의 실행 위치 확인 : 
		// => eclipse 개발환경 (배포전)
		
		//    D:\MTest\myWork\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Spring02\
		//    C:\eGovFrame-4.0.0\workspace.edu\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Spring02\

		// => 톰캣서버에 배포 후 : 서버내에서의 위치가 됨
		//    D:\MTest\IDESet\apache-tomcat-9.0.41\webapps\Spring02\

		String realPath = request.getRealPath("/");
		System.out.println("** realPath => " + realPath);
		
		// 1.2) 위의 값(realPath)을 이용해서 실제저장위치 확인
		// => 개발중인지, 배포했는지에 따라 결정
		if(realPath.contains(".eclipse"))  {// 개발중(배포전 : eclipse 개발환경)
			realPath = "C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\uploadImages\\";
		}else {
			realPath += "resources\\uploadImages\\";
			// += 를 해주는 이유
			// 필요한 경로 : D:\MTest\IDESet\apache-tomcat-9.0.41\webapps\Spring02\resour...\~ploadImages\
		}
		
		// ** 폴더 만들기 (File 클래스활용)
		// => 위의 저장경로에 폴더가 없는 경우 (uploadImage가 없는경우)  만들어 준다
		// => file.exists()
		//   -> 파일 또는 폴더가 존재하는지 리턴
		//   -> 폴더가 아닌, 파일존재 확인하려면 file.isDirectory() 도 함께 체크해야함. 
		//     ( 참고: https://codechacha.com/ko/java-check-if-file-exists/ )
		// => file.isDirectory() : 폴더이면 true 그러므로 false 이면 file 이 존재 한다는 의미가 됨. 
		// => file.isFile()
		//   -> 파일이 존재하는 경우 true 리턴,
		//      file의 Path 가 폴더인 경우는 false 리턴
		
		File f1 = new File(realPath);
		if(!f1.exists()) {
			f1.mkdir();
			// => realPath 가 존재하지 않으면 생성
		}
		
		// => 기본이미지(basicman4.png)가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
		f1 = new File(realPath + "basicman4.png"); // uploadImages 폴더에 파일존재 확인을 위함
		if( !f1 .isFile() ) { // 존재하지 않는다면
			String basicImagePath 
					= "C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\images\\basicman4.png";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트링 생성
			FileOutputStream fo = new FileOutputStream(f1); 
			// => 목적지 파일(realPath + "basicman4.png") 출력바이트스트링 생성 
			FileCopyUtils.copy(fi, fo);
		}
		// => 기본 이미지 지정하기
		String file1, file2 = "resources/uploadImages/basicman4.png";

		// ** MultipartFile
		// => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
		//    -> String getOriginalFilename(), 
		//    -> void transferTo(File destFile),
		//    -> boolean isEmpty()

		// 1.3) 저장경로 완성
		MultipartFile uploadfilef = dto.getUploadfilef();
		if( uploadfilef != null && !uploadfilef.isEmpty() ) {
			// => image_File 을 선택함 -> 저장 (저장경로 : realPath + 파일명)
			// 1.3.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)
			
			// 1.3.2) Table 저장경로 완성 (file2)
			file2 = "resources/uploadImages/" + uploadfilef.getOriginalFilename(); 
		}
		// 1.4) 완성된 경로를 dto 에 set
		dto.setUploadfile(file2);
		
		// ==========================================================================
		
	    // ** Transaction_AOP 적용 ********************* 
		// 1. 준비: pom.xml (dependency) 확인
		// =>  AspectJ(기본제공), AspectJ Weaver(추가)

		// 2. servlet-context.xml AOP 설정

		// 3. Rollback Test
		// 3.1) Aop xml 적용전 => insert1 은 입력되고, insert2 에서  500_Dupl..Key  오류 발생
		// 3.2) Aop xml 적용후 => insert2 에서 오류발생시 모두 Rollback 되어 insert1, insert2 모두 입력 안됨 

		// 3.1) Transaction 적용전 : 동일자료 2번 insert
		// => 첫번째는 입력완료(commit) 되고, 두번째자료 입력시 Key중복 오류발생 (500 발생)
		// 3.2) Transaction 적용후 : 동일자료 2번 insert
		// => 첫번째는 입력완료 되고, 두번째 자료입력시 Key중복 오류발생 하지만,
		//    rollback 되어 둘다 입력 안됨
		// service.insert(dto); // Transaction_Test, insert1 

		
		
		// 2. Service 처리
		if(service.insert(dto) > 0) { // Transaction_Test, insert2
			model.addAttribute("message" , "회원가입 성공 로그인 후 이용하세요.");
		}else {
			model.addAttribute("message" , "회원가입 실패 다시 하세요.");
			uri = "member/memberJoin";
		}

		// 3. View
		return uri;
	}

	// ===============================================================
	
	// Member Update
	// => 요청 : home에서 내정보수정 -> 내정보수정 Form (memberUpdate.jsp) 출력
	// => 수정 후 submit -> 수정 Service 
	// 		-> 성공 : detail 
	//      -> 실패 : 재시도 유도 (memberUpdate.jsp)
	//@RequestMapping(value = "/mupdate", method = RequestMethod.POST)
	// ** Member Update
		// => 요청: home 에서 내정보수정 -> 내정보수정Form (memberUpdate.jsp) 출력
		// => 수정후 submit -> 수정 Service 
		//		-> 성공: detail
		//		-> 실패: 재시도 유도 (memberUpdate.jsp)
		//@RequestMapping(value="/mupdate", method=RequestMethod.POST)
		@PostMapping(value="/mupdate")
		public String memberUpdte(HttpServletRequest request, 
								  MemberDTO dto, Model model) throws IOException {
			
			// => 처리결과에 따른 화면 출력을 위해서 dto 의 값을 Attribute에 보관
			model.addAttribute("apple", dto);
			String uri="member/memberDetail";
			
			// passwrod 수정과 나머지 컬럼 수정을 분리
			// => mapper 에서 이것을 구분 할 수 있도록 password 값을 null 로
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
				if(realPath.contains(".eclipse"))  {// 개발중(배포전 : eclipse 개발환경)
					realPath = "C:\\IDESET\\mTest\\Mywork\\demo\\src\\main\\webapp\\resources\\uploadImages\\";
				}else {
					realPath += "resources\\uploadImages\\";
				}
				
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
		
		@GetMapping(value = "/pUpdateForm")
		public void pUpdateForm() {
			// viewName 생략 -> 요청명이 viewName 이 됨
		}
		
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
		// ===============================================================

		// Member Delete : 탈퇴
		// => 삭제대상 : Parameter 로 전달, dto 에 자동 set
		// @RequestMapping(value = "/mdelete", method = RequestMethod.GET)
		@GetMapping(value = "/mdelete")
		public String mdelete(HttpSession session, MemberDTO dto, Model model, RedirectAttributes rttr) {

			// 1) 본인 탈퇴
			// 결과 : message(삭제 성공/실패), home.jsp, session 무효화

			// 2) 관리자 강제 탈퇴
			// 결과 : message(삭제 성공/실패), memberList.jsp, 

			// => 본인탈퇴 or 관리자에 의한 강제탈퇴 구분이 필요
			//    dto 의 id 와 session 의 loginID 와 같으면 본인탈퇴,
			// 	 다르면서 session 의 loginID 값이 "admin" 이면 강제탈퇴

			String uri = "redirect:/";
			if( service.delete(dto) > 0) {
				rttr.addFlashAttribute("message", "탈퇴 성공 1개월 후 재가입 가능");
				if( ((String)session.getAttribute("loginID")).equals("admin") ) {
					// 관리자에 의한 강제탈퇴
					uri = "redirect:memberList";
				}else {
					// 본인 직접 탈퇴
					session.invalidate();
				}
			}else {
				rttr.addFlashAttribute("message", "탈퇴 실패");
			}
			return uri;
		}

	// ===============================================================
	
}
