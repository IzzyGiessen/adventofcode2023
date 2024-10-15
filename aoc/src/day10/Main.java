package day10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day10/input/sample"));
        List<char[]> map = new ArrayList<>();

        int sx = 0;
        int sy = 0;
        int y = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.indexOf('S') != -1) {
                sx = line.indexOf('S');
                sy = y;
            }
            map.add(line.toCharArray());
            y++;
        }
        boolean[][] loop = getLoop(map, sx, sy);
        List<Coord> innerSquares = findInnerSquare(loop);

        boolean[][] visited = new boolean[loop.length][loop[0].length];
        for (Coord innerSquare : innerSquares) {
            findInnerSpace(loop, visited, innerSquare);
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[0].length; j++) {
                    System.out.print(visited[i][j] ? "0 " : "- ");
                }
                System.out.println();
            }
        }
    }

    public static List<Coord> findInnerSquare(boolean[][] loop) {
        List<Coord> allFree = new ArrayList<>();
        for (int y = 0; y < loop.length; y++) {
            for (int x = 0; x < loop[0].length; x++) {
                if (!loop[y][x]) continue;
                int free = 0;
                List<Coord> coords = new ArrayList<>();
                if (y > 0) {
                    if (!loop[y-1][x]) {
                        free++;
                        coords.add(new Coord(x, y-1));
                    }
                }
                if (y < loop.length-1) {
                    if (!loop[y+1][x]) {
                        free++;
                        coords.add(new Coord(x, y+1));
                    }
                }
                if (x < loop[0].length-1) {
                    if (!loop[y][x+1]) {
                        free++;
                        coords.add(new Coord(x+1, y));
                    }
                }
                if (x > 0) {
                    if (!loop[y][x-1]) {
                        free++;
                        coords.add(new Coord(x-1, y));
                    }
                }
                if (free > 1) allFree.addAll(coords);
            }
        }
        return allFree;
    }

    public static boolean[][] findInnerSpace(boolean[][] loop, boolean[][] visited, Coord s) {
        Queue<Coord> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            Coord current = q.poll();
            int x = current.x; int y = current.y;
            visited[y][x] = true;

            int[][] neighbours = new int[][] {{x-1, x, x+1}, {y-1, y, y+1}};
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int nx = neighbours[0][i];
                    int ny = neighbours[1][j];
                    if (visited[ny][nx] || loop[ny][nx]) continue;
                    q.add(new Coord(nx, ny));
                }
            }
        }
        return visited;
    }

    public static boolean[][] getLoop(List<char[]> map, int sx, int sy) {
        int[][] distances = new int[map.size()][map.getFirst().length];
        boolean[][] visited = new boolean[map.size()][map.getFirst().length];
        Queue<Coord> q = new LinkedList<>();
        q.add(new Coord(sx, sy));
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().length; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }
        distances[sy][sx] = 0;

        while (!q.isEmpty()) {
            Coord current = q.poll();
            int x = current.x; int y = current.y;
            char symbol = map.get(y)[x];
            visited[y][x] = true;

            switch (symbol) {
                case 'S' -> checkCoord(q, visited, distances, new Coord(x, y+1), new Coord(x-1, y), distances[y][x]);
                case '-' -> checkCoord(q, visited, distances, new Coord(x-1, y), new Coord(x+1, y), distances[y][x]);
                case '|' -> checkCoord(q, visited, distances, new Coord(x, y-1), new Coord(x, y+1), distances[y][x]);
                case 'L' -> checkCoord(q, visited, distances, new Coord(x, y-1), new Coord(x+1, y), distances[y][x]);
                case 'F' -> checkCoord(q, visited, distances, new Coord(x, y+1), new Coord(x+1, y), distances[y][x]);
                case 'J' -> checkCoord(q, visited, distances, new Coord(x, y-1), new Coord(x-1, y), distances[y][x]);
                case '7' -> checkCoord(q, visited, distances, new Coord(x, y+1), new Coord(x-1, y), distances[y][x]);
            }
        }

        return visited;
    }

    public static void checkCoord(Queue<Coord> q, boolean[][] visited, int[][] distances, Coord p1, Coord p2, int distance) {
        for (Coord p : new Coord[] {p1, p2}) {
            if (p.x >= 0 && p.x < visited[0].length && p.y >= 0 && p.y < visited.length) {
                if (!visited[p.y][p.x]) {
                    distances[p.y][p.x] = Math.min(distances[p.y][p.x], distance+1);
                    q.add(p);
                }
            }
        }
    }

    static class Coord {
        public int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ", " + y;
        }
    }
}