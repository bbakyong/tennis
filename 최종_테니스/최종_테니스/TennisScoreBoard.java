package 최종_테니스;

public class TennisScoreBoard implements I {

	// 필드 
	int numsets; 
	private String player1Name;     // 플레이어 1의 이름
	private String player2Name;     // 플레이어 2의 이름

	private int player1PointScore;  // 플레이어 1의 현재 포인트 점수
	private int player2PointScore;  // 플레이어 2의 현재 포인트 점수

	private int player1GameScore;   // 플레이어 1의 현재 게임 점수
	private int player2GameScore;   // 플레이어 2의 현재 게임 점수

	private int player1SetScore;    // 플레이어 1의 현재 세트 점수
	private int player2SetScore;    // 플레이어 2의 현재 세트 점수

	private boolean isDeuce;        // 현재 게임이 디득스 상태인지 여부
	private boolean isAdvantagePlayer1;  // 플레이어 1이 어드벤티지 상태인지 여부
	String winner;

	// 생성자 
	public TennisScoreBoard(int numsets, String player1Name, String player2Name) {
		this.numsets=numsets;
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	} 

	
	// 포인트 스코어 
	@Override
	// 해당 선수의 포인트를 증가시킨다.
	public void pointWinner(int p) {
		if (p == 1) {
			player1PointScore++;	           
			updateGameScore(player1GameScore, player2GameScore);
		} else if (p == 2) {
			player2PointScore++;
			updateGameScore(player2GameScore, player1GameScore);
		}
	}
	// 게임 스코어 
	private void updateGameScore(int player1GameScore2, int player2GameScore2) {
		if (player1PointScore == 40 && player2PointScore < 40) {
			// Player 1 wins the game
			player1GameScore++;
			System.out.printf("%s wins the game!\n", player1Name);
			resetPointScores();
			updateSetScore();

		} else if (player2PointScore == 40 && player1PointScore < 40) {
			// Player 2 wins the game
			player2GameScore++;
			System.out.printf("%s wins the game!\n", player2Name);
			resetPointScores();
			updateSetScore();

		} else if (player1PointScore == 40 && player2PointScore == 40) {
			// Deuce
			if (isDeuce) {
				// Advantage for one player
				if (isAdvantagePlayer1) {
					player1GameScore++;
					System.out.printf("%s wins the game!\n", player1Name);
					resetPointScores();
					updateSetScore();
				} else {
					player2GameScore++;
					System.out.printf("%s wins the game!\n", player2Name);
					resetPointScores();
					updateSetScore();
				}
				isDeuce = false;
				isAdvantagePlayer1 = false;
			} else {
				// Deuce
				System.out.println("Deuce!");
				isDeuce = true;
			}
		} else {
			// Normal play
			isDeuce = false;
			isAdvantagePlayer1 = false;
		}
	}
	// 세트 스코어 
	private void updateSetScore() {
		if (player1GameScore >= 6 && player1GameScore - player2GameScore >= 2) {
			// Player 1 wins the set
			player1SetScore++;
			System.out.printf("%s wins the set!\n", player1Name);
			resetGameScores();
		} else if (player2GameScore >= 6 && player2GameScore - player1GameScore >= 2) {
			// Player 2 wins the set
			player2SetScore++;
			System.out.printf("%s wins the set!\n", player2Name);
			resetGameScores();
		}
	}
	
	
	// 리셋 메서드
	private void resetPointScores() {
		player1PointScore = 0;
		player2PointScore = 0;
	}
	
	private void resetGameScores() {
		player1GameScore = 0;
		player2GameScore = 0;
	}

	
	// 현재의 스코어보드를 화면에 출력한다.
	@Override
	public void dispScoreBoard() {
		/* System.out.println(player1 + " vs. " + player2);
        System.out.println("Sets: " + player1SetsWon + "-" + player2SetsWon);
        for (int i = 0; i < numSets; i++) {
            System.out.println("Set " + (i+1) + ": " + player1GameScores[i] + "-" + player2GameScores[i]);
        }
        System.out.println("Game: " + currentGamePoint);*/
		int[] player1Points = { 0, 15, 30, 40 };
		int[] player2Points = { 0, 15, 30, 40 };
		int printplayer1Point = player1Points[player1PointScore % 4];
		int printplayer2Point = player2Points[player2PointScore % 4];

		System.out.printf("point : %d vs %d \t", printplayer1Point, printplayer2Point);
		System.out.printf("game : %d vs %d \t", player1GameScore, player2GameScore);
		System.out.printf("set : %d vs %d \n ", player1SetScore, player2SetScore);

		if (winner != null) {
			System.out.printf("승자가 나왔습니다 : %s", winner);
		}
	}
}
