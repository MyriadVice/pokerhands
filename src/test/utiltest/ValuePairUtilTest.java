package test.utiltest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.CardValue;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;
import test.TestHands;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the getValuePair function in card utils {@link pokerhands.utils.CardUtils}
 **/
public class ValuePairUtilTest {

    @Test
    @DisplayName("Getting a value pair of a negative length from a hand should return no value")
    void noPairOfNegativeLength() {
        assertFalse(CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, -1).isPresent());
        assertFalse(CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, -1).isPresent());
    }

    @Test
    @DisplayName("Getting a value pair with zero length from a hand should return an empty list")
    void noPairOfZeroLength() {
        assertTrue(CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, 0).isPresent());
        assertTrue(CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, 0).isPresent());

        assertEquals(0, CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, 0).get().size());
        assertEquals(0, CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, 0).get().size());
    }

    @Test
    @DisplayName("Modifying the pair returned from a call to getValuePair should not change source hand")
    void pairModifiableWithoutModifyingSource() {
        HandView source = TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE;
        Optional<HandView> pair = CardUtils.getValuePair(source, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());

        //modifying the pair should not affect the source (deep copy)
        assertEquals(TestHands.HAND_SIZE, source.size());
        pair.get().remove(Arrays.asList(source.getCards().get(2), source.getCards().get(3)));
        assertEquals(TestHands.HAND_SIZE, source.size());
    }

    @Test
    @DisplayName("Getting a value pair from a hand containing exactly one value pair of the requested size should return the contained pair")
    void getPairFromHandWithAPair() {
        CardValue expectedValue = CardValue.ACE;
        Optional<HandView> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());

        //check for expected values
        assertEquals(expectedValue, pair.get().valueAt(0));
        assertEquals(expectedValue, pair.get().valueAt(1));
    }

    @Test
    @DisplayName("Trying to get a value pair from a hand with the requested pair size larger than the size of the hand should return no value")
    void noPairOfOverflowingLength() {
        Optional<HandView> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.HAND_SIZE + 1);
        assertFalse(pair.isPresent());
    }

    @Test
    @DisplayName("Getting a value pair from a hand not containing a value pair should return no value")
    void noPairFromHandWithoutPairs() {
        Optional<HandView> pair = CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, TestHands.DEFAULT_PAIR_SIZE);
        assertFalse(pair.isPresent());
    }

    @Test
    @DisplayName("Getting a value pair from a hand with a requested pair size of one should return a pair with exactly" +
            " one element that is the first element of the source hand")
    void pairOfLengthOneIsHandHighestCard() {
        HandView source = TestHands.ValuePairs.NO_VALUE_PAIR;
        Optional<HandView> pair = CardUtils.getValuePair(source, 1);

        assertTrue(pair.isPresent());

        assertEquals(1, pair.get().size());
        assertEquals(source.valueAt(0), pair.get().valueAt(0));
    }

    @Test
    @DisplayName("Getting a value pair from a hand containing more potential elements for the pair then requested " +
            "should return a pair of elements from the hand of the exact requested amount")
    void getPairFromHandWithLongerPair() {
        Optional<HandView> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_3_ACE, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());
    }

    @Test
    @DisplayName("Getting a value pair from a hand containing only shorter pairs should return no value")
    void noPairFromHandWithShorterPair() {
        Optional<HandView> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.DEFAULT_PAIR_SIZE + 1);
        assertFalse(pair.isPresent());
    }

    @Test
    @DisplayName("Getting a value pair from a hand with multiple pairs returns a pair of requested size from all possible pairs")
    void getHighestPairOnMultiplePairs() {
        CardValue expectedValue = CardValue.KING;
        Optional<HandView> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN, TestHands.DEFAULT_PAIR_SIZE);

        assertTrue(pair.isPresent());
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.get().size());

        assertEquals(expectedValue, pair.get().valueAt(0));
        assertEquals(expectedValue, pair.get().valueAt(1));
    }
}
