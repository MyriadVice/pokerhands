package test.utiltest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.CardSuit;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;
import test.TestHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the getSuitPair function in card utils {@link pokerhands.utils.CardUtils}
 **/
public class SuitPairUtilTest {

    @Test
    @DisplayName("Getting a suit pair of a negative length from a hand should return no value")
    void noPairOfNegativeLength() {
        assertFalse(CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, -1).isPresent());
    }

    @Test
    @DisplayName("Getting a suit pair with zero length from a hand should return an empty list")
    void noPairOfZeroLength() {
        assertTrue(CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, 0).isPresent());

        assertEquals(Collections.emptyList(), CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, 0).get().getCards());
    }

    @Test
    @DisplayName("Modifying the pair returned from a call to getSuitPair should not change its source hand")
    void pairModifiableWithoutModifyingSource() {
        HandView source = TestHands.SuitPairs.SUIT_PAIR_H;
        Optional<HandView> pair = CardUtils.getSuitPair(source, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());

        //modifying the pair should not affect the source (deep copy)
        assertEquals(TestHands.HAND_SIZE, source.size());
        pair.get().remove(Arrays.asList(source.getHand().getCards().get(2), (source.getHand().getCards().get(3))));
        assertEquals(TestHands.HAND_SIZE, source.size());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand containing exactly one suit pair should return the contained pair")
    void getPairFromHandWithAPair() {
        CardSuit expectedValue = CardSuit.H;
        Optional<HandView> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());

        //check for expected suits
        assertEquals(expectedValue, pair.get().suitAt(0));
        assertEquals(expectedValue, pair.get().suitAt(1));
    }

    @Test
    @DisplayName("Trying to get a suit pair from a hand with the requested pair size larger than the size of the hand should return no value")
    void noPairOfOverflowingLength() {
        Optional<HandView> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, TestHands.HAND_SIZE + 1);
        assertFalse(pair.isPresent());
    }

    @Test
    @DisplayName("Getting a suit pair of length one from a hand should return a list with exactly one element")
    void getPairWithOneAmount() {
        HandView source = TestHands.SuitPairs.SUIT_PAIR_H;
        Optional<HandView> pair = CardUtils.getSuitPair(source, 1);

        assertTrue(pair.isPresent());
        assertEquals(1, pair.get().size());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand containing more potential elements for the pair then requested " +
            "should return a pair of elements from the hand of the exact requested amount")
    void getPairFromHandWithLongerPair() {
        Optional<HandView> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_3_H, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand containing only shorter pairs should return no value")
    void noPairFromHandWithShorterPair() {
        Optional<HandView> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H, TestHands.DEFAULT_PAIR_SIZE + 1);
        assertFalse(pair.isPresent());
    }

    @Test
    @DisplayName("Getting a suit pair from a hand with multiple pairs returns a valid pair of the requested length")
    void getHighestPairOfMultiplePairs() {
        Optional<HandView> pair = CardUtils.getSuitPair(TestHands.SuitPairs.SUIT_PAIR_H_AND_S, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());

        //check matching suit
        assertEquals(pair.get().suitAt(0), pair.get().suitAt(1));
    }
}
