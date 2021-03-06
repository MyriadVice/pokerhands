package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.StraightFlushStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.StraightFlushStrategy}
 **/
public class StraightFlushStrategyTest {
    private StraightFlushStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new StraightFlushStrategy();
    }

    @Test
    @DisplayName("Straight flush strategy is not permissible on a hand not containing a sequence of 5 of same suit cards")
    void notPermissibleOnInvalidHand() {
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
    @DisplayName("Straight flush strategy is permissible on a containing a sequence of 5 of same suit cards")
    void isPermissibleOnSequenceOfSameSuit() {
        assertTrue(strategy.isPermissible(TestHands.Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT));
    }

    @Test
    @DisplayName("Straight flush strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ, TestHands.SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ).isPresent());
    }

    @Test
    @DisplayName("Straight flush strategy returns the correct hand for hands with differing highest values")
    void correctEvaluationOnDifferentHighValues() {
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
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("Straight flush strategy returns null for hands with equal highest values regardless of the other values")
    void noEvaluationOnEqualHighValues() {
        //hands with equal high cards
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.TWO)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand2.createView(), hand1.createView());
        assertFalse(result.isPresent());
    }
}
