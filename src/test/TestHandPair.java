package test;

import pokerhands.Hand;

/**
 * A pair of tow hands with their expected ranking under the hand ranking of {@link pokerhands.Main}. Used for testing purposes.
 */
public class TestHandPair {
    public final Hand hand1;
    public final Hand hand2;
    public final Hand expectedResult;

    TestHandPair(Hand hand1, Hand hand2, int expectedResult) {
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