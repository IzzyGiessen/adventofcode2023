package day7.classes;

import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card> {
    private char name;
    private Map<Character, Integer> rank;

    public Card(char name) {
        this.name = name;
        rank = new HashMap<>();
        rank.put('A', 13);
        rank.put('K', 12);
        rank.put('Q', 11);
        rank.put('T', 10);
        rank.put('J', 1);
    }

    public char getName() {
        return this.name;
    }

    @Override
    public int compareTo(Card card) {
        if (Character.isDigit(card.name)) {
            if (Character.isDigit(this.name)) {
                return this.name - card.name;
            }
            return rank.get(this.name) - Integer.parseInt(card.name+"");
        } else if (Character.isDigit(this.name)) {
            return Integer.parseInt(this.name+"") - rank.get(card.name);
        }
        return rank.get(this.name) - rank.get(card.name);
    }

    @Override
    public String toString() {
        return ""+name;
    }
}
