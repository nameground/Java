package j06_packageTest;


class Car {

	// ** 맴버변수, 필드(field) : 속성 
	// => 클래스의 전역변수
	//    클래스내에 있는 모든 메서드에서 사용가능
	//    다른 클래스에도 접근 가능
	public int speed;
	public int mileage;
	public String color = "Yellow";
	
    // package Test 용 변수선언
    public static int ddd = 100;


	// ** 맴버메서드 (Method, function, procedure)
	// => 동작 또는 기능을 구현
	// void = 메서드에서 return 값이 없음을 알려주는 타입
	public void speedUp() {
		speed += 10;
	}

	public void speedDown() {
		speed -= 10;
	}

	// => String (return Type) : 문자열을 반환 (return) -> 메서드 처리 결과로 문자열을 제공
	// => toStrinf (메서드명) : 객체의 속성(멤버필드,멤버변수) 의 값을 문자로 제공하는 역할에 주로 사용됨

	public String toString () { 
		return "속도 : " + speed + " 주행거리 : " + mileage + " 색상 : " + color; 
	}

	// toString 메서드
	// => 출력문에서 인스턴스명만 사용했을때 자동호출됨
	// => Object 에 정의됨 (객체의 주소를 String Type 으로 return)
	// => 대부분의 클래스에서 속성 출력용도로 재정의하여 사용함.
}

// => API 라이브러리의 클래스명 비추
// class System {
//  System.out~~~ 이 오류 뜨느것을 확인해본다.
// } 

public class Ex01_CarTest {

	public static void main(String[] args) {
		System.out.println("Car class Test");

		// 객체 생성과 사용
		// => 생성 -> 사용 -> 소멸
		// => 생성 : "인스턴스화 한다" 또는 "인스턴스를 생성한다" 라고함

		Car myCar = new Car();
		myCar.color = "Blue";
		myCar.mileage = 1000;
		myCar.speed = 130;
		myCar.speedUp();
		System.out.println("myCar1 toString  => " + myCar.toString());
		System.out.println("myCar2 => " + myCar);
		System.out.println("");

		// ** 소멸 : 메모리청소
		// => 더이상 사용되지않는 값들은 자동제거해줌
		// => Garbage Collector (쓰레기 수집기)
		// => 정해진 알고리즘에 의해 작동됨 ( 일정시간동안 또는 더이상 참조되지않는등등... )   
		// => 참조형 변수에 null 값을 주면 소멸 (더이상 참조되지않음)
		myCar = null; // null -> 값없음을 의미
		// myCar.color; == Error (접근불가) : 컴파일 오류
		// myCar.speedDown(); => 실행오류 : java.lang.NullPointerException
		

		Car mCar = new Car();
		mCar.color = "Green";
		mCar.mileage = 800;
		mCar.speed = 80;
		mCar.speedUp();
		System.out.println("mCar1 toString => " + mCar.toString());
		System.out.println("mCar2 => " + mCar);
		System.out.println("");

		Car rCar = mCar; // mCar 의 주소값이 전달됨
		System.out.println("rCar => " + rCar);
		System.out.println("");

		Car fCar = new Car();
		fCar.color = "Pink";
		fCar.mileage = 1500;
		fCar.speed = 100;
		fCar.speedDown();
		System.out.println("fCar1 toString  => " + fCar.toString());
		System.out.println("fCar => " + fCar);

	}

}
