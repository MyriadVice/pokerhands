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
 * Test class for the getConsecutiveValues function in card utils {@link pokerhands.utils.CardUtils}
 **/
public class ConsecutiveValuesUtilTest {

    @BeforeEach
    void setup() {
        TestHands.setup();
    }

    @Test
    @DisplayName("Getting a sequence from an empty hand should return null")
    void noSequenceFromEmptyHand() {
        assertNull(CardUtils.getConsecutiveValues(TestHands.EMPTY_HAND, TestHands.DEFAULT_SEQUENCE_SIZE));
    }

    @Test
    @DisplayName("Getting a sequence from null should return null")
    void noSequenceFromNullHand() {
        assertNull(CardUtils.getConsecutiveValues(null, TestHands.DEFAULT_SEQUENCE_SIZE));
    }

    @Test
    @DisplayName("Getting a sequence of negative length from a hand should return null")
    void noSequenceOfNegativeLength() {
        assertNull(CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, -1));
    }

    @Test
    @DisplayName("Getting a sequence of zero elements from a hand should return an empty list")
    void noSequenceOfZeroLength() {
        assertEquals(Collections.emptyList(), CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 0));
        assertEquals(Collections.emptyList(), CardUtils.getConsecutiveValues(TestHands.Sequences.NO_SEQUENCE, 0));
    }

    @Test
    @DisplayName("Getting a sequence from a hand that overflows the hands capacity should return null")
    void noSequenceOfOverflowingLength() {
        assertNull(CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, TestHands.HAND_SIZE + 1));
    }

    @Test
    @DisplayName("Getting a sequence from a hand containing a sequence of the requested length should return the contained sequence")
    void sequenceFromHandWithConsecutiveValues() {
        List<Card> sequence = CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 3);

        assertNotNull(sequence);
        assertEquals(3, sequence.size());

        //descending as hand is sorted in descending order
        assertEquals(CardValue.FIVE, sequence.get(0).getValue());
        assertEquals(CardValue.FOUR, sequence.get(1).getValue());
        assertEquals(CardValue.THREE, sequence.get(2).getValue());
    }

    @Test
    @DisplayName("Getting a sequence from a hand with a contained shorter sequence should return null")
    void sequenceFromHandWithShorterSequence() {
        List<Card> sequence = CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 4);
        assertNull(sequence);
    }

    @Test
    @DisplayName("Getting a sequence from a hand with a contained longer sequence should return a the starting part of" +
            "the sequence with the requested length")
    void sequenceFromHandWithLongerSequence() {
        List<Card> sequence = CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, TestHands.DEFAULT_SEQUENCE_SIZE);

        assertNotNull(sequence);
        assertEquals(TestHands.DEFAULT_SEQUENCE_SIZE, sequence.size());

        //descending as hand is sorted in descending order
        assertEquals(CardValue.FIVE, sequence.get(0).getValue());
        assertEquals(CardValue.FOUR, sequence.get(1).getValue());
    }
}
