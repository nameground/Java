package j02_ifswitch;

import java.util.Random;
import java.util.Scanner;

//** 숫자 맞추기 게임
//=> 1~10 범위에서 숫자 하나를 입력받아
//=> Random 클래스의 추출번호 와 일치하면 금메달
//=> 차이가 1 이면 은메달, 차이가 2면 동메달, 아니면 꽝
//=> Math 클래스를 이용하세요 ~~

public class Ex05_RandomGame {

	public static void main(String[] args) {
		// 1) Random Number 구하기
		Random rn = new Random();
		int happyNumber = rn.nextInt(10) + 1; // 1 ~ 10 내에서 임의의 정수를 제공
		
		// 2) My Number 입력받기
		System.out.println("1 ~ 10 사이 정수를 입력하세요.");
		Scanner sc = new Scanner(System.in);
		
		int myNumber = Integer.parseInt(sc.nextLine());
		
		// 결과 처리
		// => 차이 : happyNumber - myNumber 의 절대값
		// => Math.abs(happyNumber - myNumber)
		if(Math.abs(happyNumber - myNumber) == 0) {
			System.out.println("축");
		}else if (Math.abs(happyNumber - myNumber) == 1) {
			System.out.println("1깝");
		}else if (Math.abs(happyNumber - myNumber) == 2) {
			System.out.println("2깝");
		}else {
			System.out.println("집가라 걍;;");
		}
		// --------------------------------------------------------- 내가 한 Ex

//		// 1) Random Number 구하기
//		Random rn = new Random();
//		int happyNumber = rn.nextInt(10) + 1; // 1 ~ 10 내에서 임의의 정수를 제공
//		
//		// 2) My Number 입력받기
//		System.out.println("1 ~ 10 사이 정수를 입력하세요.");
//		Scanner sc = new Scanner(System.in);
//		int myNumber = sc.nextInt();
//		
//		
//		// 결과 처리
//		// => 차이 : happyNumber - myNumber 의 절대값
//		// => Math.abs(happyNumber - myNumber)
//		String medal = "꽝";
//		switch(Math.abs(happyNumber - myNumber)) {
//		case 0 : medal = "Gold"; break;
//		case 1 : medal = "Silver"; break;
//		case 2 : medal = "Bronze"; break;
//		default : medal = "꽝";
//		}
//		
//		System.out.printf("당신의 당첨번호 = %d , myNumber = %d, 결과는 %s \n",happyNumber, myNumber, medal);
//		// --------------------------------------------------------- 선생님이 한 Ex
	}

}
