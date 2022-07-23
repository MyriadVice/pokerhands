package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.FullHouseStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.FullHouseStrategy}
 **/
public class FullHouseStrategyTest {
    private FullHouseStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new FullHouseStrategy();
    }

    @Test
    @DisplayName("Full house strategy is not permissible on a hand not containing a values pair of 3 and 2")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.NO_VALUE_PAIR));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_3_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN));
    }

    @Test
    @DisplayName("Full house strategy is permissible on a hand containing a values pair of 3 and 2")
    void isPermissibleOnHandWithPairOf3And2() {
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN));
    }

    @Test
    @DisplayName("Full house strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN, TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN).isPresent());
    }

    @Test
    @DisplayName("Full house strategy returns the correct hand for hands with differing highest value pairs of three")
    void evaluationOnDifferingHighPairs() {
        //hand1 with higher ranked triple pair and higher ranked double pair
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand1 with higher ranked triple pair and lower ranked double pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand1 with higher ranked triple pair and equally ranked double pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("Full house strategy returns correct hand for hands with higher pair of two than pair of three")
    void evaluationOnHigherTwoPair() {
        //hand1 with higher pair of two, hand2 with lower pair of two
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //both hands with higher pair of two
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand2 with higher pair of two
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.C, CardValue.TWO)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("Full house strategy returns null for hands with equal highest value pairs of three regardless of the" +
            " other card values")
    void noEvaluationOnEqualHighPairs() {
        //hands with equally ranked pair of three, hand1 with higher pair of 2
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertFalse(result.isPresent());

        //hand1 with higher ranked triple pair and lower ranked double pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertFalse(result.isPresent());

        //hands with equally ranked pair of three, hand1 with higher pair of 2 than pair of three
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertFalse(result.isPresent());

        //hands with equally ranked pair of three, both hands with higher pair of 2 than pair of three
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertFalse(result.isPresent());
    }
}
