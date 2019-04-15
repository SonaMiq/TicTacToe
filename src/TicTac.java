import java.util.Scanner;

public class TicTac {
    public static int fstPlayerWinCount = 0;
    public static int secPlayerWinCount = 0;
    public static String fstPlayerName = "";
    public static String scdPlayerNmae = "";

    public static void main(String[] args) {
        int gameCount;
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert games count");
        boolean error = false;
        /*
        check if inserting game count not integer
         */
        do {
            try {
                gameCount = sc.nextInt();
                error = false;
            } catch (Exception e) {
                System.out.println("Please insert correct count");
                sc = new Scanner(System.in);
                error = true;
                gameCount = 0;
            }
        }
        while (error);
         /*
        inserting integer game count must be >=1
         */
        while (gameCount < 1) {
            System.out.println("Game count must be more one");
            gameCount = sc.nextInt();
        }
        for (int i = 0; i < gameCount; i++) {
            oneGame();
        }
        System.out.println(fstPlayerName + "'s score is " + fstPlayerWinCount);
        System.out.println(scdPlayerNmae + "'s score is " + secPlayerWinCount);
    }

    public static void oneGame() {
        TicTacToeConfig game = new TicTacToeConfig();
        TicTacToe play = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int[][] board = game.board;
        String[] pos;
        String pos1;
        String pos2;
        fstPlayerName = game.firstPlayerName;
        scdPlayerNmae = game.secondPlayerName;
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
                    do {
                        System.out.println("Insert position");
                        pos = scanner.next().split(",");
                    }
                    while (!readInput(pos, game.boardSize));
                    pos1 = pos[0];
                    pos2 = pos[1];
                }
                /*
                this part check, if we select existing position
                 */
                while (!play.input(board, Integer.valueOf(pos1), Integer.valueOf(pos2), 0));
                play.printBoard(board);
            } else {
                System.out.println(game.secondPlayerName + " is winner");
                secPlayerWinCount++;
                break;
            }
            if (!play.check(board, game.winCnd)) {
                do {
                    System.out.println("It's " + game.secondPlayerName + "'s order");
                    play.printBoard(board);
                    do {
                        System.out.println("Insert position");
                        pos = scanner.next().split(",");
                    }
                    while (!readInput(pos, game.boardSize));
                    pos1 = pos[0];
                    pos2 = pos[1];
                }
                while (!play.input(board, Integer.valueOf(pos1), Integer.valueOf(pos2), 1));
                play.printBoard(board);
            } else {
                System.out.println(game.firstPlayerName + " is winner");
                fstPlayerWinCount++;
                break;
            }
        }
    }

    /*
    This method check correct of inputting position
     */
    public static boolean readInput(String[] posit, int boardSize) {
        switch (posit.length) {
            /*
            If position don't separated with ',', work this case
             */
            case 1:
                System.out.println("Please separate position with ,");
                return false;
                /*
            If position  separated with one ',', work this case
             */
            case 2:
                /*
                catch an error, when separated values not integers
                 */
                try {
                    Integer.valueOf(posit[0]);
                } catch (Exception e) {
                    System.out.println("Please insert correct position");
                    return false;
                }
                try {
                    Integer.valueOf(posit[1]);
                } catch (Exception e) {
                    System.out.println("Please insert correct position");
                    return false;
                }
                /*
                Check separated integers values
                 */
                if (Integer.valueOf(posit[0]) < 0 || Integer.valueOf(posit[0]) >= boardSize || Integer.valueOf(posit[1]) < 0 || Integer.valueOf(posit[1]) >= boardSize) {
                    System.out.println("Please insert correct position");
                    return false;
                } else
                    return true;
            default:
                return false;

        }
    }
}


