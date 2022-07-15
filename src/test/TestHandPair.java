package test;

import pokerhands.Card;

import java.util.List;

/**
 * A pair of tow hands with their expected ranking under the hand ranking of {@link pokerhands.Main}. Used for testing purposes.
 */
public class TestHandPair {
    public final List<Card> hand1;
    public final List<Card> hand2;
    public final List<Card> expectedResult;

    TestHandPair(List<Card> hand1, List<Card> hand2, int expectedResult) {
        this.hand1 = hand1;
        this.hand2 = hand2;

        switch (expectedResult) {
            case 0:
                this.expectedResult = null;
                break;
            case 2:
                this.expectedResult = hand2;
                break;
            default:
                this.expectedResult = hand1;
        }
    }
}