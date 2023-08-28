package j01_basic;

// 치환, 기본자료형의 Wrapper class, Overflow

public class Ex03_Variable02 {

	public static void main(String[] args) {

		// 치환
		// => 값의 맞교환 (cup1 의 값과 cup2의 값을 교환)
		// => box 내에 여러값이 들어있는데 그중 어떤값이 cup1 , cup2 의 값이 될지 모른다고 가정
		// => 중간 보관을 위한 같은 타입의 임시 변수가 필요함
		// => "=" 동일성을 의미하는것이 아니고 대입연산자   
		
		String cup1 = "사이다";
		String cup2 = "콜라";
		
		String temp = cup1;
		cup1 = cup2;
		cup2 = temp;
		
		System.out.println(cup1);
		System.out.println(cup2);
		
		// ------------------------------------------------- 치환
		
	    //  기본자료형의 Wrapper Class
	    // => 기본 자료형을 지원해주는 클래스 (모든 기본자료형에 있음)
	    //    해당 클래스명은 기본 자료형의 첫글자를 대문자로 하면됨
		
		System.out.println("정수형의 Literal 의 범");
		System.out.println("byte => " + Byte.MIN_VALUE + "~" + Byte.MAX_VALUE);
		System.out.println("short => " + Short.MIN_VALUE + "~" + Short.MAX_VALUE);
		System.out.println("int => " + Integer.MIN_VALUE + "~" + Integer.MAX_VALUE);
		System.out.println("long => " + Long.MIN_VALUE + "~" + Long.MAX_VALUE);
	      
		System.out.println("실수형의 Literal 의 범");
		System.out.printf("float => %f ~ %f \n", Float.MIN_VALUE , Float.MAX_VALUE);
		System.out.printf("double => %f ~ %f \n", Double.MIN_VALUE , Double.MAX_VALUE);
		
		// ------------------------------------------------- 기본자료형의 Wrapper Class
		
		// 오버플로우 (Overflow) / 언더플로우 (Underflow)
		// 1) 정수형
		// => 컴파일, 런타임 오류 없음
		short sMax = Short.MAX_VALUE;
		short sMin = Short.MIN_VALUE;
        // sMax = Short.MAX_VALUE+1; // Error
		
		// => short 오버플로우 (Overflow) Test : 최소값과 동일
		
		System.out.printf("s_Overflow1 => sMax = %d, sMax+1 = %d \n ", sMax, (sMax + 1)); // 정상적 int 연산
		System.out.printf("s_Overflow2 => sMax = %d, sMax+1 = %d \n ", sMax, (short)(sMax + 1));
		// => 최대값(32767) : 1111 1111 1111 1111 + 1
		//             ->  1 0000 0000 0000 0000 -> 우축의 2byte 만 남게됨
		
		// => short 언더플로우 (Underflow) Test : 최대값과 동
		
		System.out.printf("s_Underflow1 => sMin = %d, sMin-1 = %d \n ", sMin, (sMin - 1)); // 정산적 int 연산
		System.out.printf("s_Underflow2 => sMin = %d, sMin-1 = %d \n ", sMin, (short)(sMin - 1));
		
		
		// 2) 실수
		// => by zero Test
		// => 컴파일, 런타임 오류 없음
	    float f = 1234.567f;
	    System.out.println("실수형 by 0, 나누기 => " + (f/0));
	    // => Infinity (무한수) -> 오버플로우(Overflow) 
	    
	    System.out.println("실수형 by 0, 나머지 => " + (f%0));
	    // => NaN (Not a Number) -> 언더플로우(Underflow)
	    
	    // 비교
	    // => 정수형,by zero
	    // 실행시 컴파일 오류 없으나, 런타임 오류 발생 (java.lang.ArithmeticException: / by zero)
	    System.out.println("정수형 by 0, 나누기 => " + (sMax/0));
	    System.out.println("정수형 by 0, 나머지 => " + (sMax%0));
		
	}

}
