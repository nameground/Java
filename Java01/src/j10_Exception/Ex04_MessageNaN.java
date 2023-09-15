package j10_Exception;

public class Ex04_MessageNaN {

	public static void main(String[] args) {
		
		// ** by Zero ( 나누기 0 )
		// => 정수형 연산 : ArithmeticException 
		// => 실수형 연산 : Exception 이 일어나지않음
		//                Wrapper 클래스의 메서드 활용
		try {
			// => 실수형 연산 : Exception 이 일어나지않음
			double d1 = 1.5/0.0; // Infinity : 무한수
			double d2 = 1.5%0.0; // NaN : Not a Number (숫자가 아님)
			
			System.out.printf(" 실수 by zero Test: d1=%f, d2=%f, d1*100=%f \n", d1, d2, d1*100 );
			// => 실수 by zero Test: d1=Infinity, d2=NaN, d1*100=Infinity 
			// => 그러므로 Exception 으로 대응할 수 없음
			if ( Double.isInfinite(d1) || Double.isNaN(d2) ) {
				System.out.println("** Zero 연산 허용불가 **");
			}else 
				System.out.printf(" 실수 by zero Test: d1=%f, d2=%f, d1*100=%f \n", d1, d2, d1*100 );
			
			// => 정수형 연산: ArithmeticException 
			System.out.printf(" 정수 by zero Test: 100/0=%d \n", 100/0 );
			
		} catch (Exception e) {
			System.out.println("** Exception, toString => "+e.toString());
			System.out.println("** Exception, getMessage => "+e.getMessage());
			System.out.println("** Exception, printStackTrace => ");
			e.printStackTrace();
			// => e.toString 을 포함한 순차적인 실행경로 등이 출력되어
			//    예외발생의 원인과 경로 파악에 도움. 
		} finally {
			System.out.println("** finally_무조건 실행 **");
		}
		System.out.println("** Program 정상종료 **");
	} //main

} //class
