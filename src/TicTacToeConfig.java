import java.util.Scanner;

/*
This class configured TicTacToe game
Configuration by board size(if you insert 5, it's mean 5*5 size board)
Configuration by winning condition(if you insert 3,it's mean, if in board 3 or more Xs side by side,this player win
 */
public class TicTacToeConfig {

    public static String firstPlayerName;
    public static String secondPlayerName;
    public int boardSize;
    public int winCnd;
    public int[][] board;
/*
Player names must configured only in start
 */
    static {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert first player name: X");
        firstPlayerName = sc.next();
        System.out.println("Insert Second player name: O");
        secondPlayerName = sc.next();
    }

    TicTacToeConfig() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert board size");
        boardSize = sc.nextInt();
        while (boardSize % 2 == 0 || boardSize <= 1) {
            System.out.println("Please insert correct size");
            boardSize = sc.nextInt();
        }
        System.out.println("Insert winning condition");
        winCnd = sc.nextInt();
        while (winCnd <= 2 || winCnd > boardSize) {
            System.out.println("Please insert correct condition");
            winCnd = sc.nextInt();
        }
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 2;
    }
}

