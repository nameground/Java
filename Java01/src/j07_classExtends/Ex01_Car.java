package j07_classExtends;

public class Ex01_Car {

	// ** 맴버변수, 필드(field) : 속성 
	public int speed;
	public int mileage;
	public String color = "Yellow";
	
	// 생성자
	public Ex01_Car() {}
	public Ex01_Car(int speed, int mileage, String color) {
		this.speed = speed;
		this.mileage = mileage;
		this.color = color;
	}
	
	public void speedUp() {
		speed += 10;
	}
	
	public void speedDown() {
		speed -= 10;
	}
	
	
	public String toString () { 
		return "속도 : " + speed + " 주행거리 : " + mileage + " 색상 : " + color; 
	}

}
