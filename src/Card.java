/**
 * Card class representing a poker card with its suit {@link CardSuit} and value {@link CardValue}.
 */
public class Card {
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

    //no setter since we do not allow a cards properties to change after creation
}