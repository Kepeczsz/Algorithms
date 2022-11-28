import sac.State;
import sac.StateFunction;

public class Heuristic extends StateFunction {
    public double calculate(State state) {
        Connect4 connect4 = (Connect4) state;

        double playerScore = 0;
        double aiScore = 0;

        if (connect4.isSolution() && !connect4.isMaximizingTurnNow())     //wariant z wygraną gracza
            return Double.POSITIVE_INFINITY;
        else if (connect4.isSolution() && connect4.isMaximizingTurnNow())     ////wariant z wygraną AI
            return Double.NEGATIVE_INFINITY;

        if(connect4.isMaximizingTurnNow()){
            playerScore += connect4.isClose();
            }
         else if(!connect4.isMaximizingTurnNow()) {
            aiScore += connect4.isClose();
        }

        return -(aiScore - playerScore);
        }
    }

