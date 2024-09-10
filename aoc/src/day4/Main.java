package day4;

import day4.classes.Card;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new FileReader("src/day4/input/input"));

        List<Card> cards = new ArrayList<>();
        Card prevCard = null;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] split = line.split("( )+\\|( )+");

            List<Integer> cardNumbers =
                    Arrays.stream(split[1].split("( )+")).map(Integer::parseInt).toList();
            List<Integer> winningNumbers =
                    Arrays.stream(split[0].split(":( )+")[1].split("( )+")).map(Integer::parseInt).toList();
            Card card = new Card(winningNumbers, cardNumbers);
            cards.add(card);
            if (prevCard != null) {
                prevCard.addNext(card);
            }
            prevCard = card;
        }
        int points = 0;

        for (Card card : cards) {
            points += card.play();
        }
        System.out.println(points);
    }
}