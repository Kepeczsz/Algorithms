import sac.game.GameState;
import sac.game.GameStateImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connect4 extends GameStateImpl {


    private int rows = 7;
    private int columns = 6;
    private char[][] board;
    private char player1Sign = 'O';
    private char player2Sign = 'X';

    public int lastCol = -1, lastRow = -1;
    public Connect4(Boolean whoseTurn) {
        this.board = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }
        setMaximizingTurnNow(whoseTurn);
    }

    public char[][] getBoard() {
        return board;
    }
    public int getColumns() {
        return columns;
    }

    public Connect4(Connect4 parent) {
        board = new char[rows][columns];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                board[i][j] = parent.board[i][j];
        this.setMaximizingTurnNow(parent.isMaximizingTurnNow());
    }

    public String toString() {
        int rCounter = rows;
        for (int i = 0; i < rows; i++) {
            System.out.print("\n" + rCounter-- + " ");
            for (int j = 0; j < columns; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.print("|");
        }
        return "";
    }

    @Override
    public int hashCode() {
        char[] connect4Flat = new char[rows * columns];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                connect4Flat[k++] = board[i][j];
            }
        }
        return Arrays.hashCode(connect4Flat);
    }

    public boolean makeMove(int column) {
        if(isSolution()) {
            System.out.println("gg");
            return true;
        }
        for (int i = rows - 1; i >= 0;i--) {
            lastRow = i;
            lastCol = column;
            if (board[i][column] == ' ') {
                if (isMaximizingTurnNow())
                    board[i][column] = player1Sign;
                else
                    board[i][column] = player2Sign;
                this.setMaximizingTurnNow(!isMaximizingTurnNow());

                return true;
            }
        }
            return false;
    }

    @Override
    public List<GameState> generateChildren() {
        List<GameState> children = new ArrayList();

        for(int i =0;i<columns;i++){
            Connect4 child = new Connect4(this);
            if(child.makeMove(i)){
                child.setMoveName(Integer.toString(i));
                children.add(child);
            }
        }
        return children;
    }
    public String horizontal() {
        return new String(board[lastRow]);
    }
    public String vertical() {
        StringBuilder sb = new StringBuilder(rows);
        for (int h = 0; h < rows; h++) {
            sb.append(board[h][lastCol]);
        }
        return sb.toString();
    }
    public String backslashDiagonal() {
        StringBuilder sb = new StringBuilder(rows);
        for (int h = 0; h < rows; h++) {
            int w = lastCol - lastRow + h;
            if (0 <= w && w < columns) {
                sb.append(board[h][w]);
            }
        }
        return sb.toString();
    }
    public String slashDiagonal() {
        StringBuilder sb = new StringBuilder(rows);
        for (int h = 0; h < rows; h++) {
            int w = lastCol + lastRow - h;
            if (0 <= w && w < columns) {
                sb.append(board[h][w]);
            }
        }

        return sb.toString();
    }
    public static boolean contains(String str, String substring) {
        return str.indexOf(substring) >= 0;
    }

    public boolean isSolution() {
        if (lastCol == -1) {
            return false;
        }
        char sym = board[lastRow][lastCol];

        if(lastRow == 0){
            return true;
        }
        String streak = String.format("%c%c%c%c", sym, sym, sym, sym);

        return contains(horizontal(), streak) ||
                contains(vertical(), streak) ||
                contains(slashDiagonal(), streak) ||
                contains(backslashDiagonal(), streak);
    }

    public int isClose(){
        if (lastCol == -1) {
            return 0;
        }
        char sym = board[lastRow][lastCol];

        if(lastRow == 0){
            return 10000;
        }

        String streak1 = String.format("%c", sym);
        String streak2 = String.format("%c%c", sym, sym);
        String streak3 = String.format("%c%c%c", sym, sym, sym);
        String streak4 = String.format("%c%c%c%c", sym, sym, sym, sym);

        if (contains(horizontal(), streak4) ||
                contains(vertical(), streak4) ||
                contains(slashDiagonal(), streak4) ||
                contains(backslashDiagonal(), streak4)){
            return 100;
        }else
        if (contains(horizontal(), streak3) ||
                contains(vertical(), streak3) ||
                contains(slashDiagonal(), streak3) ||
                contains(backslashDiagonal(), streak3)){
            return 50;
        }else
        if (contains(horizontal(), streak2) ||
                contains(vertical(), streak2) ||
                contains(slashDiagonal(), streak2) ||
                contains(backslashDiagonal(), streak2)){
            return 10;
        } else if (contains(horizontal(), streak1) ||
                contains(vertical(), streak1) ||
                contains(slashDiagonal(), streak1) ||
                contains(backslashDiagonal(), streak1)){
            return 1;
        }
        return 0;
    }
}
