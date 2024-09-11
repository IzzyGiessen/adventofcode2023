package day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day6/input/input"));
        Long time = Long.parseLong(String.join("",
                Arrays.stream(s.nextLine().split(":( )+")[1].split(" ")).toList()));
        Long distance = Long.parseLong(String.join("",
                Arrays.stream(s.nextLine().split(":( )+")[1].split(" ")).toList()));

        long lowestCharge = binarySearchCharge(time, distance, true);
        long highestCharge = binarySearchCharge(time, distance, false);
        System.out.println(highestCharge - lowestCharge);
    }

    public static long binarySearchCharge(long time, long distance, boolean findLowest) {
        long a = 0;
        long b = time;

        while (a != b) {
            long mid = (a+b) / 2;
            if (beatsRecord(time, mid, distance) ^ !findLowest) {
                b = mid;
            } else {
                a = mid+1;
            }
        }
        return a;
    }

    public static boolean beatsRecord(long time, long charge, long record) {
        return (time - charge) * charge > record;
    }
}