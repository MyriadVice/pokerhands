package pokerhands;

/**
 * Class to represent card suits which is one of clubs, diamonds, hearts, or spades (denoted C, D, H, and S)
 **/
public final class CardSuit implements Comparable<CardSuit> {
    public final static CardSuit C = new CardSuit('C'); //clubs
    public final static CardSuit D = new CardSuit('D'); //diamonds
    public final static CardSuit H = new CardSuit('H'); //hearts
    public final static CardSuit S = new CardSuit('S'); //spades

    public final char value;

    private CardSuit(char value) {
        this.value = value;
    }

    @Override
    public int compareTo(CardSuit o) {
        return Integer.compare(this.value, o.value);
    }
}
