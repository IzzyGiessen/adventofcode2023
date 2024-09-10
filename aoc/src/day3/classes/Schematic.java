package day3.classes;

import java.util.*;

public class Schematic {
    private int maxX;
    private int maxY;
    private List<char[]> schema;
    private List<Integer> partNumbers;
    private Map<Integer, List<Integer>> gearNeighbours;

    public Schematic(List<char[]> schema) {
        this.schema = schema;
        this.maxX = schema.get(0).length;
        this.maxY = schema.size();
        this.partNumbers = new ArrayList<>();
        this.gearNeighbours = new HashMap<>();
    }

    public void findPartNumbers() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                char c = schema.get(y)[x];
                if (!Character.isDigit(c)) continue;
                boolean isPartNumber = false;
                String num = "";
                Set<Integer> gearsSet = new HashSet<>();
                do {
                    List<Integer> gears = checkPartNumber(x++, y, num.isEmpty());
                    num += c;
                    if (gears != null) {
                        isPartNumber = true;
                        gearsSet.addAll(gears);
                    }

                    if (x >= maxX) break;
                    c = schema.get(y)[x];
                } while (Character.isDigit(c));
                if (isPartNumber) {
                    int intNum = Integer.parseInt(num);
                    for (Integer gear : gearsSet) {
                        if (!gearNeighbours.containsKey(gear)) {
                            gearNeighbours.put(gear, new ArrayList<>());
                        }
                        gearNeighbours.get(gear).add(intNum);
                    }
                    partNumbers.add(intNum);
                }
            }
        }
    }

    public List<Integer> checkPartNumber(int x, int y, boolean isNewNumber) {
        List<Integer> gears = new ArrayList<>();
        int[] ys = new int[] {y-1, y, y+1};
        int[] xs = isNewNumber ? new int[] {x-1, x, x+1} : new int[] {x+1};

        boolean isPartNumber;
        for (int xc : xs) {
            if (xc < 0 || xc >= maxX) continue;
            for (int yc : ys) {
                if (yc < 0 || yc >= maxX) continue;
                char c = schema.get(yc)[xc];
                isPartNumber = c != '.' && !Character.isDigit(c);
                if (isPartNumber) {
                    if (c == '*') gears.add(xc * maxY + yc);
                    return gears;
                }
            }
        }
        return null;
    }

    public int getGearRatios() {
        int ratios = 0;
        for (int gear : gearNeighbours.keySet()) {
            if (gearNeighbours.get(gear).size() == 2) {
                ratios += gearNeighbours.get(gear).get(0) * gearNeighbours.get(gear).get(1);
            }
        }
        return ratios;
    }

    public int getPartNumberSum() {
        int sum = 0;
        for (int partNumber : partNumbers) {
            sum += partNumber;
        }
        return sum;
    }
}
