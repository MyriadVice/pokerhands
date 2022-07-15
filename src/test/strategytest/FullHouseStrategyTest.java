package test.strategytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.CardValue;
import pokerhands.strategies.FullHouseStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.FullHouseStrategy}
 **/
public class FullHouseStrategyTest {
    private FullHouseStrategy strategy;

    @BeforeEach
    void setup() {
        TestHands.setup();
        strategy = new FullHouseStrategy();
    }

    @Test
    @DisplayName("Full house strategy is not permissible on an empty hand or null or on a hand not containing a values pair of 3 and 2")
    void notPermissibleOnInvalidHand() {
        assertFalse(strategy.isPermissible(null));
        assertFalse(strategy.isPermissible(Collections.emptyList()));

        assertFalse(strategy.isPermissible(TestHands.ValuePairs.NO_VALUE_PAIR));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_3_ACE));
        assertFalse(strategy.isPermissible(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN));
    }

    @Test
    @DisplayName("Full house strategy returns null if at least one hand is invalid")
    void nullEvaluationOnOneHandInvalid() {
        assertNull(strategy.evaluatePair(null, null));
        assertNull(strategy.evaluatePair(Collections.emptyList(), null));
        assertNull(strategy.evaluatePair(null, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), Collections.emptyList()));

        assertNull(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN, null));
        assertNull(strategy.evaluatePair(null, TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN));

        assertNull(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN, Collections.emptyList()));
        assertNull(strategy.evaluatePair(Collections.emptyList(), TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN));
    }

    @Test
    @DisplayName("Full house strategy returns null if both hands are equal")
    void nullEvaluationOnEqualHands() {
        assertNull(strategy.evaluatePair(TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN, TestHands.ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN));
    }

    @Test
    @DisplayName("Full house strategy returns the correct hand for hands with differing highest value pairs of three")
    void evaluationOnDifferingHighPairs() {
        //hand1 with higher ranked triple pair and higher ranked double pair
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertEquals(hand1, strategy.evaluatePair(hand1, hand2));
        assertEquals(hand1, strategy.evaluatePair(hand2, hand1));

        //hand1 with higher ranked triple pair and lower ranked double pair
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertEquals(hand1, strategy.evaluatePair(hand1, hand2));
        assertEquals(hand1, strategy.evaluatePair(hand2, hand1));

        //hand1 with higher ranked triple pair and equally ranked double pair
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
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
    @DisplayName("Full house strategy returns correct hand for hands with higher pair of two than pair of three")
    void evaluationOnHigherTwoPair() {
        //hand1 with higher pair of two, hand2 with lower pair of two
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertEquals(hand1, strategy.evaluatePair(hand1, hand2));
        assertEquals(hand1, strategy.evaluatePair(hand2, hand1));

        //both hands with higher pair of two
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertEquals(hand1, strategy.evaluatePair(hand1, hand2));
        assertEquals(hand1, strategy.evaluatePair(hand2, hand1));

        //hand2 with higher pair of two
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.C, CardValue.TWO)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
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
    @DisplayName("Full house strategy returns null for hands with equal highest value pairs of three regardless of the" +
            " other card values")
    void noEvaluationOnEqualHighPairs() {
        //hands with equally ranked pair of three, hand1 with higher pair of 2
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertNull(strategy.evaluatePair(hand1, hand2));
        assertNull(strategy.evaluatePair(hand2, hand1));

        //hand1 with higher ranked triple pair and lower ranked double pair
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertNull(strategy.evaluatePair(hand1, hand2));
        assertNull(strategy.evaluatePair(hand2, hand1));

        //hands with equally ranked pair of three, hand1 with higher pair of 2 than pair of three
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.THREE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        assertNull(strategy.evaluatePair(hand1, hand2));
        assertNull(strategy.evaluatePair(hand2, hand1));

        //hands with equally ranked pair of three, both hands with higher pair of 2 than pair of three
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
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
