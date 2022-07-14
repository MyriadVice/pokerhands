package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.CardValue;
import pokerhands.strategies.StraightStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.StraightStrategy}
 **/
public class StraightStrategyTest {
    private StraightStrategy strategy;

    @BeforeEach
    void setup() {
        TestHands.setup();
        strategy = new StraightStrategy();
    }

    @Test
    @DisplayName("Straight strategy is not permissible on an empty hand or null or a hand not containing a sequence of 5")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(null));
        assertFalse(strategy.isPermissible(Collections.emptyList()));

        assertFalse(strategy.isPermissible(TestHands.Sequences.NO_SEQUENCE));

        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_FOUR));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_SIX));

        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN));
    }

    @Test
    @DisplayName("Straight strategy returns null if at least one hand is invalid")
    void nullEvaluationOnOneHandInvalid() {
        assertNull(strategy.evaluatePair(null, null));
        assertNull(strategy.evaluatePair(Collections.emptyList(), null));
        assertNull(strategy.evaluatePair(null, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), Collections.emptyList()));

        assertNull(strategy.evaluatePair(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN, null));
        assertNull(strategy.evaluatePair(null, TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN));

        assertNull(strategy.evaluatePair(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN));
    }

    @Test
    @DisplayName("Straight strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertNull(strategy.evaluatePair(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN, TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN));
    }

    @Test
    @DisplayName("Straight strategy returns correct hand for two fully sequences hands")
    void evaluationOnEqualSequenceHands() {
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
}
