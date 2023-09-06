package j05_classMethod;

import java.util.Arrays;
import java.util.Random;

public class Ex10_CallByRefLotto {

	public static void main(String[] args) {
		// 1) 배열정의
		int[] lotto = new int[6];
		
		// 2) Random 으로 숫자추출 & 배열에 담기
		Random rn = new Random(5);
		for (int i=0; i<lotto.length; i++) {
			
			lotto[i] = rn.nextInt(45)+1;
			// ** 중복확인
			for (int j=0; j<=i-1; j++) {
				if ( lotto[i]==lotto[j] ) {
					--i;
					break;
				} //if
			} //j
		} //i
		
		// 3) 출력 (정렬전)
		System.out.println("** Lotto (정렬전) => "+Arrays.toString(lotto));
		
		// 4) 오름차순 정렬
		// => 순차정렬 (Sequence Sort) 메서드 만들기 
		// => static 
		lottoSort(lotto);
		System.out.println("** Lotto (정렬후) => "+Arrays.toString(lotto));

	} //main
	
	public static void lottoSort(int[] arr) {
		
		
		
		
	} //lottoSort
	
} //class
