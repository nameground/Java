package j02_ifswitch;

public class Ex02_Ifelseif {

	public static void main(String[] args) {

		// ** 등급 처리
		// => score 90이상:A , 80이상:B, 70이상:C, 60이상:D, 아니면 "F" 출력
		// => 이상 / 이하  (포함)
		// => 초과 / 미만  (불포함)

		int score = 75;
		char grade = 'A';
		// => grade 확인
		if(score >= 90) {
			grade = 'A';
		}else if (score >= 80) {
			grade = 'B';
		}else if (score >= 70) {
			grade = 'C';
		}else if (score >= 60) {
			grade = 'D';
		}else {
			grade = 'F';
		}


		// => grade 출력
		System.out.printf("score = %d , grade %s \n ", score, grade);

		// 비교 1
		if (score >= 90) grade = 'A';
		if (score >= 90) grade = 'B';
		if (score >= 70) grade = 'C';
		if (score >= 60) grade = 'D';
		else grade = 'f';

		System.out.printf("비교 1 ) score = %d , grade %s \n ", score, grade);
		
		//---------------------------------------------------------------- else if

		// 중첩 if 
		if (score >= 90) {
			grade = 'A';
		}else {
			if (score >= 80) {
				grade = 'B';
			}else {
				if (score >= 70) {
					grade = 'C';
				}else {
					if (score >= 60) {
						grade = 'D';
					}else {
						grade = 'F';
					}
				}
			}
		}
		System.out.printf("중첩 if) score = %d , grade %s \n ", score, grade);

		//---------------------------------------------------------------- 중첩 if
	}

}
