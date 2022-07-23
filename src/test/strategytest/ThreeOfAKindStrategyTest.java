package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.ThreeOfAKindStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.ThreeOfAKindStrategy}
 **/
public class ThreeOfAKindStrategyTest {
    private ThreeOfAKindStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new ThreeOfAKindStrategy();
    }

    @Test
    @DisplayName("Three of a kind strategy is not permissible if hand does not contain a pair of three")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.NO_VALUE_PAIR));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE));
    }

    @Test
    @DisplayName("Three of a kind strategy is permissible on a hand containing a pair of three or higher")
    void isPermissibleOnPairOf3() {
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_3_ACE));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_4_ACE));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_5_ACE));
    }

    @Test
    @DisplayName("High card strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_3_ACE, TestHands.ValuePairs.VALUE_PAIR_3_ACE).isPresent());
    }

    @Test
    @DisplayName("High card strategy returns correct hand for hands with differing pairs")
    void evaluationOnDifferingPairHands() {
        //hand1 with higher ranked pair, rest below rank of pair
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.TWO)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.TWO)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertEquals(hand1, result.get().getHand());
        assertEquals(hand1, result.get().getHand());

        //hand1 with higher ranked pair, rest above rank of pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertEquals(hand1, result.get().getHand());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("High card strategy returns null for hands with same pairs regardless of other values")
    void noEvaluationOnSamePairHands() {
        //hands with same value pair
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertFalse(result.isPresent());
    }
}
