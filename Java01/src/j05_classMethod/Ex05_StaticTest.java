package j05_classMethod;

// static, instance 접근하기
// => 다른 클래스의 멤버 접근 ( Ex05_Static )

public class Ex05_StaticTest {

	public static void main(String[] args) {
		// 1) static 변수 출력하기
		System.out.println("total => " + Ex05_Static.total);
		
		// 2) instance(non_static) 멤버 접근
		Ex05_Static ex05 = new Ex05_Static();
		ex05.instanceAll(10, 3, ex05);
		Ex05_Static.staticAll(10, 3, ex05);
		
		// 3) 인스턴스 추가 후 Test
		System.out.println("인스턴스 추가 후 Test");
		Ex05_Static ex055 = new Ex05_Static();
		ex055.instanceAll(10, 3, ex055);
		Ex05_Static.staticAll(10, 3, ex055);
		
		
		
	}

}
