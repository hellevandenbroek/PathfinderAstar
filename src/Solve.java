import java.util.ArrayList;
import java.util.List;

public class Solve {

    private Node start;
    private Node goal;
    private ArrayList<Node> nodes = new ArrayList<>();

    public Solve(Node start, ArrayList nodes) {
        start.setDistance(0);
        this.start = start;
        this.nodes = nodes;
    }


    //A* algorithm
    public ArrayList<Node> aStar(){
        List<Node> closedSet = new ArrayList<>();
        List<Node> openSet = new ArrayList<>();
        openSet.add(start);

        while(openSet.size() != 0){
            Node current = null;
            for (Node n : openSet) {
                if(current == null){
                    current = n;
                }
                if ((n.getTotalNodeCost() < current.getTotalNodeCost())) {
                    current = n;
                }
            }
            if (current.getType().equals("end")){
                this.goal = current;
                break;
            }
            openSet.remove(current);
            closedSet.add(current);

            //Calling getNeighbors for our current node
            ArrayList<Node> nb = getNeighbors(current);

            //Finding shortest path
            for (Node neighbor : nb) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                if ("wall".equals(neighbor.getType())) {
                    continue;
                }
                int tentative = current.getDistance() + neighbor.getNodecost();
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentative >= neighbor.getDistance()) {
                    continue;
                }
                neighbor.setParent(current);
                neighbor.setDistance(tentative);
            }
        }
        return getSolution();
    }

    //Function that gets all the immediate neighbors for a node.
    private ArrayList<Node> getNeighbors(Node n){
        ArrayList<Node> neighbors = new ArrayList<>();
        int x = n.getX();
        int y = n.getY();
        for (Node node : nodes) {
            int ix = node.getX();
            int iy = node.getY();
            if (((x == ix & y == iy + 1) | (x == ix && y == iy - 1)) | ((y == iy & x == ix + 1) | (y == iy & x == ix - 1))) {
                neighbors.add(node);
            }
        }
        return neighbors;
    }

    //Getting our solution by tracing back all parent nodes for each node traversed
    private ArrayList<Node> getSolution(){
        ArrayList<Node> solution = new ArrayList<>();
        solution.add(this.goal);
        Node parent = this.goal.getParent();

        while (parent != null){
            solution.add(parent);
            parent = parent.getParent();
        }
        return solution;
    }
}
