package j10_Exception;

//** try ~ catch ~ finally
//=> finally 블럭은 무조건 시행 
//=> 1) 정상실행
//   2) Exception : ArrayIndexOutOfBoundsException 
//   3) 반복문 제어할수있는 구문: return, break, continue 

public class Ex03_finally {

	public static void main(String[] args) {
		
		int[] price = {100, 200, 300};
		
		// Test 1)
		// for (int i=0; i<price.length; i++) {
		
		// Test 2)
		for (int i=0; i<=price.length; i++) {
			if (i==1) return; // 비교: finally와 무관
			try {
				// Test 3) continue, break, return
				// => try 블럭에 진입하면 무조건 finally 는 실행한다
				if (i==1) return; // void 메서드에서 사용하면 메서드 무조건 종료
				if (i==2) break;  // 무조건 반복문종료
				if (i==3) continue; 
				// => 나머지 문장 실행하지않고 다음 반복문 진행 
				//	  그러나 i=4 면 반복문 종료하게됨   
				
				System.out.printf("** price[%d]=%d \n", i, price[i]);
			}catch (Exception e) {
				System.out.println("** Exception => "+e.toString());
			}finally {
				System.out.println("** finally_무조건 실행, i => "+i);
			} //finally
			System.out.println("** for_endLine, i => "+i);
		} //for
		
		System.out.println("** Program 정상종료 **");
	} //main
} //class
