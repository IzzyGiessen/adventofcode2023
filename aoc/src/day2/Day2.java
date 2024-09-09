package day2;

import day2.classes.Game;
import day2.classes.Set;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Day2 {
    public static Map<Set.Colors, Integer> rules = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        rules.put(Set.Colors.RED, 12);
        rules.put(Set.Colors.GREEN, 13);
        rules.put(Set.Colors.BLUE, 14);

        Scanner s = new Scanner(new FileReader("src/day2/input/input"));
        List<Game> games = new ArrayList<>();
        while (s.hasNextLine()) {
            games.add(Game.readLine(s.nextLine()));
        }

        int sum = 0;
        for (Game g : games) {
            sum += g.getPower();
        }
        System.out.println(sum);
    }
}