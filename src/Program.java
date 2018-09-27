import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {

    private ArrayList<Node>nodes = new ArrayList<Node>();
    private Node start;
    private Node end;

    public void run() throws IOException {
        //reading the board and creating Node-elements
        readBoard();
        //solving the board, finding shortest path
        ArrayList<Node> solution = new Solve(start, nodes).aStar();
        //creating a picture from the found solution
        Picture p = new Picture(nodes, solution);

    }

    //Reading from txt file and saving content as string
    private void readBoard() throws IOException {
        File file = new File("./boards/board-2-4.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int x = 1;
        int y = 1;
        while ((st = br.readLine()) != null) {
            char[] br_list = st.toCharArray();
            System.out.println(st);
            for (char c : br_list) {
                Node n = new Node(x, y, c);
                nodes.add(n);
                //Finding start and end node while creating
                if (c == 'A') {
                    this.start = n;
                } else if (c == 'B') {
                    this.end = n;
                }
                x++;
            }
            x = 1;
            y++;
        }
        br.close();
        //calling make estimates after all nodes are created
        makeEstimates();
    }

    //loops through all nodes and sets an estimate for each of them
    private void makeEstimates(){
        for (Node n : nodes){
            estimateManhattan(n);
        }
    }

    //calculates the Manhattan-distance from node n to end-node
    private void estimateManhattan(Node n) {
        int sx = n.getX();
        int sy = n.getY();
        int ex = end.getX();
        int ey = end.getY();
        int dx = Math.abs(sx-ex);
        int dy = Math.abs(sy-ey);
        int distance = dx+dy;
        //sets estimate for node n
        n.setEstimate(distance);
    }
}
