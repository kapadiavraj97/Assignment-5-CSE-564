import java.util.*;

public class TSPCalculation {
    private List<List<Integer>> routeNodes=new ArrayList<>();
    private List<List<Integer>> cost=new ArrayList<>();

    public List<List<Integer>> getRouteNodes() {
        return routeNodes;
    }

    public List<List<Integer>> getCost() {
        return cost;
    }

    public int[][] distanceCalculation(List<Integer> xCoordinates, List<Integer> yCoordinates) {
        int[][] distanceMatrix = new int[xCoordinates.size()][yCoordinates.size()];
        for (int i = 0; i < xCoordinates.size() - 1; i++) {
            for (int j = i + 1; j < xCoordinates.size(); j++) {
                double a = Math.sqrt((xCoordinates.get(i) - xCoordinates.get(j)) * (xCoordinates.get(i) - xCoordinates.get(j)) + (yCoordinates.get(i) - yCoordinates.get(j)) * (yCoordinates.get(i) - yCoordinates.get(j)));
                distanceMatrix[i][j] = (int) a;
                distanceMatrix[j][i] = (int) a;
            }
        }
        return distanceMatrix;
    }

    public void tsp(int adjacencyMatrix[][]) {
        for (int nodePosition = 0; nodePosition < adjacencyMatrix.length; nodePosition++) {
            int initial = nodePosition;
            List<Integer> list = new ArrayList<Integer>();
            list.add(nodePosition);
            int result = 0;
            for (int i = 0; i < adjacencyMatrix.length - 1; i++) {
                int min = 9999;
                int minpos = 0;
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    if (min > adjacencyMatrix[j][initial] && !list.contains(j)) {
                        min = adjacencyMatrix[j][initial];
                        minpos = j;
                    }
                }
                result = result + min;
                list.add(minpos);
                initial = minpos;
            }
            List<Integer> list3 = new ArrayList<>();
            list3.add(result + adjacencyMatrix[list.get(0)][list.get(list.size()-1)]);
            list3.add(list.get(0));
            cost.add(list3);
            routeNodes.add(list);
        }
        Collections.sort(cost,(o1,o2) -> {
            return o1.get(0)-o2.get(0);
        });
    }


}
