package pokerhands;

/**
 * Class to represent card values which is one of
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A) with
 * 2 being the lowest value and ace being the highest
 **/
public final class CardValue implements Comparable<CardValue> {
    public static final CardValue TWO = new CardValue(2);
    public static final CardValue THREE = new CardValue(3);
    public static final CardValue FOUR = new CardValue(4);
    public static final CardValue FIVE = new CardValue(5);
    public static final CardValue SIX = new CardValue(6);
    public static final CardValue SEVEN = new CardValue(7);
    public static final CardValue EIGHT = new CardValue(8);
    public static final CardValue NINE = new CardValue(9);
    public static final CardValue TEN = new CardValue(10);
    public static final CardValue JACK = new CardValue(11);
    public static final CardValue QUEEN = new CardValue(12);
    public static final CardValue KING = new CardValue(13);
    public static final CardValue ACE = new CardValue(14);

    public final int value;

    private CardValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(CardValue o) {
        return Integer.compare(this.value, o.value);
    }
}
