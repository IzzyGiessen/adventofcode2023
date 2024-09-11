package day5;

import day5.classes.Category;
import day5.classes.Mapping;
import day5.classes.Range;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day5/input/input"));

        List<Long> seedsNumbers = Arrays.stream(s.nextLine().split(": ")[1].split(" ")).map(Long::parseLong).toList();
        List<Range> seeds = new ArrayList<>();
        for (int i = 0; i < seedsNumbers.size(); i += 2) {
            seeds.add(new Range(seedsNumbers.get(i), seedsNumbers.get(i+1)));
        }
        s.nextLine();
        Map<String, Category> categories = new HashMap<>();

        while (s.hasNextLine()) {
            // remove empty line
            String[] mapNames = s.nextLine().split(" ")[0].split("-to-");
            String fromName = mapNames[0];
            String toName = mapNames[1];
            if (!categories.containsKey(fromName)) {
                categories.put(fromName, new Category(fromName));
            }
            if (!categories.containsKey(toName)) {
                categories.put(toName, new Category(toName));
            }
            Category from = categories.get(fromName);
            Category to = categories.get(toName);
            Mapping map = new Mapping(from, to);
            from.addMap(map);

            String line;
            while (s.hasNextLine()) {
                line = s.nextLine();
                if (line.isEmpty()) break;
                String[] range = line.split(" ");
                long rangeFrom = Long.parseLong(range[1]);
                long rangeTo = Long.parseLong(range[0]);
                long length = Long.parseLong(range[2]);
                map.addRange(rangeFrom, rangeTo, length);
            }
        }
        List<Range> res = new ArrayList<>();
        for (Range seed : seeds) {
            resetCategories(categories);
            Category seedC = categories.get("seed");
            List<Range> se = new ArrayList<>();
            se.add(seed);
            seedC.seed = se;
            res.addAll(getLocation(seedC));
        }
        long min = Long.MAX_VALUE;
        for (Range re : res) {
            min = Math.min(min, re.start);
        }
        System.out.println(min);
    }

    public static List<Range> getLocation(Category seed) {
        Queue<Category> neighbours = new LinkedList<>();
        neighbours.add(seed);

        while (!neighbours.isEmpty()) {
            Category c = neighbours.poll();
            if (c.name.equals("location")) {
                return c.seed;
            }
            for (Mapping map : c.getMaps()) {
                if (map.to.seed != null) continue;
                neighbours.add(map.to);
                map.to.seed = new ArrayList<>();
                for (Range r : map.from.seed) {
                    map.to.seed.addAll(map.map(r));
                }
            }
        }
        return new ArrayList<>();
    }

    public static void resetCategories(Map<String, Category> categories) {
        for (String s : categories.keySet()) {
            categories.get(s).seed = null;
        }
    }
}