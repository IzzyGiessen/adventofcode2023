package day4.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Card {
    private Set<Integer> winningNumbers;
    private List<Integer> cardNumbers;
    private Card next;
    private int copies;

    public Card(List<Integer> winningNumbers, List<Integer> cardNumbers) {
        this.winningNumbers = new HashSet<>(winningNumbers);
        this.cardNumbers = cardNumbers;
        this.copies = 1;
    }

    public void addNext(Card c) {
        next = c;
    }

    public int getScore() {
        int score = 1;
        for (int cardNumber : cardNumbers) {
            if (winningNumbers.contains(cardNumber)) score *= 2;
        }
        return score == 1 ? 0 : score/2;
    }

    public int getWins() {
        int wins = 0;
        for (int cardNumber : cardNumbers) {
            if (winningNumbers.contains(cardNumber)) wins++;
        }
        return wins;
    }

    public void addCopies(int copies) {
        this.copies += copies;
    }

    public int play() {
        int wins = getWins();
        if (wins == 0) return this.copies;
        Card c = next;
        c.addCopies(this.copies);
        for (int win = 0; win < wins-1; win++) {
            c = c.next;
            c.addCopies(this.copies);
        }
        return this.copies;
    }
}
