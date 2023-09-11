package j08_absInterface;

class Child3 extends Parent3 implements MyInterface, MyInterface2 {
	public void method1() {
		System.out.println("method1() in Child3"); // 오버라이딩
	}
}

class Parent3 {
	public void method2() {
		System.out.println("method2() in MyInterface");
	}
}

interface MyInterface {
	default void method1() {
		System.out.println("method1() in MuInterface");
	}
	default void method2() {
		System.out.println("method2() in MuInterface");
	}
	static void staticMethod() {
		System.out.println("staticmethod() in MuInterface");
	}
}

interface MyInterface2 {
	default void method1() {
		System.out.println("method1() in MuInterface2");
	}
	
	static void staticMethod() {
		System.out.println("staticMethod() in MyInterface2");
	}
}
public class Ex99_Review3 {

	public static void main(String[] args) {
		Child3 c = new Child3();
		c.method1();
		c.method2();
		MyInterface.staticMethod();
		MyInterface2.staticMethod();
		
		
		
	}

}
