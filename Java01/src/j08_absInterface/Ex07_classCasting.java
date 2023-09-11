package j08_absInterface;

//*** 클래스의 형변환
//=> 자동 형변환 (형변환 생략가능)
//   조상 <- 자손 (Up_Casting)

// => 명시적 형변환 (형변환 생략불가능)
//    자손 <- 조상 (Down_Casting)
//    실제적 클래스타입이 변환가능한 경우에만 적용됨 
//    (변환불가능한 경우에는 컴파일 오류 없어도 런타임 오류(ClassCastException) 발생)

//Animal => Mammal 포유류  => PetAnimal
//Animal => Reptil 파충류 

class Animal2 {
	String name;
	public Animal2() {System.out.println("** Animal 생성자 **"); }
	void breath() {
		System.out.println(name +" 는 숨을 쉽니다 ~~");
	}
} // Animal

class Mammal extends Animal2 {
	String cry ;
	public Mammal() {System.out.println("** Mammal 생성자 **"); }
	void crying() {
		System.out.println(super.name+ " 은(는) "+cry + " 웁니다 ~~");
	}
} // Mammal

class PetAnimal extends Mammal {
	PetAnimal() { System.out.println("~~ PetAnimal 생성 ~~"); }
	void checking() { System.out.printf("** %s는 예방접종을 했습니다.\n", name);  } 
} //PetAnimal

class Reptile extends Animal2 {
} // Reptile


public class Ex07_classCasting {

	public static void main(String[] args) {
		// Test 1)  
		PetAnimal dog = new PetAnimal();
		// => instanceof Test
		if (dog instanceof PetAnimal) System.out.println("dog = PetAnimal 의 인스턴스 입니다.");
		if (dog instanceof Mammal) System.out.println("dog = Mammal 의 인스턴스 입니다.");
		if (dog instanceof Animal2 )System.out.println("dog = Animal2 의 인스턴스 입니다.");

		// Test 2) class Up_Casting Test
		Animal2 an1 = new PetAnimal(); // 조상 <- 후손 : Up_Casting, 자동
		// an1 = new Reptile();  // 모든 후손은 교체가능 = (다형성 적용)

		// PetAnimal pa = new Animal2(); // 후손 <- 조상 : Down_Casting , Error

		// => 생성자 종류에 따른 Animal 인스턴스(an1,an1) 비교
		// => 모두 Up_Casting 허용됨.
		Animal2 an2 = new Animal2();
		// an1 = dog;
		// an2 = dog;

		// Test 3) Down_Casting
		// => 가능한 경우에만 명시적으로 허용
		System.out.println("Down_Casting Test");
		an1.breath();
		PetAnimal cat = null;
		cat = (PetAnimal)an1; // PetAnimal(후손) Type <- Animal2(조상) Type
		// (PetAnimal) => 명시적 형변환
		cat.checking(); // PetAnimal 에 정의된 멤버 접근가능.
		
		// cat = (PetAnimal)an2;
		// cat.checking();
		// => 컴파일오류 없음, 런타임오류_ClassCastException
		//    ( 위 60행 있을때(정상실행), 없을때(런타임오류) 비교)
		// => 그러므로 instanceof 연산자로 확인 후 Down_Casting 적용
		if(an2 instanceof PetAnimal) {
			cat = (PetAnimal)an2;
			System.out.println("an2 = PetAnimal 의 인스턴스 입니다.");
		}else {
			
			System.out.println("an2 = PetAnimal 의 인스턴스가 아닙니다.");
		}
		
		// 인스턴스의 class Type 확인하기
		// => Object 에 정의된 getCalss() 메서드 이용
		System.out.println("dog 의 class Type " + dog.getClass().getName());
		System.out.println("cat 의 class Type " + cat.getClass().getName());
		System.out.println("an1 의 class Type " + an1.getClass().getName());
		System.out.println("an2 의 class Type " + an2.getClass().getName());
		
		System.out.println("Program Stop");


	} //main

} //class
