import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    private static ArrayList<Node> nodes = new ArrayList<Node>();
    private Node startnode;
    private Node endnode;


    public static void main(String[] args) throws IOException {
        Program p = new Program();
        p.run();
        System.out.println("helloworlds");
    }

}