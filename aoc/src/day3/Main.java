package day3;

import day3.classes.Schematic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day3/input/input"));
        List<char[]> schem = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            schem.add(line.toCharArray());
        }
        Schematic schema = new Schematic(schem);
        schema.findPartNumbers();
        System.out.println(schema.getGearRatios());
    }


}