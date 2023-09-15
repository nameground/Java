package j13_generic;

import j07_classExtends.Ex01_Car;

//** 과제
// => 배열을 출력해주는 제네릭 클래스 만들기
//    배열정의, setter/getter, arrayPrint()
// => 실행시에 원하는 타입을 결정 & 출력
// => 배열 타입 Generic

class GenArray<T> {
	private T[] arr;
	public void setArr(T[]arr) {this.arr = arr;}
	public T[] getArr() {
		return this.arr;
	}
	
	public void arrayPrint() {
		for( T a: arr) {
			System.out.println(a);
		}
	}
}

public class Ex02_GenArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1) String
		String[] ss = {"가", "나", "Da", "Re", "Mi"};
		
		GenArray<String> ga1 = new GenArray<String>();
		ga1.setArr(ss);
		ga1.arrayPrint();  
		
		// 2) Integer
		Integer[] ii = {1,2,3,4,5};
		GenArray<Integer> ga2 = new GenArray<Integer>();
		ga2.setArr(ii);
		ga2.arrayPrint();
		
		// 3) Character(char)
		Character[] cc = {'a', 'b', 'c', 'd', 'e'};
		GenArray<Character> ga3 = new GenArray<Character>();
		ga3.setArr(cc); 
		ga3.arrayPrint();
		
		// 4) Ex01_Car
		Ex01_Car [] rr = {
				new Ex01_Car(100, 500, "Sliver"),
				new Ex01_Car(100, 200, "Gold"),
				new Ex01_Car(100, 300, "Black"),
		};
		GenArray<Ex01_Car> ga4 = new GenArray<Ex01_Car>();
		ga4.setArr(rr);
		ga4.arrayPrint();
	}

}
