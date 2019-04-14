import java.util.Scanner;
/*
This class configured TicTacToe game
Configuration by board size(if you insert 5, it's mean 5*5 size board)
Configuration by winning condition(if you insert 3,it's mean, if in board 3 or more Xs side by side,this player win
 */
public class TicTacToeConfig {
    public int boardSize;
    public String firstPlayerName;
    public String secondPlayerName;
    public int winCnd;
    public int[][] board;

    TicTacToeConfig() {
        System.out.println("Insert board size");
        Scanner sc = new Scanner(System.in);
        boardSize = sc.nextInt();
        while (boardSize % 2 == 0 || boardSize == 1 || boardSize == 0) {
            System.out.println("Please insert correct size");
            boardSize = sc.nextInt();
        }
        System.out.println("Insert first player name: X");
        firstPlayerName = sc.next();
        System.out.println("Insert Second player name: O");
        secondPlayerName = sc.next();
        System.out.println("Insert winning condition");
        winCnd = sc.nextInt();
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 2;
    }
}

