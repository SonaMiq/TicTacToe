public class TicTacToe {
/*
This method input X or O, if this position is empty
 */
    public boolean input(int[][] brd, int cord1, int cord2, int xOro) {
        if (brd[cord1][cord2] == 2) {
            brd[cord1][cord2] = xOro;
            return true;
        }
        else {
            System.out.println("This position already selected");
            return false;
        }
    }
/*
This method check the presence of elements that are next to each other,
if count of this elements >= winner condition, we have winner
 */
    public boolean check(int[][] positions, int winPosCount) {
        int k = 0;
        for (int i = 0; i < positions.length; i++) {
            k = 0;
            for (int j = 0; j < positions.length - 1; j++) {
                if (positions[i][j] == positions[i][j + 1] && positions[i][j] != 2)
                    k++;
            }
            if (k >= winPosCount - 1)
                return true;
        }
        for (int j = 0; j < positions.length; j++) {
            k = 0;
            for (int i = 0; i < positions.length - 1; i++) {
                if (positions[j][i] == positions[j][i + 1] && positions[i][j] != 2)
                    k++;
            }
            if (k >= winPosCount - 1)
                return true;
        }
        k = 0;
        for (int i = 0; i < positions.length - 1; i++) {
            if (positions[i][i] == positions[i + 1][i + 1] && positions[i][i] != 2)
                k++;
        }
        if (k >= winPosCount-1)
            return true;
        return false;
    }
/*
This method print board elements.
 */
    public void printBoard(int[][] position) {
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
}





