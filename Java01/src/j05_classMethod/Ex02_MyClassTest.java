package j05_classMethod;

// MyClass
// => 멤버변수 3개 메서드 2개 정의
// -> MyClass 를 정의하고
//	  Ex02_MuClassTest 에서 인스턴스 2개 만들어 출력하기


class Music {

	// 맴버 변수 
	// 전역(Global) 변수
	// 클래스내에 있는 모든 메서드에서 사용가능
	// 다른 클래스에도 접근 가능
	public String type;
	public String artist;
	public String album;
	public int volume;

	public void volumeUp() {
		volume += 10; // 전역변수

		// 지역(local) 변수
		// => 내부에 정의된 변수, 인자
		// { } 내부에서만 적용가능
		// 클래스 외부에서도 접근 불가능
		// 전역변수와 동일한 이름의 지역변수를 정의하면 지역변수 우선 적용
		// 단, 지역변수 선언 이후부터
		// int master = 100;
	}
	public void volumeDown() {
		volume -= 10;
	}

	public String toString() {
		return "좋아하는 장르 : " + type + " 좋아하는 가수 : " + artist + " 아티스트의 좋아하는 앨범 : " + album + " 현재 볼륨 : " + volume;
	}
}

public class Ex02_MyClassTest {

	public static void main(String[] args) {

		System.out.println("Music Class Test");
		System.out.println("");

		Music kMusic = new Music();
		kMusic.type = "RnB,";
		kMusic.artist ="Gallant,";
		kMusic.album = "Ology";
		kMusic.volumeUp();
		System.out.println("kMusic toString => " + kMusic.toString());
		System.out.println("");


		Music pMusic = new Music();
		pMusic.type = "Hip-hop,";
		pMusic.artist= "Dynamic-Duo,";
		pMusic.album = "Taxi-Driver";
		pMusic.volumeUp();
		System.out.println("pMusic toString => " + pMusic.toString());

	}

}
