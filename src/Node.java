public class Node {

    int y;
    int x;
    int nodecost;
    int distance;
    int estimate;
    String type;
    Node parent;

    public Node(int x, int y, char z) {
        this.x = x;
        this.y = y;

        if (z == '.') {
            this.type = "open";
        }
        else if (z == 'A') {
            this.type = "start";
        }
        else if (z == 'B') {
            this.type = "end";
        }
        else if (z == '#') {
            this.type = "wall";
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
}
