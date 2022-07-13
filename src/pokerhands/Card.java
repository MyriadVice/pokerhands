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

    //we implement the Comparator interface for easy card comparision
    @Override
    public int compareTo(Card c) {
        //Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
        //the specified object
        if (this.value.ordinal() < c.value.ordinal()) return -1;
        if (this.value.ordinal() == c.value.ordinal()) return 0;
        return 1; //default
    }

    //no setter since we do not allow a cards properties to change after creation

    @Override
    public String toString() {
        return "(" + suit + "/" + value + ")";
    }
}