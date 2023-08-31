package j03_forWhile;

//** Continue 
//=> Continue 문 이하의 구문을 실행하지 않고 다음 반복문 진행

//** Break
//=> 반복문 탈출

//** Label
//=> continue, break 가 적용될 반복문을 지정할 수 있도록 해줌
//=> 위치를 표시함
//  반드시 ":" 표시,
// 반드시 반복문 바로 위에 위치함.
// -> Lable 다음에 반복문외의 문장이 오면 break, continue 에서 인식안됨 오류

//** 과제
//=> 5층건물에 각 층마다 7개의 방이 있고 이것을 [층,호실] 출력하기 
//=> 4층 4호 는 창고이기 때문에 출력하지 않는다.

// [1,1] [1,2] ... [1,7] 
//....................
// [5,1] [5,2] ... [5,7]
public class Ex05_ConBreak {

	public static void main(String[] args) {

		// 1) 모든방 번호 출력하기

		System.out.println(" 1) 모든방 번호 출");
		
		for (int i = 1; i<=5; i++) {
			for(int j =1; j<=7; j++) {

				System.out.printf("[%d , %d]", i, j);
			}
			System.out.println("");
		}

		// -------------------------------- 1

		// 2) 4층 4호는  출력하지않는다.

		System.out.println("\n 2) 4층 4호는  출력하지않는다.");
		
		for (int i = 1; i<=5; i++) {
			for(int j =1; j<=7; j++) {
				if(i == 4 && j == 4) {
					continue;
				}
				System.out.printf("[%d , %d]", i, j);
			}
			System.out.println("");
		}

		// -------------------------------- 2

		// 3) 3층은 5호 까지만 있다.

		System.out.println("\n 3) 3층은 5호 까지만");
		
		for (int i = 1; i<=5; i++) {
			for(int j =1; j<=7; j++) {
				if(i == 4 && j == 4) {
					continue;
				}
				if(i == 3 && j == 6) {
					break;
				}
				System.out.printf("[%d , %d]", i, j);
			}
			System.out.println("");
		}

		// -------------------------------- 3

		// Label 을 활용하여 완전탈출
		// => continue, break 를 원하는 반복문 에 적용
		// => 원하는 반복문 위에 Labeling, 사용     
		// => Labeling 주의사항 
		//  - 식별자 끝에 : 사용 
		//  - 뒤에는 반드시 반복문이 와야됨.
		
		System.out.println("\n 4) Label 적용 _continue");
		
		out: 
		for (int i = 1; i<=5; i++) {
			for(int j =1; j<=7; j++) {
				if(i == 4 && j == 4) {
					System.out.println("");
					continue out;
				}
				// => 반복문의 나머지 구문을 수행하지 않고 
				//Labeling 된 다음 증가값 진행
				System.out.printf("[%d , %d]", i, j);
			}
			System.out.println("");
		}

		// -------------------------------- 4

		System.out.println("\n 5) Label 적용 _break");
		
		out: 
			for (int i = 1; i<=5; i++) {
				for(int j =1; j<=7; j++) {
					if(i == 4 && j == 4) {
						System.out.println("");
						break out;
					}
					// => 반복문의 나머지 구문을 수행하지 않고 
					//Labeling 된 반복문을 무조건 탈출
					System.out.printf("[%d , %d]", i, j);
				}
				System.out.println("");
			}

	}

}
