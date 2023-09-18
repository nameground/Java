package j14_collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

//** Set 을 이용한 로또번호 생성하기
//=> 중복자료가 허용안되는 Set 의 특징을 이용하여 로또 번호 생성하기
//=> Random 또는 Math 로 1 ~ 45 범위내의 정수를 중복되지 않도록 
// 6개를 추출후 오름차순으로 출력하기   

//** Set 구현 클래스
//=> HashSet
// HashSet은 해쉬 테이블에 원소를 저장하기 때문에 성능면에서 가장 우수하다.
// 하지만 원소들의 순서가 일정하지 않은 단점이 있다.
//=> TreeSet
// 레드-블랙 트리(red-black tree)에 원소를 저장한다. 
// 따라서 값에 의해 순서가 결정되지만 HashSet보다는 느리다.
//=> LinkedHashSet
//  해쉬 테이블과 링크드 리스트를 결합한 것으로
//  원소들의 순서는 삽입되었던 순서와 같다. 


public class Ex05_SetLotto {

	public static void main(String[] args) {
		// 1. set 정의
		// => Random 또는 Math 로 1 ~ 45 범위내의 정수가 6개 채워지도록 하면됨

		TreeSet<Object> tSet = new TreeSet<Object>();
		Random x = new Random();

		// 2. 번호생성 & set에 담기
		// => Math, while 또는 for
		// => 조건 : set.size() 가 6이 될때까지
		//          자료가 없을때(size = 0) 부터 시작하므로 6 보다 작은동
		while (tSet.size() < 6) {
			tSet.add(x.nextInt(45) + 1);
		}

		// 3. 출력
		System.out.println(tSet);



		// 4. 배열과 Set
		// => set -> 배열로
		HashSet<Object> hSet = new HashSet<Object>();  // 비교용
		// => 비교용 : 중복확인은 하지만, 정렬은 안됨.
		while (hSet.size() < 6) {
			hSet.add(x.nextInt(45) + 1);
		}

		System.out.println("정렬전 hSet => "+hSet);
		Object[] oSet = hSet.toArray();
		Arrays.sort(oSet);
		System.out.println("정렬후 oSet 배열 => " + Arrays.toString(oSet));
		System.out.println("정렬후 원본 hSet => " + hSet); // 원본값 유지됨

		// 5. Collections
		// => Collection 들의 WrapperClass
		//    Collection 과 관련된 편리한 메서드를 제공
		//    단, interface Collection 과 구별
		//        interface Collector 의 구현클래스 Collectors 와 구별
		// => Collections.sort(List<T> list)
		//    인자로 List 타입이 필요함

		// ** 오름차순 출력
		// => Set -> 순차구조 (Iterator , List)
		// => Set -> List
		//    LinkedList 의 생성자중에 set을 매개변수로 사용하면
		//    이 set 을 list 생성해주는 생성자가 있음.  

		List<Integer> list = new LinkedList<Integer>();
		Collections.sort(list);
		System.out.println("Collections.sort => " + list);
	}

}
