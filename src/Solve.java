import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Solve {

    Node start;
    Node prev;
    Node current;
    Node goal;

    ArrayList<Node> nodes = new ArrayList<Node>();


    public Solve(Node start, ArrayList nodes, Node goal){
        start.setDistance(0);
        this.nodes = nodes;
        this.goal = goal;
    }

    public void aStar(){

        //open set
        List<Node> closedSet = new ArrayList<Node>();
        List<Node> openSet = new ArrayList<Node>();

        openSet.add(start);


        while (openSet.size() != 0) {
            for (int i = 0; i < openSet.size(); i++) {
                Node n = openSet.get(i);
                if ((current == null) | (n.getNodecost() < current.getNodecost())) {
                    current = n;
                }
            }
        }

        openSet.remove(current);
        closedSet.add(current);


        //henter ut og lagrer alle naboer til noden
        ArrayList<Node> nb = getNeighbors(current);


        for (int i = 0; i < nb.size(); i++) {
            Node neighbor = nb.get(i);
            if (closedSet.contains(neighbor)) {
                continue;
            }
            if (!openSet.contains(neighbor)) {
                openSet.add(neighbor);
            }
            this.prev = current;
        }
    }

    public ArrayList<Node> getNeighbors(Node n){
        ArrayList<Node> neighbors = new ArrayList<Node>();
        int x = n.getX();
        int y = n.getY();
        for (int i = 0; i < nodes.size(); i++){
            int ix = nodes.get(i).getX();
            int iy = nodes.get(i).getY();
            if (((x == ix & y == iy+1) | (x== ix && y == iy-1)) | ((y == iy & x == ix +1) | (y == iy & x == ix-1))){
                neighbors.add(nodes.get(i));
            }
        }
        return neighbors;
    }
}
