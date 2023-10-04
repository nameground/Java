package j99_javaStandard;
class Tv {
	boolean power;   // 전원상태
	int channel;	// 채널
	
	void power() {  power = !power;   }
	void channelUp() {  ++channel;   }
	void channelDown() {  --channel;   }
}

class SmartTv extends Tv {				 // SmartTv 는 Tv에 캡션(자막)을 보여주는 기능
	boolean caption;					 // 캡션상태 (On / off)
	void displayCaption(String text) {   
		if (caption) {					 // 캡션 상태가 on(true)일 때만 text 보여준다.
			System.out.println(text);
		}
	}
}

public class Ex07_1Extands {

	public static void main(String[] args) {
		SmartTv stv = new SmartTv();
		stv.channel = 10;					// 조상 클래스로부터 상속받은 멤버
		stv.channelUp();					// 조상 클래스로부터 상속받은 멤버
		System.out.println(stv.channel);
		
		stv.displayCaption("Hello World");
		stv.caption = true;					// 캡션(자막) 기능을 켠다.
		stv.displayCaption("Hello World");
		
		
		
	}

}
