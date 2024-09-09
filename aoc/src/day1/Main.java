package day1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    private static String[] spelledOutNumbers = {
            "one",
            "two", "three",
            "four", "five", "six", "seven", "eight",
            "nine"
    };

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day1/input/input"));
        int total = 0;
        while (s.hasNextLine()) {
            total += getIntSum(s.nextLine());
        }
        System.out.println(total);
    }

    public static int getIntSum(String line) {
        int firstInt = -1;
        int lastInt = firstInt;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                int dig = Character.getNumericValue(c);
                if (firstInt == -1) {
                    firstInt = dig;
                }
                lastInt = dig;
            } else {
                boolean isSpelledOutNumber = false;
                int dig = 0;
                for (String spelledOutNumber : spelledOutNumbers) {
                    isSpelledOutNumber = line.substring(i).startsWith(spelledOutNumber);
                    dig++;
                    if (isSpelledOutNumber) break;
                }
                if (!isSpelledOutNumber) continue;
                if (firstInt == -1) {
                    firstInt = dig;
                }
                lastInt = dig;
            }
        }
        return firstInt*10 + lastInt;
    }
}