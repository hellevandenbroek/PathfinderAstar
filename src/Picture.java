import java.util.ArrayList;

public class Picture {

    private ArrayList<Node> solution;
    private ArrayList<Node> board;
    private String p = "";

    public Picture (ArrayList<Node> nodes, ArrayList<Node> solution){
        this.solution = solution;
        this.board = nodes;
        constructAnswer();
    }

    private void constructAnswer() {
        for (Node n : board){
            char c = '.';
            if (solution.contains(n)){
                c = 'o';
            }
            else{
                if (n.getType().equals("start")){
                    c = 'A';
                }
                else if (n.getType().equals("end")){
                    c = 'B';
                }
                else if (n.getType().equals("wall")){
                    c = '#';
                }
                else if (n.getType().equals("open")){
                    c = '.';
                }
            }
            String s = Character.toString(c);
            if (n.getX() == 1){
                p += "\n";
            }
            p += s;
        }
        System.out.println(p);
    }
}