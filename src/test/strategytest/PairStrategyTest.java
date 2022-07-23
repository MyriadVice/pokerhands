package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.PairStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.PairStrategy}
 **/
public class PairStrategyTest {
    private PairStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new PairStrategy();
    }

    @Test
    @DisplayName("Pair strategy is not permissible on a hand not containing a pair (of two cards)")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.NO_VALUE_PAIR));
    }

    @Test
    @DisplayName("Pair strategy is permissible on a hand or contain a pair (of two cards) or higher")
    void isPermissibleOnPairOf2() {
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_3_ACE));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_4_ACE));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_5_ACE));
    }

    @Test
    @DisplayName("Pair strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE).isPresent());
    }

    @Test
    @DisplayName("Pair strategy returns correct result for hands with differing pair values")
    void evaluationOnDifferingPairHands() {
        //differing pair, equal rest
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //differing pair, different rest
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SEVEN)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("Pair strategy returns correct result for hands with same pair values")
    void evaluationOnSamePairHands() {
        //same pair, differing rest from first position
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SEVEN)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand2, result.get().getHand());

        //same pair, differing rest from later position
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SEVEN)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand2, result.get().getHand());
    }
}
