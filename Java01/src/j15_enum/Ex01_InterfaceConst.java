package j15_enum;

interface ScaleI{
	// 상수정의
	// => public static final String DO = "도";
	// => 상수만 정의 가능하므로 생략
	int DO = 0;
	int RE = 1;
	int MI = 2;
	int FA = 3;
	int SOL = 4;
	int RA = 5;
	int SI = 6;

}

//(정석기초 475p)
//** 열거형(enum) 상수 사용
//=> 정의 : 열거형은 서로 연관된 상수들의 집합
//=> interface 와 비교

//** 열거형의 특징
//=> 상수의 사용을 편리하게 지원.
//=> 의미가 있는 단어를 상수로 사용하기 편리함.
//=> 값과 타입을 동시에 확인하기때문에 안전하고 효율적인 코딩 가능
//=> 열거형 내부에 생성자, 필드, 메소드를 가질 수 있어서 단순히 상수가 아니라 더 많은 역할이 가능함. 

enum ScaleE{
	DO, RE, MI, FA, SOL, RA, SI
}


public class Ex01_InterfaceConst {

	public static void main(String[] args) {
		// Test1) interface 에 정의된 상수 사용하기
		// => switch case 에 적용하기
		int key = ScaleI.MI;
		switch(key) {
		case 0 : System.out.println("~~ 도 ~~ "); break;
		case 1 : System.out.println("~~ 레 ~~ "); break;
		case 2 : System.out.println("~~ 미 ~~ "); break;
		case 3 : System.out.println("~~ 파 ~~ "); break;
		case 4 : System.out.println("~~ 솔 ~~ "); break;
		case 5 : System.out.println("~~ 라 ~~ "); break;
		case 6 : System.out.println("~~ 시 ~~ "); break;
		default : System.out.println("~~도, 레, 미, 파, 솔, 라, 시~~");
		}

		// Test2) enum 사용하기
		// => 의미가 있는 단어를 상수로 사용하하므로
		//    값과 타입을 동시에 확인하기 때문에 안전하고 효율적인 코딩 가능  

		ScaleE eKey = ScaleE.RA;
		System.out.println("eKey => " + eKey);
		//eKey = null;
		// => 컴파일 오류는 없음
		//    default 로 가지않고 런타임오류(java.lang.NullPointerException) 발생
		// => 안전하게 enum ScaleE 에 정의된 값만 사용가능 (enum 의 장점)
		switch(eKey) {
		case DO : System.out.println("~~ 도 ~~ "); break;
		case RE : System.out.println("~~ 레 ~~ "); break;
		case MI : System.out.println("~~ 미 ~~ "); break;
		case FA : System.out.println("~~ 파 ~~ "); break;
		case SOL : System.out.println("~~ 솔 ~~ "); break;
		case RA : System.out.println("~~ 라 ~~ "); break;
		case SI : System.out.println("~~ 시 ~~ "); break;
		default : System.out.println("~~도, 레, 미, 파, 솔, 라, 시~~");
		}


	}

}
