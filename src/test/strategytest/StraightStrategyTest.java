package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.StraightStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.StraightStrategy}
 **/
public class StraightStrategyTest {
    private StraightStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new StraightStrategy();
    }

    @Test
    @DisplayName("Straight strategy is not permissible on a hand not containing a sequence of 5")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(TestHands.Sequences.NO_SEQUENCE));

        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_FOUR));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE));
        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCE_THREE_TO_SIX));

        assertFalse(strategy.isPermissible(TestHands.Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN));
    }

    @Test
    @DisplayName("Straight strategy is permissible on a hand containing a sequence of 5 regardless of suit")
    void isPermissibleOnSequenceOf5() {
        assertTrue(strategy.isPermissible(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT));
        assertTrue(strategy.isPermissible(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_DIFF_SUIT));
    }

    @Test
    @DisplayName("Straight strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT).isPresent());
    }

    @Test
    @DisplayName("Straight strategy returns correct hand for two fully sequences hands")
    void evaluationOnEqualSequenceHands() {
        //hand1 with higher ranked high card
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.TWO)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertEquals(hand1, result.get().getHand());
        assertEquals(hand1, result.get().getHand());
    }
}
