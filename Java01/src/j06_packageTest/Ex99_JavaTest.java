package j06_packageTest;

class MyPoint { // extends Object = 코드상에는 존재하지않지만 컴파일시 컴파일러에 의해 추가
	int x;
	int y;
}

//class Circle extends MyPoint {  // 상속관계  
//	int r;
//}

class Circle { // 포함관계
	MyPoint p = new MyPoint();  // 참조변수의 초기화
	int r;
}

public class Ex99_JavaTest {

	public static void main(String[] args) {
		Circle c = new Circle();
		// Object 클래스중 메서드 11개 중 toString
		System.out.println(c.toString()); // "Circle@6d06d69c" 객체의 주소값을 반환
		System.out.println(c); // "Circle@6d06d69c" 위에 문장과 같은 문장
		Circle c2 = new Circle();
		System.out.println(c2.toString()); // "Circle@7852e922" 객체의 주소값을 반환
		
	}

}
