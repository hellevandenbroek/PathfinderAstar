public class Node {

    private int y;
    private int x;
    private int nodecost;
    private int distance;
    private int estimate;
    private String type;
    private Node parent;

    public Node(int x, int y, char c) {
        this.x = x;
        this.y = y;

        switch(c){
            case 'A' : this.type = "start"; break;
            case 'B' : this.type = "end"; break;
            case '.' : this.type = "open"; break;
            case '#' : this.type = "wall"; break;
            case 'w' : this.type = "water"; this.nodecost = 100; break;
            case 'm' : this.type = "mountain"; this.nodecost = 50; break;
            case 'f' : this.type = "forest"; this.nodecost = 10; break;
            case 'g' : this.type = "grassland"; this.nodecost = 5; break;
            case 'r' : this.type = "road"; this.nodecost = 1; break;
        }
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getNodecost() {
        return nodecost;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public String getType() {
        return this.type;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getEstimate(){
        return this.estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getTotalNodeCost() {
        return this.estimate + this.distance;
    }
}
