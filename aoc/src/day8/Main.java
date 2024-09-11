package day8;

import day8.classes.Node;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day8/input/input"));
        char[] instructions = s.nextLine().toCharArray();
        Map<String, Node> nodes = new HashMap<>();
        List<Node> starts = new ArrayList<>();
        s.nextLine();
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" = ");
            String[] lr = line[1].split(", ");
            Node n = new Node(line[0], lr[0].substring(1), lr[1].substring(0, 3));
            if (line[0].charAt(2) == 'A') starts.add(n);
            nodes.put(line[0], n);
        }
        for (String id : nodes.keySet()) {
            Node n = nodes.get(id);
            n.left = nodes.get(n.leftName);
            n.right = nodes.get(n.rightName);
        }
        List<Long> steps = new ArrayList<>();
        for (Node start : starts) {
            steps.add(walk(instructions, start));
        }
        System.out.println(lcm(steps));
    }

    public static long walk(char[] instr, Node start) {
        int steps = 0;
        Node current = start;
        while (true) {
            current = instr[steps%instr.length] == 'L' ? current.left : current.right;
            steps++;
            if (current.isEndpoint()) return steps;
        }
    }

    public static long lcm(List<Long> xs) {
        return xs.stream().reduce(1l, (x, y) -> (x * y) / gcd(x, y));
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}