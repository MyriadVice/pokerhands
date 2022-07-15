package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.CardValue;
import pokerhands.strategies.StraightFlushStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.StraightFlushStrategy}
 **/
public class StraightFlushStrategyTest {
    private StraightFlushStrategy strategy;

    @BeforeEach
    void setup() {
        TestHands.setup();
        strategy = new StraightFlushStrategy();
    }

    @Test
    @DisplayName("Straight flush strategy is not permissible on an empty hand or null or on a hand not containing a sequence of 5 of same suit cards")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(null));
        assertFalse(strategy.isPermissible(Collections.emptyList()));

        assertFalse(strategy.isPermissible(TestHands.Sequences.NO_SEQUENCE));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_FOUR));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_SIX));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN));
        assertFalse(strategy.isPermissible(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_DIFF_SUIT));

        assertFalse(strategy.isPermissible(TestHands.SuitPairs.SUIT_PAIR_H));
        assertFalse(strategy.isPermissible(TestHands.SuitPairs.SUIT_PAIR_3_H));
        assertFalse(strategy.isPermissible(TestHands.SuitPairs.SUIT_PAIR_H_AND_S));
        assertFalse(strategy.isPermissible(TestHands.SuitPairs.FULL_SUIT_PAIR_S_NO_SEQ));
    }

    @Test
    @DisplayName("Straight flush strategy returns null if at least one hand is invalid")
    void nullEvaluationOnOneHandInvalid() {
        assertNull(strategy.evaluatePair(null, null));
        assertNull(strategy.evaluatePair(Collections.emptyList(), null));
        assertNull(strategy.evaluatePair(null, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), Collections.emptyList()));

        assertNull(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ, null));
        assertNull(strategy.evaluatePair(null, TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ));

        assertNull(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ));
    }

    @Test
    @DisplayName("Straight flush strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertNull(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ, TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ));
    }

    @Test
    @DisplayName("Straight flush strategy returns the correct hand for hands with differing highest values")
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
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.TWO)
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
    @DisplayName("Straight flush strategy returns null for hands with equal highest values regardless of the other values")
    void noEvaluationOnEqualHighValues() {
        //hands with equal high cards
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.TWO)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertNull(strategy.evaluatePair(hand1, hand2));
        assertNull(strategy.evaluatePair(hand2, hand1));
    }
}
