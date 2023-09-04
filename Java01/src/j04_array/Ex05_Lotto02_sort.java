package j04_array;

import java.util.Arrays;
import java.util.Random;

//** Lotto 번호 생성기 만들기 2
//=> 찾기(Search) & 정렬(Sort)

public class Ex05_Lotto02_sort {

	public static void main(String[] args) {

		// 1) 배열정의
		int lotto [] = new int[6];



		// 2) Random 이용하여 숫자추출 & 배열에 담기
		// => java.util 에 있으므로 import 필요하고, new 선언후 사용가능함.
		// => Random() : 호출시마다 현재시간을 이용한 종자값이 자동 설정됨 
		//    Random(long seed) : 인자값으로 주어진 종자값이 설정됨 
		//    종자값 : 난수 만드는 알고리즘에서 사용되는 값
//		           ( 같으면 같은난수 얻음 )
		//
		// => Random().nextInt(큰수 - 작은수 + 1) + 작은수 
		//    Random().nextInt() , Random().nextLong() , Random().nextBoolean()
		//    Random().nextDouble() , Random().nextFloat()
		Random x = new Random();

		for(int i = 0; i < lotto.length; i++) {

			lotto[i] = x.nextInt(45) + 1;

			// 중복확인 

			// => 숫자추출 후, lotto에 담기 전 
			//  -> 임시변수 필요, 중복존재하면 안담고, 존재하지않으면 담는다.

			// => 숫자추출 후, lotto에 딤기 후
			//  -> 중복존재하면 i 값을 후진시켜 다시 담도록 <- 이 방법을 사용하여 코

			// 중복확인 방법
			// => 이미 lotto 배열에 담겨있는 Data 와 동일성 비
			//    (그러므로 반복문 필요)

			for(int j = 0; j <= i - 1; j++) {
				if (lotto[i] == lotto[j]) {
					--i;
					break;
				}
			}
		}

		// 3) 출력 (정렬전)

		System.out.println("Lotto 정렬전 => " + Arrays.toString(lotto));

		// 4) 오름차순 정렬
		// => 순차정렬 (Sequence Sort)
		// => 정렬 알고리즘에서 가장 간단하고 기본이 되는 알고리즘으로
		//    배열의 처음과 끝을 탐색하면서 차순대로 정렬하는 가장 기초적인 정렬 알고리즘
		// ** 정렬 알고리즘 : 삽입(insert)정렬, 합병(Merge)정렬, 퀵(Quick)정렬...
		// => https://blog.naver.com/tepet/221690306235 
		
		for(int i = 0; i < lotto.length; i++) {
			for (int j = i+1; j < lotto.length; j++) {
				if(lotto[i] > lotto[j]) {
					int temp = lotto[i];
					lotto[i] = lotto[j];
					lotto[j]= temp;
				}
			}
		}
		System.out.println("Lotto 정렬후 => " + Arrays.toString(lotto));
		
		// 배열 Wrapper Calss : Arrays
		// => Arrays 의 주요 메서드 equals(null, null) , sort(null)
		
		
		// 5) myNumber 생성 후 비교하기
		// => 배열정의, Random 추출후, 중복확인후 배열에 담기,
		// => 정렬, 비교 (equals)
		
		int myNumber [] = new int [6];
		
		for(int i =0; i < myNumber.length; i++) {
			myNumber[i] = x.nextInt(45) + 1;
			for(int j = 0; j < i; j++) {
				if(myNumber[i] == myNumber[j]) {
					--i;
					break;
				}
			}
		}
		for(int i = 0; i < myNumber.length; i++) {
			for (int j = i+1; j < myNumber.length; j++) {
				if(myNumber[i] > myNumber[j]) {
					int temp = myNumber[i];
					myNumber[i] = myNumber[j];
					myNumber[j]= temp;
				}
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("Lotto 행운번호=> " + Arrays.toString(lotto));
		System.out.println("Lotto 내꺼 => " + Arrays.toString(myNumber));
		if(Arrays.equals(lotto, myNumber)) {
			System.out.println("축");
		}else {
			System.out.println("땡 탈락");
		}
	}

}
