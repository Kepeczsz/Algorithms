import sac.State;
import sac.StateFunction;


public class MissPlacedTilesHeuristic extends StateFunction {

    @Override
    public double calculate(State state) {
        int misPlacedTiles = 0;
        SlidingPuzzles slidingPuzzle = (SlidingPuzzles) state;
        byte [][] board = slidingPuzzle.getBoard();
            int cnt = 0;
            for(int i =0 ; i < slidingPuzzle.size; i++){
                for(int j =0; j < slidingPuzzle.size; j++){
                    if(board[i][j] != cnt && board[i][j] != 0){
                        misPlacedTiles++;
                    }
                    cnt++;
                }
            }
        return misPlacedTiles;
    }
}