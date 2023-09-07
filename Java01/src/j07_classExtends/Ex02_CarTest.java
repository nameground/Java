package j07_classExtends;

// ** 상속 : extends
// => 기능을 확장해서 업그레이드 버젼 만듦.
// => 기존(조상) 클래스의 맴버들(필드와 메소드)을 재사용 & 일부변경도 가능 

// ** 생성순서
// => JVM은 extends 키워드가 있으면 조상생성후 후손생성
//    이때 기본은 조상의 default 생성자를 실행하고,
//    특별히 후손 생성자에서 조상생성자_super(...)를 호출해 놓으면 그생성자를 실행함.
// => 상속을 허용하는 클래스는 default 생성자를 반드시 작성하는것이 바람직함.

// ** 조상 : Super (Parent, Base) class  
// => super.  : 조상의 인스턴스를 의미 (조상의 맴버에 접근 가능)
// => super()
//    조상의 생성자를 의미 (조상의 생성자에 접근 가능).
//    this() 처럼 생성자 메서드 내에서 첫줄에 위치해야함.

// => 생성자메서드_super(), this() 호출은 생성자 내에서만 가능 

// ** 후손 : Sub ( Child, Derived [diráivd] ) class
// => super class 를 포함

class SportCar extends Ex01_Car{
	// 1)
	int turbo;
	int speed;
	SportCar() {      
		super(); // 넣어주지 않아도 컴파일러가 조상의 default 생성자를 의미하는 super() 를 넣어줌
		System.out.println("SportCar Default 생성자");
	}
	
	// 2)
	SportCar(int turbo) {      
		this.turbo = turbo;
		System.out.println("SportCar Default 생성자");
	}
	
	// 3)
	// => 조상의 멤버들도 모두 초기화하는 생성자
	SportCar(int turbo, int speed, int mileage, String color) {      
		// 조상 생성자 호출, 항상 첫줄에 위치해야함
		super(speed, mileage, color);
		this.turbo = turbo;
		System.out.println("SportCar Default 생성자");
	}
	
	// 4)
	// => Car 클래스에 생성자를 추가하지 않고,
	//    super(speed,mileage) -> 없음(호출 불가능)
	//    turbo, speed, mileage 를 초기화 하는 생성자를 추가하세요.
	//    -> 조상의 speed, mileage 에 매개변수의 값을 전달
	SportCar(int turbo, int speed, int mileage) {
		this.turbo = turbo;
		super.speed = speed;
		super.mileage = mileage;
		System.out.println("turbo, speed, mileage 초기화 생성자");
	}
	
	public int powerUP(int turbo) {
		return this.turbo += turbo;
	}
	
	// 5)
	// => 조상과 동일한 (이름, 매개변수, return 값)메서드
	// => 이런것을 메서드 오버라이딩
	// => 조상에 정의된 메서드 기능을 업그레이드 하고싶을때 사용
	//    조상에 정의된 기능 + 추가기능
	@Override  
	public void speedUp() {
		super.speedUp();
		this.speed += 100; // 추가기능 SportCar_speed
	}
	
    // @Override => 메서드 오버라이딩 아니고 메서드 오버로딩에 해당
	// 오버라이딩은 무조건 상속관계에서 일어나고
	// 오버로딩은 한 클래스에서 일어남
	public void speedUp(int speed) {
		this.speed += speed; // SportCar_speed
		super.speed += speed; // Car_speed
		System.out.printf("speed = %d, this.speed = %d, super.speed = %d, COUNTRY = %s \n", 
				speed, this.speed, super.speed, Ex01_Car.COUNTRY);
	}
	
	// => 조상과 동일한 이름의 메서드
	//    조상에도 있고 자식에도 있음
	// => 조상(super) / 자식 (this) 구별 필요
	@Override
	public String toString() {
		// => 조상의 toString 메서드 호츌
		return super.toString() + "\n SportCar [ turbo = " + turbo + " ]";
	}
	
	// 6) final Test
	// 오버라이딩 금지
    //  public final void speedDown() {
    //		speed -= 100;
    //	}
	// => 매개변수가 다르므로 오버로딩은 허용
	public final void speedDown(int i) {
		speed -= i;
	}
	
	
}


public class Ex02_CarTest {

	public static void main(String[] args) {
		
		// 1) 상속 Test
		// => 조상의 멤버의 접근가능
		// => SportCar 멤버 추가이전 / 이후 비교
		//    이름이 같은 경우 현재(자식) 클래스 정의된 멤버 우선 적용됨
		// => 생성자 추가 후 출력순서 확인
		//    조상생성자 -> 자식생성자 실행
		
		SportCar sc1 = new SportCar();
		sc1.color = "Blue";
		System.out.println("sc1 => " + sc1);  // 추가이전 Car 출력
		System.out.println("sc1_color => " + sc1.color);
		
		// 2) => 초기화 생성자 Test
		//    사용자입장에서 조상의 생성자를 선택할 수 없음 : 조상은 Default 생성자 호출됨
		SportCar sc2 = new SportCar(100);
		System.out.println("생성자 Test sc2 => " + sc2);  
		
		// 3) => 조상포함 모든 멤버변수를 초기화 하는 생성자
		SportCar sc3 = new SportCar(300, 100, 1000, "Red");
		System.out.println("생성자 Test sc3 => " + sc3);  
		
		// 4) => super 접근 Test
		SportCar sc4 = new SportCar(500,500,5000);
		System.out.println("생성자 Test sc4 => " + sc4);  
		
		// 5) 멤버 변수 Test
		// => 자식 클래스에 speed 추가하기전 출력(Car) / 추가후 출력
		System.out.println("Test5) speed1 => " + sc4.speed);
		// => speedUP 메서드 추가 전 / 후 비교
		sc4.speedUp();
		System.out.println("Test5) speed2 => " + sc4.speed);
		sc4.speedUp(123);
		
		
	}

}
