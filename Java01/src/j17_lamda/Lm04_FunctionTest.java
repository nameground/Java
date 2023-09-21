package j17_lamda;

import java.util.function.Function;

// FunctionalInterface 1
// => Function <T, R>

// 실습
// => String 의 길이를 return
// => Double 입력받아 Double return




public class Lm04_FunctionTest {

	public static void main(String[] args) {
		// 실습 1) String 의 길이를 return
		Function<String, Integer> f1 = s -> s.length();;
		System.out.println("Function<T,R> 사용");
		
		// 실습 2) Double 입력받아 Double return
		// => Double 입력받아 inch로,
		//    inch 를 cm 로 출력하기
		
		Function<Double, Double> cToi = d -> d * 0.393701;
		Function<Double, Double> iToc = d -> d * 2.54;
		
		// 실행
		System.out.println("실습 1) Function 의 길이 => " + f1.apply("Function"));
		System.out.println("실습 1) 가나다라마 의 길이 => " + f1.apply("가나다라마"));
		
		System.out.println("실습 2) cToi, 123cm  => " + cToi.apply(123.0) + " Inch ");
		System.out.println("실습 2) iToc, 123inch  => " + iToc.apply(123.0) + " cm ");
		
		
	}

}
