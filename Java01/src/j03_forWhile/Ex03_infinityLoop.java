package j03_forWhile;

public class Ex03_infinityLoop {

	public static void main(String[] args) {
		
		// 1) for 무한루프
		
		int count = 0;
		System.out.println("무한 Loop Test => for");
		
		for(; ;) { // 무조건
			System.out.println("for_count => " + count++);
			
			if (count > 100) {
				break; // 무조건 탈
			}
		}
		
		// ------------------------------------------------- for
		
		// 2) while 무한루프
		count = 0;
		System.out.println("무한 Loop Test => while");
		
		while(true) {
			
			System.out.println("while_count => " + count++);
			if (count > 100) {
				break; // 무조건 탈
			}
			
		}
		
		// ------------------------------------------------- while
		
		// 3) do_while 무한루프
		
		count = 0;
		System.out.println("무한 Loop Test => do_while");
		
		do {
			
			System.out.println("while_count => " + count++);
			if (count > 100) {
				break; // 무조건 탈
			}
			
		}while(true);
		
		// ------------------------------------------------- do_while
		
		// 4) 조건문 : 무조건 실행
		
		if(true) System.out.println("TRUE");
		// else System.out.println("FALSE"); // 무한 if 문에서 else로 갈 수 없기에 오류코
		
		// ------------------------------------------------- if

	}

}
