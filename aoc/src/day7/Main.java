package day7;

import day7.classes.Hand;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day7/input/input"));
        PriorityQueue<Hand> hands = new PriorityQueue<>();
        while (s.hasNextLine()) {
            String[] line = s.nextLine().split(" ");
            Hand hand = new Hand(line[0], Integer.parseInt(line[1]));
            hands.add(hand);
        }
        int i = 1;
        int winnings = 0;
        while (!hands.isEmpty()) {
            Hand hand = hands.poll();
            winnings += i++ * hand.getBid();
        }
        System.out.println(winnings);
    }
}