package j07_classExtends;

// final
// => class : 상속 금지 (종단 클래스, String 대표적 예)
// => 메서드 : 오버라이딩 금지
// => 필드(변수) : 상수 

// public final class Ex01_Car {
public class Ex01_Car {

	// ** 맴버변수, 필드(field) : 속성 
	public int speed;
	public int mileage;
	public String color = "Yellow";
	public static final String COUNTRY = "Korea";
	
	// 생성자
	public Ex01_Car() {
		System.out.println("Car Default 생성자");
	}
	public Ex01_Car(int speed, int mileage, String color) {
		this.speed = speed;
		this.mileage = mileage;
		this.color = color;
		System.out.println("Car 초기화 생성자");
		// COUNTRY = "한국"; -> 상수는 수정 불가능
	}
	
	public void speedUp() {
		speed += 10;
	}
	
	public final void speedDown() {
		speed -= 10;
	}
	
	
	public String toString () { 
		return "속도 : " + speed + " 주행거리 : " + mileage + " 색상 : " + color; 
	}

}
