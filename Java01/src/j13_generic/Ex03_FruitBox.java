package j13_generic;

//** Generic Class Test 
//** FruitBox 만들기
//=> Apple, Banana 등 모든 과일을 담을수 있는 Generic FruitBox class 를 만들어 보세요.
//   단 과일들만 담을 수 있어야 함.
//   ( 자율적으로 해보시면 됩니다. )
//   힌트: Apple, Banana 등 각종 과일들이 class 이고, 
//        이들은 과일(Fruit) 로 구분 될 수 있어야함.   
//=> 실습
//   -> 1) Fruit 만들기
//   -> 2) 과일 클래스들 만들기 (3개)
//   -> 3) FruitBox 만들기
//      : 과일배열선언, 과일들담기_setter, 과일들출력메서드_fruitPrint()
//   -> 4) main 완성하기

interface Fruiti { }
class Fruit { public String toString() { return "I am Fruit"; } }

class Apple extends Fruit implements Fruiti { public String toString() { return "I am Apple"; } }
class Banana extends Fruit implements Fruiti { public String toString() { return "I am Banana"; } }
class Orange extends Fruit implements Fruiti { public String toString() { return "I am Orange"; } }

class Tomato { public String toString() { return "I am Tomato"; } }

//** FruitBox 만들기
//=> Apple, Banana 등 과일만 담을수있는 Generic FruitBox class
//=> T : Type 제한 없음
class FruitBoxA<T> {
   private T[] arr ;
   public void setArr(T[] arr) { this.arr=arr; }
   public void fruitPrint() {
      for ( T a:arr ) {
         System.out.println(a);
      } //for
   } //arrayPrint
} //FruitBoxA

// => T : Fruit Type 으로 제한 -> Fruit의 후손 클래스들만 허용 
class FruitBox<T extends Fruit> {
   private T[] arr ;
   public void setArr(T[] arr) { this.arr=arr; }
   public void fruitPrint() {
      for ( T a:arr ) {
         System.out.println(a);
      } //for
   } //arrayPrint
} //FruitBox

// => interface Test
//    extends 를 이용해서 적용가능함
class FruitBoxi<T extends Fruiti> {
   private T[] arr ;
   public void setArr(T[] arr) { this.arr=arr; }
   public void fruitPrint() {
      for ( T a:arr ) {
         System.out.println(a);
      } //for
   } //arrayPrint
} //FruitBoxi

public class Ex03_FruitBox {

   public static void main(String[] args) {
      // 1) 종합 선물Set
      // => 모든 객체들을 다 담을수있음
      Object[] oarr = { new Apple(), new Banana(), new Orange(), new Tomato() };
      FruitBoxA fb1 = new FruitBoxA();
      fb1.setArr(oarr);
      fb1.fruitPrint();
      // => Tomato Box
      Tomato[] tarr = { new Tomato(), new Tomato(), new Tomato() };
      FruitBoxA<Tomato> fb2 = new FruitBoxA<Tomato>();
      fb2.setArr(tarr);
      fb2.fruitPrint();
      
      // 2) Fruit 선물set
      Fruit[] farr = { new Apple(), new Banana(), new Orange() }; //Tomato는 담을수 없음
      FruitBox<Fruit> fb3 = new FruitBox<Fruit>();
      fb3.setArr(farr);
      fb3.fruitPrint();
      
      Apple[] parr = { new Apple(), new Apple(), new Apple() };
      FruitBox<Apple> fb4 = new FruitBox<Apple>();
      fb4.setArr(parr);
      fb4.fruitPrint();

      // 3) interface Test
      FruitBoxi<Fruiti> fb5 = new FruitBoxi<Fruiti>();
      Fruiti[] farri = { new Apple(), new Banana(), new Orange() };
      fb5.setArr(farri);
      //fb5.setArr(farr); // Fruit Type 배열 farr은 담을 수 없지만,  
      fb5.setArr(parr);  // Fruit 를 구현한 Apple Type 배열은 허용됨.
      fb5.fruitPrint();
      
      
      
   } //main
} //class