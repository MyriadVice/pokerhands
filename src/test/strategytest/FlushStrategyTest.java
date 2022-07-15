package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.CardValue;
import pokerhands.strategies.FlushStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.FlushStrategy}
 **/
public class FlushStrategyTest {
    private FlushStrategy strategy;

    @BeforeEach
    void setup() {
        TestHands.setup();
        strategy = new FlushStrategy();
    }

    @Test
    @DisplayName("Flush strategy is not permissible on an empty hand or null or a hand not containing a suit pair of 5")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(null));
        assertFalse(strategy.isPermissible(Collections.emptyList()));

        assertFalse(strategy.isPermissible(TestHands.SuitPairs.SUIT_PAIR_H));
        assertFalse(strategy.isPermissible(TestHands.SuitPairs.SUIT_PAIR_3_H));
        assertFalse(strategy.isPermissible(TestHands.SuitPairs.SUIT_PAIR_H_AND_S));
    }

    @Test
    @DisplayName("Flush strategy returns null if at least one hand is invalid")
    void nullEvaluationOnOneHandInvalid() {
        assertNull(strategy.evaluatePair(null, null));
        assertNull(strategy.evaluatePair(Collections.emptyList(), null));
        assertNull(strategy.evaluatePair(null, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), Collections.emptyList()));

        assertNull(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S, null));
        assertNull(strategy.evaluatePair(null, TestHands.SuitPairs.FULL_SUIT_PAIR_S));

        assertNull(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), TestHands.SuitPairs.FULL_SUIT_PAIR_S));
    }

    @Test
    @DisplayName("Flush strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertNull(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S, TestHands.SuitPairs.FULL_SUIT_PAIR_S));
    }

    @Test
    @DisplayName("Flush strategy returns the correct hand for flush hands with differing highest values")
    void correctEvaluationOnDifferentHighValues() {
        //hand1 with higher ranked high card
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.S, CardValue.SIX),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.S, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.THREE),
                new Card(CardSuit.S, CardValue.TWO)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertEquals(hand1, strategy.evaluatePair(hand1, hand2));
        assertEquals(hand1, strategy.evaluatePair(hand2, hand1));
    }

    @Test
    @DisplayName("Flush strategy returns the correct hand for flush hands with same highest values")
    void correctEvaluationOnSameHighValues() {
        //hand1 and hand2 with second most highest cards of equal values, ranking should be based on third highest card
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.FIVE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertEquals(hand2, strategy.evaluatePair(hand1, hand2));
        assertEquals(hand2, strategy.evaluatePair(hand2, hand1));
    }

    @Test
    @DisplayName("Flush strategy returns null for flush hands with same values but differing suits")
    void noEvaluationOnEqualValues() {
        //hand1 and hand2 with second most highest cards of equal values, ranking should be based on third highest card
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.S, CardValue.ACE),
                new Card(CardSuit.S, CardValue.KING),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.S, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.THREE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertNull(strategy.evaluatePair(hand1, hand2));
    }
}
