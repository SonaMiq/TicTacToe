import java.util.Scanner;

/*
inserting position must be separated by ',',like a,b
positions counting start from 0
 */
public class TicTacToeMain {
    private static String firstPlayerName;
    private static String secondPlayerName;
    private static int boardSize;
    private static int winCnd;
    private static int[][] board;

    static {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert first player name: X");
        firstPlayerName = sc.next();
        System.out.println("Insert Second player name: O");
        secondPlayerName = sc.next();
    }

    private static MoveInBoard moveInBoard = new MoveInBoard();
    private static int fstPlayerWinCount = 0;
    private static int secPlayerWinCount = 0;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Insert games count");
        int gameCount = checkInputErrors("Please insert correct count");
        while (gameCount < 1) {
            System.out.println("Game count must be one or more");
            gameCount = checkInputErrors("Please insert correct count");
        }
        for (int i = 0; i < gameCount; i++) {
            newGame();
        }
        System.out.println(firstPlayerName + "'s score is " + fstPlayerWinCount);
        System.out.println(secondPlayerName + "'s score is " + secPlayerWinCount);
    }

    /*
   Configuration parameters for game begin
   */
    private static void config() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Insert board size");
        boardSize = checkInputErrors("Please insert correct board size");
        while (boardSize % 2 == 0 || boardSize <= 1) {
            System.out.println("Board size must be uneven number that more one");
            boardSize = checkInputErrors("Please insert correct board size");
        }
        System.out.println("Insert winning condition");
        winCnd = checkInputErrors("Please insert correct condition");
        while (winCnd <= 2 || winCnd > boardSize) {
            System.out.println("Condition must be more or equal to two and no more than board size");
            winCnd = checkInputErrors("Please insert correct condition");
        }
        /*
        Empty board configuration
         */
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 2;
    }

    private static void newGame() {
        config();
        Scanner scanner = new Scanner(System.in);
        int fstPlayerStepCounts = 0;
        int scdPlayerStepCounts = 0;
          /*
            for interrupted when we have winner or board is full (Stand Off)
             */
        for (int i = 0; i < boardSize * boardSize; i++) {
            /*
            Check board state: if haven't the winner, first player do step
             */
            if (!moveInBoard.findTheWinner(board, winCnd)) {
                fstPlayerStepCounts++;
                playerStep(firstPlayerName, 0);
                 /*
                 check if all positions selected and haven't winner
                 */
                if (moveInBoard.checkFullBoard(board)) {
                    System.out.println("Stand Off");
                    break;
                } else {
                       /*
                      Check board state: if haven't the winner, second player do step
                        */
                    if (!moveInBoard.findTheWinner(board, winCnd)) {
                        scdPlayerStepCounts++;
                        playerStep(secondPlayerName, 1);
                        if (moveInBoard.checkFullBoard(board)) {
                            System.out.println("Stand Off");
                            break;
                        }
                    } else {
                        System.out.println(firstPlayerName + " is winner, steps count is " + fstPlayerStepCounts);
                        fstPlayerWinCount++;
                        break;
                    }
                }

            } else {
                System.out.println(secondPlayerName + " is winner, steps count is " + scdPlayerStepCounts);
                secPlayerWinCount++;
                break;
            }
        }
    }

    private static void playerStep(String playerName, int xOro) {
        String[] position;
        String position1;
        String position2;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("It's " + playerName + "'s order");
            printBoard(board);
            do {
                System.out.println("Insert position");
                position = scanner.next().split(",");
            }
            while (!checkInputPosition(position, boardSize));
            position1 = position[0];
            position2 = position[1];
        }
                /*
                this part check, are player select existing position
                 */
        while (!moveInBoard.inputInBoard(board, Integer.valueOf(position1), Integer.valueOf(position2), xOro));
        printBoard(board);
    }

    /*
    This method check correct of inputting position
     */
    private static boolean checkInputPosition(String[] posit, int boardSize) {
        switch (posit.length) {
            /*
            If position don't separated with ',', work this case
             */
            case 1:
                System.out.println("Please insert position separated by ',': Position1,Position2,");
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
                    Integer.valueOf(posit[1]);
                } catch (Exception e) {
                    System.out.println("Please insert correct position");
                    return false;
                }
                /*
                Check separated integers values
                 */
                if (Integer.valueOf(posit[0]) >= boardSize || Integer.valueOf(posit[1]) >= boardSize) {
                    System.out.println("Positions should not be more than board size");
                    return false;
                } else if (Integer.valueOf(posit[0]) < 0 || Integer.valueOf(posit[1]) < 0) {
                    System.out.println("Positions should  be more than zero");
                    return false;
                } else
                    return true;

            default:
                return false;
        }
    }

    /*
    This method print board elements.
     */
    private static void printBoard(int[][] position) {
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position.length; j++) {
                switch (position[i][j]) {
                    case 0:
                        System.out.print("X" + " ");
                        break;
                    case 1:
                        System.out.print("O" + " ");
                        break;
                    case 2:
                        System.out.print("." + " ");
                }
            }
            System.out.println(" ");
        }
    }

    /*
    This method check errors that arise when inputting wrong data
     */
    private static int checkInputErrors(String errorMessage) {
        boolean error = false;
        int inputValue = 0;
        do {
            try {
                error = false;
                Scanner sc = new Scanner(System.in);
                inputValue = sc.nextInt();
            } catch (Exception e) {
                System.out.println(errorMessage);
                error = true;
            }
        }
        while (error);
        return inputValue;
    }
}




