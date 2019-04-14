import java.util.Scanner;

public class TicTac {

    public static void main(String[] args) {
        TicTacToeConfig game = new TicTacToeConfig();
        TicTacToe play = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int[][] board = game.board;
        String[] pos;
        String pos1;
        String pos2;
        /*
        At worst case(if we haven't a winner) we insert positions boarsSize*boardSize time
         */
        for (int i = 0; i < game.boardSize * game.boardSize; i++) {
            /*
            for interrupted when we have winner
             */
            if (!play.check(board, game.winCnd)) {
                do {
                    System.out.println("It's " + game.firstPlayerName + "'s order");
                    play.printBoard(board);
                    System.out.println("Insert position");
                    pos = scanner.next().split(",");
                    pos1 = pos[0];
                    pos2 = pos[1];
                }
                /*
                this part check, if we select existing position
                 */
                while (!play.input(board, Integer.valueOf(pos1), Integer.valueOf(pos2), 0));
                play.printBoard(board);
            }
            else {
                System.out.println(game.secondPlayerName+ " is winner");
                break;
            }
            if (!play.check(board, game.winCnd)){
                do {
                    System.out.println("It's " + game.secondPlayerName + "'s order");
                    play.printBoard(board);
                    System.out.println("Insert position");
                    pos = scanner.next().split(",");
                    pos1 = pos[0];
                    pos2 = pos[1];
                }
                while (!play.input(board, Integer.valueOf(pos1), Integer.valueOf(pos2), 1));
                play.printBoard(board);
            }
            else {
                System.out.println(game.firstPlayerName+ " is winner");
                break;
            }
        }
    }
}

