package test.utiltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.utils.CardUtils;
import test.TestHands;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the getSuitPair function in card utils {@link pokerhands.utils.CardUtils}
 **/
public class SuitPairUtilTest {

    @BeforeEach
    void setup() {
        TestHands.setup();
    }

    @Test
    @DisplayName("Getting a suit pair of a negative length from a hand should return null")
    void noPairOfNegativeLength() {
        assertNull(CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, -1));
    }

    @Test
    @DisplayName("Getting a suit pair with zero length from a hand should return an empty list")
    void noPairOfZeroLength() {
        assertEquals(Collections.emptyList(), CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, 0));
    }

    @Test
    @DisplayName("Modifying the pair returned from a call to getSuitPair should not change source list")
    void pairModifiableWithoutModifyingSource() {
        List<Card> source = TestHands.SuitPairs.SUIT_PAIR_H;
        List<Card> pair = CardUtils.getSuitPair(source, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());

        //modifying the pair should not affect the source (deep copy)
        assertEquals(TestHands.HAND_SIZE, source.size());
        pair.remove(0);
        assertEquals(TestHands.HAND_SIZE, source.size());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand containing exactly one suit pair should return a list with the contained pair")
    void getPairFromHandWithAPair() {
        CardSuit expectedValue = CardSuit.H;
        List<Card> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());

        //check for expected suits
        assertEquals(expectedValue, pair.get(0).getSuit());
        assertEquals(expectedValue, pair.get(1).getSuit());
    }

    @Test
    @DisplayName("Trying to get a suit pair from a hand with the requested pair size larger than the size of the hand should return null")
    void noPairOfOverflowingLength() {
        List<Card> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, TestHands.HAND_SIZE + 1);
        assertNull(pair);
    }

    @Test
    @DisplayName("Getting a suit pair of length one from a hand should return a list with exactly one element")
    void getPairWithOneAmount() {
        List<Card> source = TestHands.SuitPairs.SUIT_PAIR_H;
        List<Card> pair = CardUtils.getSuitPair(source, 1);

        assertNotNull(pair);
        assertEquals(1, pair.size());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand containing more potential elements for the pair then requested " +
            "should return a list of elements from the hand of the exact requested amount")
    void getPairFromHandWithLongerPair() {
        List<Card> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_3_H, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand containing only shorter pairs should return null")
    void noPairFromHandWithShorterPair() {
        List<Card> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, TestHands.DEFAULT_PAIR_SIZE + 1);
        assertNull(pair);
    }

    @Test
    @DisplayName("Getting a suit pair from a hand with multiple pairs returns a valid pair of the requested length")
    void getHighestPairOfMultiplePairs() {
        List<Card> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H_AND_S, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());

        //check matching suit
        assertEquals(pair.get(0).getSuit(), pair.get(1).getSuit());
    }
}
