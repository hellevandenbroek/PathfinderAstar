import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {

    ArrayList<Node>nodes = new ArrayList<Node>();
    Node start;
    Node end;

    String board = "";
    String visboard = "";

    public Program() {
        // TODO Auto-generated constructor stub
    }

    //metode som leser fra txt fil og lagrer innholdet som en string i board
    public void readBoard() throws IOException {
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
    public void classifyNodes() {
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
            //Sjekker om noden er start eller sluttnode
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

    //kaller en estimeringsmetode på alle noder i nodes
    public void getEstimates(){
        for (int i = 0; i < nodes.size(); i++){
            estimateManhattan(nodes.get(i));
        }
    }

    //regner ut Manhattan distance fra node n til sluttnoden
    public void estimateManhattan(Node n) {
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
    }
}
