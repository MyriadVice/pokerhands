package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.HighCardStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.HighCardStrategy}
 **/
public class HighCardStrategyTest {
    private HighCardStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new HighCardStrategy();
    }

    @Test
    @DisplayName("High card strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.ValuePairs.NO_VALUE_PAIR, TestHands.ValuePairs.NO_VALUE_PAIR).isPresent());
    }

    @Test
    @DisplayName("High card strategy returns the correct hand for hands with differing highest values")
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
    @DisplayName("High card strategy returns the correct hand for hands with same highest values")
    void correctEvaluationOnSameHighValues() {
        //hand1 and hand2 with second most highest cards of equal values, ranking should be based on third highest card
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.FIVE)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand2, result.get().getHand());
    }
}
