package test.strategytest;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.CardValue;
import pokerhands.strategies.PairStrategy;
import pokerhands.strategies.PokerHandStrategy;
import test.TestHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link pokerhands.strategies.PairStrategy}
 **/
public class PairStrategyTest {
    private PairStrategy strategy;

    @BeforeEach
    void setup() {
        TestHands.setup();
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
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        Optional<Pair<List<Card>, PokerHandStrategy>> result = strategy.evaluatePair(hand1, hand2);
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getKey());

        //differing pair, different rest
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SEVEN)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        result = strategy.evaluatePair(hand1, hand2);
        assertTrue(result.isPresent());
        assertEquals(hand1, result.get().getKey());
    }

    @Test
    @DisplayName("Pair strategy returns correct result for hands with same pair values")
    void evaluationOnSamePairHands() {
        //same pair, differing rest from first position
        List<Card> hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );
        List<Card> hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SEVEN)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        Optional<Pair<List<Card>, PokerHandStrategy>> result = strategy.evaluatePair(hand1, hand2);
        assertTrue(result.isPresent());
        assertEquals(hand2, result.get().getKey());

        //same pair, differing rest from later position
        hand1 = Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.THREE)
        );
        hand2 = Arrays.asList(
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SEVEN)
        );

        //sort to keep invariants
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);

        result = strategy.evaluatePair(hand1, hand2);
        assertTrue(result.isPresent());
        assertEquals(hand2, result.get().getKey());
    }
}
