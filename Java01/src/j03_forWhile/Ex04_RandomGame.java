package j03_forWhile;

import java.util.Random;
import java.util.Scanner;

//** 숫자 맞추기 게임
//=> 1~10 범위에서 숫자 하나를 입력받아
//=> Random 클래스의 추출번호 와 일치하면 금메달
//=> 차이가 1 이면 은메달, 차이가 2면 동메달, 아니면 꽝
//=> Math 클래스를 이용하세요 ~~

// => 반복문 적용 : 당첨될때까

public class Ex04_RandomGame {

	public static void main(String[] args) {

		// 1) Random Number 구하기
		Random rn = new Random();
		int happyNumber = rn.nextInt(10) + 1; // 1 ~ 10 내에서 임의의 정수를 제공

		// 2) My Number 입력받기
		// => 당첨될때까지
		Scanner sc = new Scanner(System.in);
		int myNumber = 0;
		int abs = 0; // 차이값
		
		while(true) {
			// 2.1) myNumber 입력 받기
			System.out.println("1~10 숫자 입력 ");
			myNumber = Integer.parseInt(sc.nextLine());
			abs = Math.abs(happyNumber - myNumber);
			
			// 2.2) 확인
			// => 일치, 차이가 1 or 2 : 반복문 탈출 (break;)
			//    아니면 계속 진행
			if(abs == myNumber) {
				System.out.println("축");
				break;
			}else if (abs == 1) {
				System.out.println("1깝");
				break;
			}else if (abs == 2){
				System.out.println("2깝");
				break;
			}else {
				System.out.println("집 못가겠네 ㅋ");
			}

		}
		// 결과 처리
		System.out.printf("당첨번호 : %d, 입력된 번호 : %d," , happyNumber,myNumber );
	}

}
