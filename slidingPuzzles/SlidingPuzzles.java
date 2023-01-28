import sac.graph.GraphState;
import sac.graph.GraphStateImpl;

import java.util.*;

public class SlidingPuzzles extends GraphStateImpl {

    static Random rand = new Random(100);

    int size =3;
    byte[][] board;
    public int xZeroPos =0;
    public int yZeroPos =0;

    public byte[][] getBoard() {
        return board;
    }

    @Override
    public boolean isSolution() {
        int k = 0;
            for(int i = 0; i< size;i++){
                for(int j = 0 ; j < size; j++){
                    if(board[i][j] != k){
                        return false;
                    }
                    k++;
                }
            }
            return true;
    }

    enum PossibleMoves {
        Left(0),
        Right(1),
        Top(2),
        Down(3);
        private int direction;
        PossibleMoves(final int dir) { direction = dir; }
        private static Map<Integer, PossibleMoves> map = new HashMap<Integer, PossibleMoves>();

        static {
            for (PossibleMoves possibleMoves : PossibleMoves.values()) {
                map.put(possibleMoves.direction, possibleMoves);
            }
        }
        public static PossibleMoves valueOf(int dir) {
            return map.get(dir);
        }
    }

   public SlidingPuzzles(){
        byte counter = 0;
        board = new byte[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = counter++;
    }
    public  SlidingPuzzles(SlidingPuzzles parent){
        board = new byte[size][size];
        xZeroPos = parent.xZeroPos;
        yZeroPos = parent.yZeroPos;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = parent.board[i][j];
    }

    public String toString() {
        StringBuilder txt = new StringBuilder();
        for(byte i = 0; i < board.length; ++i) {
            for(byte j = 0; j < board.length; ++j) {
                txt.append(board[i][j] + " ");
            }
            txt.append("\n");
        }
        System.out.println(txt);
        return txt.toString();
    }
    public boolean makeMove(PossibleMoves possibleMove){
            switch(possibleMove){
                case Top:
                    if(yZeroPos > 0){
                        byte puzzleTopZero = board[yZeroPos-1][xZeroPos];
                        board[yZeroPos][xZeroPos] = puzzleTopZero;
                        yZeroPos--;
                        board[yZeroPos][xZeroPos] = 0;
                    }
                    return true;

                case Down:
                    if(yZeroPos < (size-1)){
                        byte puzzleDownOfZero = board[yZeroPos+1][xZeroPos];
                        board[yZeroPos][xZeroPos] = puzzleDownOfZero;
                        yZeroPos++;
                        board[yZeroPos][xZeroPos] = 0;
                    }
                    return true;
                case Left:
                    if(xZeroPos > 0){
                        byte puzzleRightOfZero = board[yZeroPos][xZeroPos-1];
                        board[yZeroPos][xZeroPos] = puzzleRightOfZero;
                        xZeroPos--;
                        board[yZeroPos][xZeroPos] = 0;
                    }
                    return true;
                case Right:
                    if(xZeroPos < (size-1)){
                        byte puzzleRightOfZero = board[yZeroPos][xZeroPos+1];
                        board[yZeroPos][xZeroPos] = puzzleRightOfZero;
                        xZeroPos++;
                        board[yZeroPos][xZeroPos] = 0;
                    }
                    return true;
            }
        return false;
    }
    void shuffleBoard(int howManyMoves){

        for(int i = 0; i < howManyMoves;i++){
            int n = rand.nextInt(4);
            makeMove(PossibleMoves.valueOf(n));
        }
    }
    @Override
    public int hashCode() {
        byte[] puzzlesFlat = new byte[size*size];
        int k =0;
        for(int i =0;i<size;i++){
            for(int j =0;j<size;j++){
                puzzlesFlat[k++] = board[i][j];
            }
        }
        return Arrays.hashCode(puzzlesFlat);
    }

    
    @Override
    public List<GraphState> generateChildren() {
        List<GraphState> children = new ArrayList();
        if(isSolution())
            return children;
        for(byte i=0; i<4; i++) {
                SlidingPuzzles child = new SlidingPuzzles(this);

            if(child.makeMove(PossibleMoves.valueOf(i))) {
                children.add(child);
                String name = "";
                switch(PossibleMoves.valueOf(i)) {
                    case Left:
                        name = "L";
                        break;
                    case Right:
                        name = "R";
                        break;
                    case Top:
                        name = "D";
                        break;
                    case Down:
                        name = "T";
                        break;
                }
                child.setMoveName(name);
            }
        }
        return children;
    }

}
