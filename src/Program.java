import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Program {

    private ArrayList<Node>nodes = new ArrayList<Node>();
    private Node start;
    private Node end;

    public void run() throws IOException {
        readBoard();
        ArrayList<Node> solution = new Solve(start, end, nodes).aStar();
        Picture p = new Picture(nodes, solution);

    }

    //Reading from txt file and saving content as string
    private void readBoard() throws IOException {
        File file = new File("./boards/board-1-1.txt");
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
