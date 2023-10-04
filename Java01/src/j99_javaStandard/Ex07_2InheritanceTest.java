package j99_javaStandard;

class MyPoint {
	int x;
	int y;
	
}

class Circle extends MyPoint { // 상속
	int r;
}


//class Circle {
//	MyPoint p = new MyPoint();
//	int r;
//	
//}

public class Ex07_2InheritanceTest {

	public static void main(String[] args) {
		Circle c = new Circle();
		c.x = 1;
		c.y = 2;								// 상속관계
		c.r = 3;
		System.out.println("c.x = " + c.x);		// 상속관계
		System.out.println("c.y = " + c.y);
		System.out.println("c.r = " + c.r);		// 상속관계
		
//		c.p.x = 1;
//		c.p.y = 2;								// 포함관계
//		c.r = 3;
//		System.out.println("c.p.x = " + c.p.x); // 포함관계
//		System.out.println("c.p.y = " + c.p.y); 
//		System.out.println("c.r = " + c.r);		// 포함관계   
		
		
	}

}
