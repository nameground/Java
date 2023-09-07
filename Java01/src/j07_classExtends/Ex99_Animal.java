package j07_classExtends;



class Animal {
	boolean breath = true;
	int leg;


	// Default 생성자
	public Animal() {  
		super();
	}  

	public Animal(int leg ) {  
		this.leg = leg;
		System.out.println("Animals 생성자");
	}  

	public String toString() {
		return "호흡 " + breath + " 다리 수 : " + leg;
	}
}

class Mammal extends Animal{
	public Mammal() {  
		System.out.println("Default 생성");
	}  
	String growl = "grrrr";
	String run = "나는 달린다.";
	public String toMam(int leg, boolean breath) {
		return "내 다리개수는 "+ leg +" "+ breath +" 울음소리는 " + growl;
	}

}

class Wild extends Mammal{
	String lion = "Lion";
	String tiger;
	public Wild() {  
		System.out.println("Default 생성");
	} 
	public String toLion(int leg, boolean breath, String growl ,String run) {
		return "나는"+ lion + " 내 다리개수는 "+ leg + breath + "울음소리는 " + growl;
	}
}

class Pet extends Mammal{
	String dog = "DOG";
	String cat;
	public Pet() {  
		System.out.println("Default 생성");
	} 
	public String toDog(int leg, boolean breath, String growl ,String run) {
		return "나는 " + dog + " 내 다리개수는 "+ leg +" "+ breath + " 울음소리는 " + growl +" 달리수 있냐고? " + run;
	}
}
public class Ex99_Animal {

	public static void main(String[] args) {
		Animal a1 = new Animal();
		System.out.println(a1);
		
		Mammal m1 = new Mammal();
		System.out.println(m1.toMam(4, true));
		
		Pet p1 = new Pet();
		System.out.println(p1.toDog(4,true,"멍멍","못달려"));
		
	}
}


