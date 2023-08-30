package j02_ifswitch;

import java.util.Scanner;

//** 실습 과제 : 
//1. 월을 입력 받아서 (Scanner 사용)
//2. 몇일까지 인지,  
// => 1,3,5,7,8,10,12월 => ?월은 31일 까지 입니다.
// => 4,6,9,11 월 => ?월은 30일 까지 입니다.
// => 2 월 => ?월은 29일 까지 입니다.
//3. 무슨 계절인지 출력 하기
//  => 3~5:봄 , 6~8:여름, 9~11:가을, 12~2:겨울

//** switch case 구문으로 작성 하세요 ~~

public class Ex04_SwitchMonth {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("달을 입력해주세요.");
		int month = Integer.parseInt(sc.nextLine());
		int day = 0;

		switch(month) {
		case 1: 
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: day = 31; break;

		case 4:
		case 6:
		case 9:
		case 11: day = 30; break;

		case 2: day = 28; break;
		}
		/* => new_version
        Java 버젼 15이상 부터 가능
    	switch (month) {
       	case 1,3,5,7,8,10,12: days = 31; break;
        case 4,6,9,11: days = 30; break;
        case 2:  days = 29; break;
        } //switch
		 */

		String season = "";
		switch(month){
		case 3: case 4: case 5: season = "봄"; break;
		case 6: case 7: case 8: season = "여름"; break;
		case 9: case 10: case 11: season = "가을"; break;
		case 12: case 1: case 2: season = "겨울"; break;
		}
		System.out.printf("%d월은 %d까지, %s입니다.", month,day,season);
	}

}

