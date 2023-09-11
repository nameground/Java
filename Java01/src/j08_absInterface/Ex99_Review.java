package j08_absInterface;

class Car {
	String color;
	int door;
	
	void drive() { // 운전하는 기
		System.out.println("dirve, Brrrrr~");
	}
	void stop() {  // 멈추는 기
		System.out.println("Stop");
	}
}

class FireEngine extends Car { // 소방
	void water() {  		   // 물을 뿌리는 기
		System.out.println("Water!!!");
	}
}

public class Ex99_Review {

	public static void main(String[] args) {
		Car car = null;
		FireEngine fe = new FireEngine();
		FireEngine fe2 = null;
		
		fe.water();
		car = fe;
		// car.water(); // Error Car Type의 참조변수로는 water()를 호출할 수 없다.
		fe2 = (FireEngine)car;
		fe2.water();

	}

}
