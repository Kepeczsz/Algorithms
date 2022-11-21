import sac.State;
import sac.StateFunction;

public class ManhattanHeuristic extends StateFunction {
    public double calculate(State state) {
        SlidingPuzzles slidingPuzzle = (SlidingPuzzles) state;
        byte [][] board = slidingPuzzle.getBoard();
        int cnt = 0;
        byte k = 0;
        for(int i=0; i<slidingPuzzle.size; i++)
            for(int j=0; j<slidingPuzzle.size; j++, k++)
                if(board[i][j] != 0 && board[i][j] != k)
                    cnt += manhattan(slidingPuzzle, board[i][j], i, j);
        return cnt;
    }

    private double manhattan(SlidingPuzzles slidingPuzzle, byte index, int i0, int j0) {
        byte m = 0;
        int i1 = 0, j1 = 0;
        ind:
        for(int i=0; i< slidingPuzzle.size; i++) {
            for (int j = 0; j < slidingPuzzle.size; j++) {
                if (m == index) {
                    i1 = i;
                    j1 = j;
                    break ind;
                }
            }
        }
        return Math.abs(i1-i0) + Math.abs(j1-j0);
//        int iGoal = index / slidingPuzzle.size;
//        int jGoal = index % slidingPuzzle.size;
//        return Math.abs(iGoal -i0)+ Math.abs(j0 - jGoal);
    }
}
