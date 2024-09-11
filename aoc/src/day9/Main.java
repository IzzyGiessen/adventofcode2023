package day9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day9/input/input"));

        int sum = 0;
        while (s.hasNextLine()) {
            List<Integer> numbers = Arrays.stream(s.nextLine().split(" ")).map(Integer::parseInt).toList();
            sum += findNext(numbers);
        }
        System.out.println(sum);
    }

    public static int findNext(List<Integer> numbers) {
        List<Integer> newNumbers = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < numbers.size()-1; i++) {
            int newNumber = numbers.get(i+1) - numbers.get(i);
            newNumbers.add(newNumber);
            sum += newNumber;
        }
        if (sum == 0) return numbers.getFirst();

        return numbers.getFirst() - findNext(newNumbers);
    }
}