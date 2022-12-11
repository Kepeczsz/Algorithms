import sac.game.AlphaBetaPruning;
import sac.game.GameSearchAlgorithm;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Connect4 game = new Connect4(true);

        Connect4.setHFunction(new Heuristic());
        GameSearchAlgorithm algo = new AlphaBetaPruning();
        algo.setInitial(game);

        while(!game.isSolution()) {
            if(game.isMaximizingTurnNow()) {
                System.out.println("Player");
                System.out.print("Enter a column number (0 - " + (game.getColumns() - 1) + "): ");
                while (true) {
                    int playerMove = input.nextInt();
                    if(playerMove >= game.getColumns()) {
                        continue;
                    } else{
                        game.makeMove(playerMove);
                    }
                    break;
                }
            }
            else {
                algo.execute();
                Map<String, Double> bestMoves = algo.getMovesScores();
                for (Map.Entry<String, Double> entry : bestMoves.entrySet())
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                String s = algo.getFirstBestMove();
                int aiMove = Integer.parseInt(s);
                game.makeMove(aiMove);
            }
            System.out.println(game);
        }
        System.out.print("Winner: ");
        if(game.isMaximizingTurnNow())
            System.out.println("Ai");
        else
            System.out.println("Player");
    }
}