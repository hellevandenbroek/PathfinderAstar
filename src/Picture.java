import java.util.ArrayList;

public class Picture {

    private ArrayList<Node> solution;
    private ArrayList<Node> board;
    private String p = "";
    private String q = "";

    public Picture (ArrayList<Node> nodes, ArrayList<Node> solution){
        this.solution = solution;
        this.board = nodes;
        constructAnswer();
        constructAnswer2();
    }

    private void constructAnswer() {
        for (Node n : board){
            char c;
            String t = n.getType();

            switch (t) {
                case "wall": c = '#'; break;
                case "open": c = '.'; break;
                case "water": c = 'w'; break;
                case "mountain": c = 'm'; break;
                case "forest": c = 'f'; break;
                case "grassland": c = 'g'; break;
                case "road": c = 'r'; break;
                default: c = '.'; break;
            }

            if (solution.contains(n)) c = 'o';
            if (t.equals("start")) c = 'A';
            if (t.equals("end")) c = 'B';

            String s = Character.toString(c);
            if (n.getX() == 1){
                p += "\n";
            }
            p += s;
        }

        //this prints the solution board
        System.out.println(p);
    }

    //I made constructAnswer2 which prints a board that does not show the different node-types, only the solution
    private void constructAnswer2(){
        for (Node n : board){
            char c = '.';
            String t = n.getType();

            if (solution.contains(n)) c = 'o';
            if (t.equals("start")) c = 'A';
            if (t.equals("end")) c = 'B';

            String s = Character.toString(c);
            if (n.getX() == 1){
                q += "\n";
            }
            q += s;
        }

        //this prints the solution board
        System.out.println(q);
    }
}