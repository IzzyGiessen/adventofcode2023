package day8.classes;

import java.util.Map;

public class Node {
    public String id;
    public String leftName;
    public String rightName;
    public Node left;
    public Node right;

    public Node(String id, String left, String right) {
        this.id = id;
        this.leftName = left;
        this.rightName = right;
    }

    public boolean isEndpoint() {
        return id.charAt(2) == 'Z';
    }

    @Override
    public String toString() {
        return this.id;
    }
}
