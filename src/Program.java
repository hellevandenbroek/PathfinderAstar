import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {

    private ArrayList<Node>nodes = new ArrayList<Node>();
    private Node start;
    private Node end;
    //This is the board as a string.
    private String board = "";
    private String visboard = "";

    public void run() throws IOException {
        readBoard();
        ArrayList<Node> solution = new Solve(start, end, nodes).aStar();
    }

    //Reading from txt file and saving content as string
    private void readBoard() throws IOException {
        File file = new File("./boards/board-1-1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            this.board += st;
            this.visboard += "\n" + st;
        }
        System.out.println(visboard);
        br.close();

        //start på null
        int x = 1;
        int y = 1;
        int width = 20;
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);

            if (i % (width) == 0 && i!=0) {
                y ++;
                x= 1;
            }

            //create a node for each char with coordinates
            Node n = new Node(x, y, c);
            nodes.add(n);

            //checks whether node is end or startnode
            if (c == 'A') {
                this.start = n;
            }
            else if (c == 'B'){
                this.end = n;
            }
            x ++;
        }
        makeEstimates();
    }

    //Loops through all nodes and sets an estimate for each of them
    private void makeEstimates(){
        for (Node n : nodes){
            estimateManhattan(n);
        }

    }

    //regner ut Manhattan distance fra node n til sluttnoden
    private void estimateManhattan(Node n) {
        int sx = n.getX();
        int sy = n.getY();
        int ex = end.getX();
        int ey = end.getY();
        int dx = Math.abs(sx-ex);
        int dy = Math.abs(sy-ey);
        int distance = dx+dy;
        //Sets estimate for node n
        n.setEstimate(distance);
    }
}
