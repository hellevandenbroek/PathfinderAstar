import java.util.ArrayList;
import java.util.List;

public class Solve {

    private Node start;
    private Node current;

    private ArrayList<Node> nodes = new ArrayList<>();


    public Solve(Node start, ArrayList nodes){
        start.setDistance(0);
        this.nodes = nodes;
    }

    public void aStar(){

        //open set
        List<Node> closedSet = new ArrayList<>();
        List<Node> openSet = new ArrayList<>();

        openSet.add(start);

        while (openSet.size() != 0) {
            for (Node n : openSet) {
                if ((current == null) | (n.getNodecost() < current.getNodecost())) {
                    current = n;
                }
            }
            openSet.remove(current);
            closedSet.add(current);
        }

        //henter ut og lagrer alle naboer til noden
        ArrayList<Node> nb = getNeighbors(current);


        //dette funker ikke i det hele tatt. fiks
        for (Node neighbor : nb) {
            if (closedSet.contains(neighbor)) {
                continue;
            }
            if (neighbor.getType() == "closed") {
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
        //Only for debugging, remember to remove
        for (Node no : neighbors){
            System.out.println(no.getX());
            System.out.println(no.getY());
        }
        System.out.println(neighbors);
        return neighbors;
    }
}
