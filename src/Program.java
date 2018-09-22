import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {

    private ArrayList<Node>nodes = new ArrayList<Node>();
    private Node start;
    private Node end;

    private String board = "";
    private String visboard = "";

    public Program() {
        // TODO Auto-generated constructor stub
    }

    //metode som leser fra txt fil og lagrer innholdet som en string i board
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
        classifyNodes();
    }

    //metode som finner leser gjennom board og lager noder. Sjekker også om vi finner A/B (start/slutt)
    private void classifyNodes() {
        int x = 1;
        int y = 1;
        int bredde = 20;
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);

            if (i % (bredde) == 0 && i!=0) {
                y ++;
                x= 1;
            }
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
        getEstimates();
    }

    //gets an estimate on all nodes
    private void getEstimates(){
        for (int i = 0; i < nodes.size(); i++){
            estimateManhattan(nodes.get(i));
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
        //setter estimatet for noden
        n.estimate = distance;
    }

    public void run() throws IOException {
        readBoard();
        Solve s = new Solve(start, nodes);
        s.aStar();
    }
}
