package j03_forWhile;

// 반복문 숙제
// => 4-6, 4-7, 4-9, 4-10

public class Ex06_Star {

	public static void main(String[] args) {

		// 1) 반삼각형
		// => * 을 1개 부터 시작해서 갯수를 늘려가면서 9 line 출력 
		// => 각 행별로 행번호 갯수만큼 출력하기
		System.out.println("1) 반삼각형");
		for (int i = 1; i <= 9; i++) {
			for(int j= 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		// -------------------------------------------------- 1
		
		// 2) 피라미드(삼각형)
		System.out.println("\n2) 피라미드");
		
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <= i + 4 ; j++) {
				if(j < 6 - i) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			System.out.println("");
		}
		// -------------------------------------------------- 조에서 한 피라미드
		
		// 2) 피라미드(삼각형)
		// => 5행, 9컬럼(마지막행, 각 line * 2 - 1)
		
//		System.out.println("\n2) 피라미드");
//		int line = 5;
//		for (int i = 1; i <= line; i++) {
//			
//			// => 반복문1: space
//			for(int j = 1; j <= line - i; j++) {
//				System.out.print(" ");
//			}
//			// => 반복문2: *
//			for(int j = 1; j <= (i * 2 - 1); j++) {
//				System.out.print("*");
//			}
//			System.out.println("");
//		}
		
		// -------------------------------------------------- 선생님이 한 피라미드
		// -------------------------------------------------- 2
		
		
		// 3) 다이아몬드
		System.out.println("\n3) 다이아몬드");
		// 상부출력
		int line = 7;
		for (int i = 1; i <= line; i++) {
			
			// => 반복문1: space
			for(int j = 1; j <= line - i; j++) {
				System.out.print(" ");
			}
			// => 반복문2: *
			for(int j = 1; j <= (i * 2 - 1); j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		// 하부출력
		// => 총 그리는 line 갯수(위의 line - 1 = 4) ~ 1
		for(int i = line - 1; i >= 1; i--) {
			
			// 반복문 1: space
			for (int j = 1; j <= line - i; j++) {
				System.out.print(" ");
			}
			// 반복문 2: *
			for(int j = 1; j <= (i * 2 - 1); j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		
		// -------------------------------------------------- 3
		
		// 4) 모래시계
		
		System.out.println("\n4) 모래시계");
		// 상부출력
		for(int i = line; i >= 1; i--) {
			
			// 반복문 1: space
			for (int j = 1; j <= line - i; j++) {
				System.out.print(" ");
			}
			// 반복문 2: *
			for(int j = 1; j <= (i * 2 - 1); j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		// 하부출력
		for (int i = 2; i <= line; i++) {
			
			// => 반복문1: space
			for(int j = 1; j <= line - i; j++) {
				System.out.print(" ");
			}
			// => 반복문2: *
			for(int j = 1; j <= (i * 2 - 1); j++) {
				System.out.print("*");
			}
			System.out.println("");
		}





	}

}
