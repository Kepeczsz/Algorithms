import sac.graph.*;

import java.util.Iterator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        SlidingPuzzles slidingPuzzles = new SlidingPuzzles();
        slidingPuzzles.shuffleBoard(1000);

        slidingPuzzles.setHFunction(new ManhattanHeuristic());
      //slidingPuzzles.setHFunction(new MissPlacedTilesHeuristic());




        GraphSearchAlgorithm algo = new AStar(slidingPuzzles);
        algo.execute();

        GraphState solutions = algo.getSolutions().get(0);

        List<GraphState> path = solutions.getPath();
        Iterator<GraphState> iterator = path.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + "-----");
        }
//
//
//

        System.out.println("Time [ms]: " + algo.getDurationTime());
        System.out.println("Closed: " + algo.getClosedStatesCount());
        System.out.println("Open: " + algo.getOpenSet().size());
        System.out.println("Path: " + path.size());
    }
}