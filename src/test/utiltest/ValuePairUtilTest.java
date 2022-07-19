package test.utiltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.CardValue;
import pokerhands.utils.CardUtils;
import test.TestHands;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the getValuePair function in card utils {@link pokerhands.utils.CardUtils}
 **/
public class ValuePairUtilTest {

    @BeforeEach
    void setup() {
        TestHands.setup();
    }

    @Test
    @DisplayName("Getting a value pair of a negative length from a hand should return null")
    void noPairOfNegativeLength() {
        assertNull(CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, -1));
        assertNull(CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, -1));
    }

    @Test
    @DisplayName("Getting a value pair with zero length from a hand should return an empty list")
    void noPairOfZeroLength() {
        assertEquals(Collections.emptyList(), CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, 0));
        assertEquals(Collections.emptyList(), CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, 0));
    }

    @Test
    @DisplayName("Modifying the pair returned from a call to getValuePair should not change source list")
    void pairModifiableWithoutModifyingSource() {
        List<Card> source = TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE;
        List<Card> pair = CardUtils.getValuePair(source, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());

        //todo: better test for inequality of references
        //modifying the pair should not affect the source (deep copy)
        assertEquals(TestHands.HAND_SIZE, source.size());
        pair.remove(0);
        assertEquals(TestHands.HAND_SIZE, source.size());
    }

    @Test
    @DisplayName("Getting a value pair from a hand containing exactly one value pair should return a list with the contained pair")
    void getPairFromHandWithAPair() {
        CardValue expectedValue = CardValue.ACE;
        List<Card> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());

        //check for expected values
        assertEquals(expectedValue, pair.get(0).getValue());
        assertEquals(expectedValue, pair.get(1).getValue());
    }

    @Test
    @DisplayName("Trying to get a value pair from a hand with the requested pair size larger than the size of the hand should return null")
    void noPairOfOverflowingLength() {
        List<Card> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.HAND_SIZE + 1);
        assertNull(pair);
    }

    @Test
    @DisplayName("Getting a value pair from a hand not containing a value pair should return null")
    void noPairFromHandWithoutPairs() {
        List<Card> pair = CardUtils.getValuePair(TestHands.ValuePairs.NO_VALUE_PAIR, TestHands.DEFAULT_PAIR_SIZE);
        assertNull(pair);
    }

    @Test
    @DisplayName("Getting a value pair from a hand with a requested pair size of one should return a list with exactly" +
            "one element that is the first element of the source")
    void pairOfLengthOneIsHandHighestCard() {
        List<Card> source = TestHands.ValuePairs.NO_VALUE_PAIR;
        List<Card> pair = CardUtils.getValuePair(source, 1);

        assertNotNull(pair);
        assertEquals(1, pair.size());
        assertEquals(pair.get(0), source.get(0));
    }

    @Test
    @DisplayName("Getting a value pair from a hand containing more potential elements for the pair then requested " +
            "should return a list of elements from the hand of the exact requested amount")
    void getPairFromHandWithLongerPair() {
        List<Card> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_3_ACE, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());
    }

    @Test
    @DisplayName("Getting a value pair from a hand containing only shorter pairs should return null")
    void noPairFromHandWithShorterPair() {
        List<Card> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_ACE, TestHands.DEFAULT_PAIR_SIZE + 1);
        assertNull(pair);
    }

    @Test
    @DisplayName("Getting a value pair from hand with multiple pairs returns the pair with the highest value card from all possible pairs")
    void getHighestPairOnMultiplePairs() {
        CardValue expectedValue = CardValue.KING;
        List<Card> pair = CardUtils.getValuePair(TestHands.ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN, TestHands.DEFAULT_PAIR_SIZE);

        assertNotNull(pair);
        assertEquals(TestHands.DEFAULT_PAIR_SIZE, pair.size());

        assertEquals(expectedValue, pair.get(0).getValue());
        assertEquals(expectedValue, pair.get(1).getValue());
    }
}
