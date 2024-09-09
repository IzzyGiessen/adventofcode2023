package day2.classes;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Set {
    private List<Pair> sets;

    public Set() {
        sets = new ArrayList<>();
    }

    public List<Pair> getPairs() {
        return this.sets;
    }

    public void addSet(Colors color, int count) {
        sets.add(new Pair(color, count));
    }

    public boolean verify(Map<Colors, Integer> rules) {
        for (Pair set : sets) {
            if (rules.get(set.color) < set.count) {
                return false;
            }
        }
        return true;
    }

    public static Set readLine(String line) {
        String[] pairs = line.strip().split(", ");
        Set set = new Set();
        for (int i = 0; i < pairs.length; i++) {
            String[] pair = pairs[i].split(" ");
            Colors color = Colors.valueOf(pair[1].toUpperCase());
            int count = Integer.parseInt(pair[0]);
            set.addSet(color, count);
        }
        return set;
    }

    public static class Pair {
        public Colors color;
        public int count;
        public Pair(Colors color, int count) {
            this.color = color;
            this.count = count;
        }
    }

    public enum Colors {
        RED, GREEN, BLUE
    }
}
