package j04_array;

import java.util.Random;
import java.util.Scanner;

//반복문 숙제
//=> 4-6, 4-7, 4-9, 4-10


public class Ex99_dummy {

	public static void main(String[] args) {

		// 4 - 6
		for(int i = 1; i <= 6; i++) {
			for(int j = 1; j <= 6; j++) {
				if(i + j == 6) {
					System.out.println(i + " + " + j + " = " + (i + j));
				}
			}
		}

		// ------------------------------------- 4 - 6

		// 4 - 7

		String str = "12345";
		int sum = 0;

		for (int i = 0; i < str.length(); i++) {
			sum += str.charAt(i) - '0';
		}
		System.out.println("sum = " + sum);

		// ------------------------------------- 4 - 7

		int num = 12345;
		sum = 0;

		while (num > 0) {

			sum += num % 10;
			num /= 10;

		}

		System.out.println("sum = " + sum);

		// ------------------------------------- 4 - 9

		Random x = new Random();
		int answer = x.nextInt(10) + 1;
		int input = 0;
		int count = 0;

		Scanner sc = new Scanner(System.in);

		do {
			count++;
			System.out.println("1 ~ 10 정수를 입력하세요.");
			input = sc.nextInt();

			if(answer > input) {
				System.out.println("더 크게해라");
			}else if (answer < input){
				System.out.println("더 작게해라");
			}else {
				System.out.println("정답");
				System.out.println("몇번 시도했냐고? " + count);
				break;
			}
		}while(true);

		// ------------------------------------- 4 - 10

	}

}
