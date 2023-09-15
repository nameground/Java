package j10_Exception;

//** Exception 계층구조 
// => Object -> Throwable -> Exception, Error, 
//						Exception -> RuntimeException, IOException 

// => Error: 프로그램 코드에 의해 수습될 수 없는 심각한 오류
// => Exception
//	-> 프로그램 코드에 의해 수습될 수 있는 오류로, 프로그래머가 예외처리를 할 수 있음
//	-> 반드시 예외 처리를 해야하는 Checked Exception과,
//	   예외 처리를 하지 않아도 되는 Unchecked Exception으로 나뉜다.
// => IOException
//	-> Checked Exception
//	-> 파일 및 입출력 작업에서 발생하는 Exception
// => RuntimeException
//	-> Unchecked Exception
//	-> 개발자의 실수로 발생하는 Exception.

// ** Exception Test
// => Exception 처리하지않는 경우
// 		-> Exception 발생위치에서 비정상종료 
// => Exception 처리하는 경우
//		-> Exception 발생시 대응을 하여 정상진행가능 

public class Ex01_Basic {

	public static void main(String[] args) {
		int x=6, y=3, result=0;
		String s="123000";
		String[] names = {"Apple", "Banana", "Coffee"};
		// Test1) Exception 처리하지않는 경우 
		// result=x/0; 
		// => 컴파일오류는 없음
		// => Exception 발생 행에서 해당하는 Exception 객체를 생성해 메시지를 출력하고 
		//    비정상종료 ( java.lang.ArithmeticException: / by zero )
		System.out.println("** result1 => "+result);
		
		// Test2) Exception 처리하는 경우 
		try {
			result=x/y;
			System.out.println("** result2 => "+result);
			System.out.println("** result3 => "+ (result+=Integer.parseInt(s)));
			System.out.println("** 배열 Test => "+names[2]);
			// => 배열크기 음수지정 : NegativeArraySizeException 발생
			names = new String[5];
			System.out.println("** names.length => "+names.length);
			s=null;
			System.out.println("** NullPointerException Test => "+s.length());
			
		} catch(ArithmeticException e) {
			System.out.println("** ArithmeticException => "+e.toString());	
		} catch(NumberFormatException e) {
			System.out.println("** NumberFormatException => "+e.toString());	
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("** ArrayIndexOutOfBoundsException => "+e.toString());	
			// => 인덱스 음수 지정시에도 동일한 Exception 발생 
		} catch(NullPointerException e) {
			System.out.println("** NullPointerException => "+e.toString());	
		} catch(Exception e) {  
			// ** Exception 에 다형성 적용
			// => Exception e = new 후손()
			// => 즉, Exception 의 모든 후손 Exception 들이 이 catch 블럭으로 전달됨 
			//    ArithmeticException, NumberFormatException....
			// => 그러므로 각 Exception 별로 처리하는것도 가능
			//    이때는 조상인 Exception 객체는 제일 마지막에 정의해야함 (순서중요, 컴파일오류)
			System.out.println("** Exception => "+e.toString());
		} finally {
			System.out.println("** finally => 무조건 실행 **");
		}
		
		System.out.println("** 정상 종료 **");

	} //main

} //class
