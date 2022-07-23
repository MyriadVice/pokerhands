package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.TwoPairsStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.TwoPairsStrategy}
 **/
public class TwoPairStrategyTest {
    private TwoPairsStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new TwoPairsStrategy();
    }

    @Test
    @DisplayName("Two pair strategy is not permissible on a hand not containing two pairs")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.NO_VALUE_PAIR));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_3_ACE));
    }

    @Test
    @DisplayName("Two pair strategy is permissible on a hand containing two pairs of at least 2")
    void isPermissibleOnTwoPairs() {
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_5_ACE)); //two sets with same value
    }

    @Test
    @DisplayName("Two pair strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN, TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN).isPresent());
    }

    @Test
    @DisplayName("Two pair strategy returns correct result for hands with differing high pairs")
    void evaluationOnDifferingHighPairHands() {
        //hand 1 with higher high pair, rest equal
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand 1 with higher high pair, hand 2 with higher low pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.FOUR)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.FOUR)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("Two pair strategy returns correct result for hands with equal high pairs")
    void evaluationOnEqualHighPairHands() {
        //hand 2 with higher low pair, rest equal
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.SIX)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertEquals(hand2, result.get().getHand());
        assertEquals(hand2, result.get().getHand());

        //hand 2 with higher low pair, rest different
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.SIX)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertEquals(hand2, result.get().getHand());
        assertEquals(hand2, result.get().getHand());
    }

    @Test
    @DisplayName("Two pair strategy returns correct result for hands with equal high and low pairs")
    void evaluationOnEqualPairHands() {
        //same pairs, remaining value ranked lower than pairs
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.THREE)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //same pairs, remaining value ranked between pairs
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //same pairs, remaining value ranked higher than pairs
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.JACK)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }
}
