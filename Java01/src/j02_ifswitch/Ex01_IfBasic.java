package j02_ifswitch;

public class Ex01_IfBasic {

	public static void main(String[] args) {
		// 삼항식
		boolean rain = true; // 지역변수 : 메서드내에서만 유효
		String doing = (rain == true) ? "Study_Java" : "그래도 공부"; 
		
		// if
		if (rain == true) doing = "Study_Java";
		else doing = "그래도 공부"; 
		
		// if : 복합구문(Compound statement)
		// => 여러문장 경우 중괄호를 사용하여 문장들을 그룹핑
		if (rain) {
			int i = 100; // 지역변수 : 정의된 { } 블럭내에서만 유
			System.out.println("비오면");
			System.out.println(doing);
			System.out.println("비오면 우산 필요함, i = " + i);
		}else {
			System.out.println("비 안오면");
			System.out.println(doing);
			System.out.println("비 안오면 양산 필요함");
		}
		
		// ------------------------------------------- 1번 예제 (날씨가 좋으면 테니스를 친다
		
		// 복합조건식
		// => 날씨가 좋고 공휴일이면 공원에 산책을 간다
		//    아니면 집에서 요리를 한다.
		// => rain 은 false , day 는 "Red" 이면
		String day = "Red";
		if (rain == false) {
			if (day == "Red") {
				System.out.println("산책을 간다.");
			}
		}else {
			System.out.println("집에서 쉰다.");
		}
		// -------------------------------------------- 2번 예제 내가 한거
		if (!rain && day == "Red") {
			System.out.println("산책을 간다.");
		}else {
			System.out.println("집에서 쉰다.");
		}
		
		// -------------------------------------------- 우리조가 한거
		
		// 중첩 if 구문
		// => if 문 내에 if 문 포함
		if (!rain) {
			if (day == "Red") {
				System.out.println("산책을 간다.");
			}else {
				System.out.println("집에서 쉰다.");
			}
		}else {
			System.out.println("집에서 쉰다.");
		}
	}

}
