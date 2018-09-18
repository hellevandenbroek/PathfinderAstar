import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {

    ArrayList<Node>nodes = new ArrayList<Node>();

    Node start;
    Node end;

    //20 + 1
    String board = "";
    String visboard = "";

    public Program() {
        // TODO Auto-generated constructor stub
    }

    public void readBoard() throws IOException {
        File file = new File("./boards/board-1-3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        //skriver ut board og lagrer den i board
        String st;
        while ((st = br.readLine()) != null) {
            this.board += st;
            this.visboard += "\n" + st;

        }
        //just for prettyness
        System.out.println(visboard);
        //System.out.println(board);
        //System.out.println(board.length());
        //System.out.println(visboard.length());
        classifyNodes();
        br.close();
    }

    public void classifyNodes() {
        int x = 1;
        int y = 1;
        int bredde = 20;
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);

            //sjekker om vi har funnet start eller slutt

            if (i % (bredde) == 0 && i!=0) {
                y ++;
                x= 1;
            }

            Node n = new Node(x, y, c);
            nodes.add(n);

            if (c == 'A') {
                this.start = n;
            }
            else if (c == 'B'){
                this.end = n;
            }

            x ++;
        }

        //for pretty printing

        int sx = start.getX();
        int sy = start.getY();
        int ex = end.getX();
        int ey = end.getY();

        System.out.println("A is located at: (" + sx + "," + sy + ")");
        System.out.println("B is located at: (" + ex + "," + ey + ")");

    /*
    for (int i = 0; i < nodes.size(); i ++) {
        System.out.println(nodes.get(i).getType());
    }*/

        estimateManhattan(nodes.get(7));

    }

    public void estimateManhattan(Node n) {
        int sx = n.getX();
        int sy = n.getY();
        int ex = end.getX();
        int ey = end.getY();
        System.out.println("A is located at: (" + sx + "," + sy + ")");
        System.out.println("B is located at: (" + ex + "," + ey + ")");
        int dx;
        int dy;

        if (sx > ex) {
            dx = sx-ex;
        }
        else {
            dx = ex-sx;
        }
        if (sy > ey) {
            dy = sy-ey;
        }
        else {
            dy = ey-sy;
        }


        System.out.println(dx + ", " + dy);
        int d = dx+dy;
        System.out.println("Manhattan distance: " + d);
    }


    public void run() throws IOException {
        readBoard();
    }
}
