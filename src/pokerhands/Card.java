package pokerhands;

/**
 * Card class representing a poker card with its {@link CardSuit} and {@link CardValue}.
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
    public boolean equals(Object o) {
        if (!(o instanceof Card)) return false;

        return this.compareTo((Card) o) == 0;
    }

    @Override
    public String toString() {
        return "(" + suit.value + "/" + value.value + ")";
    }
}