package pokerhands;

/**
 * Card class representing a poker card with its suit {@link CardSuit} and value {@link CardValue}.
 */
public class Card implements Comparable<Card> {
    private CardSuit suit;
    private CardValue value;

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    @Override
    public int compareTo(Card c) {
        int valueCmp = this.value.compareTo(c.value);
        if (valueCmp == 0) return this.getSuit().compareTo(c.suit);
        return valueCmp;
    }

    @Override
    public String toString() {
        return "(" + suit.value + "/" + value.value + ")";
    }
}