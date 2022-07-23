package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;
import pokerhands.strategies.FourOfAKindStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.FourOfAKindStrategy}
 **/
public class FourOfAKindStrategyTest {
    private FourOfAKindStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new FourOfAKindStrategy();
    }

    @Test
    @DisplayName("Four of a kind strategy is not permissible on a hand not containing a value pair of four")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.NO_VALUE_PAIR));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_3_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN));
    }

    @Test
    @DisplayName("Four of a kind strategy is permissible on a hand containing a value pair of four or higher")
    void isPermissibleOnPairOfFourOrHigher() {
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_4_ACE));
        assertTrue(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_5_ACE));
    }

    @Test
    @DisplayName("Four of a kind strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertFalse(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_4_ACE, TestHands.ValuePairs.VALUE_PAIR_4_ACE).isPresent());
    }

    @Test
    @DisplayName("Four of a kind strategy returns the correct hand for hands with differing pairs of four regardless of" +
            "the remaining cards value")
    void evaluationOnDifferingPairs() {
        //hand1 with higher ranked pair of four, both remaining cards ranked below the pair
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.TWO)
        ));

        Optional<HandView> result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand1 with higher ranked pair of four, hand1s remaining card ranked below its pair, hand2s remaining card
        //ranked above its pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.QUEEN)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand1 with higher ranked pair of four, hand1s remaining card ranked above its pair, hand2s remaining card
        //ranked below its pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.KING)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.TWO)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());

        //hand1 with higher ranked pair of four, both hands remaining cards values above their and the others pair of four
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        result = strategy.evaluatePair(hand1.createView(), hand2.createView());
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getHand());
    }

    @Test
    @DisplayName("Four of a kind strategy returns null for hands with equal pairs of four regardless of" +
            "the remaining cards value")
    void noEvaluationOnEqualPairs() {
        //both hands remaining cards ranked below the pair
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        Hand hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TWO)
        ));

        assertFalse(strategy.evaluatePair(hand1.createView(), hand2.createView()).isPresent());

        //hand1s remaining card ranked below its pair, hand2s remaining card
        //ranked above its pair
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TEN)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        assertFalse(strategy.evaluatePair(hand1.createView(), hand2.createView()).isPresent());

        //both hands remaining cards values above their pair of four
        hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.ACE)
        ));
        hand2 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.ACE)
        ));

        assertFalse(strategy.evaluatePair(hand1.createView(), hand2.createView()).isPresent());
    }
}
