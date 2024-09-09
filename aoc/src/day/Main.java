package day;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/dayX/input/sample"));
        System.out.println(s.nextLine());
    }
}