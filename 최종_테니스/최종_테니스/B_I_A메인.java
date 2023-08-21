package 최종_테니스;

import java.util.Random;
import java.util.Scanner;

public class B_I_A메인 {
	public static void main(String[] args) {

		System.out.println("====================================");
		System.out.println("  테니스 게임");
		System.out.println("====================================");
		System.out.println("                                                  Made by 귀염 2조 ");
		System.out.println();
		System.out.println("\t게임을 시작하려면 Enter키를 누르세요\t\n : ");


		System.out.println("\n");
		System.out.println("================================================");
		System.out.println("     경기방법 설정을 위한 정보값을 입력합니다.");
		System.out.println("================================================");
		System.out.println("\n");

		// 게임 세팅 : 1. 성별 -> 세트수 / 2. 이름 
		Scanner sc = new Scanner(System.in);

		String gen, player1, player2; 

		// 1. 성별 -> 세트수 
		System.out.print("성별을 입력하세요 (남자 또는 여자): ");
		gen = sc.next();


		int setnums = gen=="남자"? 5 : 3; 
		System.out.printf( "%s단식경기: %d세트 진행합니다", gen, setnums );


		// 2. 이름
		System.out.print("player1 이름을 입력하시오 > ");
		player1 = sc.next();
		System.out.print("player2 이름을 입력하시오 > ");
		player2 = sc.next();


		// 객체 생성 
		TennisScoreBoard tns= new TennisScoreBoard(setnums, player1, player2);


		// 게임 진행 	: while 
		while (tns.winner == null) {	// 요거 반복 세트 반영 해줘야함 
			Random rnd = new Random();
			int p = rnd.nextInt(2) + 1;
			tns.pointWinner(p);

			tns.dispScoreBoard();

		} // while

	}
}
