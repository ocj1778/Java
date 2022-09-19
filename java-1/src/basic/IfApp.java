package basic;

//제어문(Control Statement) : 프로그램의 흐름을 바꾸어 주는 구문
// => 선택문(if, switch), 반복문(for, while), 기타(break, continue, return)

//if : 조건식에 의해 명령을 선택 실행
//형식) if(조건식) { 명령;  명령; ... }
// => 조건식의 결과가 참(true)인 경우 { } 안의 명령들을 실행
// => { } 안에 작성된 명령이 하나인 경우 { } 기호 생략 가능
//형식) if(조건식) { 명령;  명령; ... } else { 명령; 명령;... }
// => 조건식의 결과가 참(true)인 경우와 거짓인 경우를 구분하여 { } 안의 명령 실행
public class IfApp {
	public static void main(String[] args) {
		//int su=90;
		int su=40;
		
		//변수값이 50 이상인 경우에만 화면에 변수값 출력
		/*
		if(su>=50) {
			System.out.println("su = "+su);
		}
		*/
		
		//{ } 안에 명령이 하나이므로 { } 기호 생략
		if(su>=50) System.out.println("su = "+su);
		System.out.println("==============================================================");
		//int score=80;
		int score=50;
		
		/*
		//변수값이 60 이상인 경우 합격 메세지를 출력하고 60 미만인 경우 불합격 메세지 출력
		if(score>=60) {
			System.out.println("[결과]점수가 60점 이상이므로 합격입니다.");
		}
		
		if(score<60) {
			System.out.println("[결과]점수가 60점 미만이므로 불합격입니다.");
		}
		*/

		//변수값이 60 이상인 경우 합격 메세지를 출력하고 아니라면 불합격 메세지 출력
		if(score>=60) {
			System.out.println("[결과]점수가 60점 이상이므로 합격입니다.");
		} else {
			System.out.println("[결과]점수가 60점 미만이므로 불합격입니다.");
		}
		System.out.println("==============================================================");
		
		
	}
}










