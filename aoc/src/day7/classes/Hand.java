package day7.classes;

import java.util.*;

public class Hand implements Comparable<Hand> {
    private Card[] cards;
    private int bid;

    public Hand(String cards, int bid) {
        this.cards = new Card[5];
        for (int i = 0; i < cards.length(); i++) {
            this.cards[i] = new Card(cards.charAt(i));
        }
        this.bid = bid;
    }

    public int getBid() {
        return this.bid;
    }

    public int getType() {
        Map<Character, Integer> counts = new HashMap<>();
        List<Character> names = Arrays.stream(this.cards).map(Card::getName).toList();
        for (char name : names) {
            if (!counts.containsKey(name)) counts.put(name, 0);
            counts.put(name, counts.get(name)+1);
        }

        int jokerCount = 0;
        if (counts.containsKey('J')) jokerCount = counts.get('J');

        List<Integer> keys = counts.keySet().stream().map(counts::get).sorted(Collections.reverseOrder()).toList();
        int totalKeys = keys.size();
        return switch (totalKeys) {
            case 1 -> 7;
            case 2 -> {
                if (keys.getFirst() == 4) yield jokerCount > 0 ? 7 : 6;
                if (jokerCount != 0) yield 7;
                yield 5;
            }
            case 3 -> {
                if (keys.getFirst() == 3) yield jokerCount > 0 ? 6 : 4;
                if (jokerCount == 2) yield 6;
                if (jokerCount == 1) yield 5;
                yield 3;
            }
            case 4 -> jokerCount > 0 ? 4 : 2;
            default -> jokerCount > 0 ? 2 : 1;
        };
    }

    @Override
    public int compareTo(Hand hand) {
        if (this.getType() != hand.getType()) return this.getType() - hand.getType();
        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i].getName() != hand.cards[i].getName()) return this.cards[i].compareTo(hand.cards[i]);
        }
        return 0;
    }

    @Override
    public String toString() {
        return Arrays.stream(cards).toList().toString();
    }
}
