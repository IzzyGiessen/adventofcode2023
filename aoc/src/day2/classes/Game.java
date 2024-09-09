package day2.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private int id;
    private List<Set> sets;

    public Game(int id) {
        this.id = id;
        this.sets = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void addSet(Set set) {
        sets.add(set);
    }

    public int getPower() {
        Map<Set.Colors, Integer> counts = new HashMap<>();
        for (Set set : sets) {
            for (Set.Pair p : set.getPairs()) {
                counts.put(p.color, Math.max(p.count, counts.getOrDefault(p.color, 0)));
            }
        }
        return counts.get(Set.Colors.BLUE) * counts.get(Set.Colors.GREEN) * counts.get(Set.Colors.RED);
    }

    public boolean verify(Map<Set.Colors, Integer> rules) {
        for (Set set : sets) {
            if (!set.verify(rules)) return false;
        }
        return true;
    }

    public static Game readLine(String line) {
        String[] colonSplit = line.split(":");
        Game game = new Game(Integer.parseInt(colonSplit[0].split(" ")[1]));
        String[] setsString = colonSplit[1].split(";");
        for (String setString : setsString) {
            game.addSet(Set.readLine(setString));
        }
        return game;
    }
}
