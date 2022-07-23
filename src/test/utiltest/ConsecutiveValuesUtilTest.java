package test.utiltest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.CardValue;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;
import test.TestHands;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the getConsecutiveValues function in card utils {@link pokerhands.utils.CardUtils}
 **/
public class ConsecutiveValuesUtilTest {

    @Test
    @DisplayName("Getting a sequence of negative length from a hand should return no result")
    void noSequenceOfNegativeLength() {
        assertFalse(CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, -1).isPresent());
    }

    @Test
    @DisplayName("Getting a sequence of zero elements from a hand should return an empty list")
    void noSequenceOfZeroLength() {
        assertTrue(CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 0).isPresent());
        assertTrue(CardUtils.getConsecutiveValues(TestHands.Sequences.NO_SEQUENCE, 0).isPresent());


        assertEquals(Collections.emptyList(), CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 0).get().getCards());
        assertEquals(Collections.emptyList(), CardUtils.getConsecutiveValues(TestHands.Sequences.NO_SEQUENCE, 0).get().getCards());
    }

    @Test
    @DisplayName("Getting a sequence from a hand that overflows the hands size should return no value")
    void noSequenceOfOverflowingLength() {
        assertFalse(CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, TestHands.HAND_SIZE + 1).isPresent());
    }

    @Test
    @DisplayName("Getting a sequence from a hand containing a sequence of the requested length should return the contained sequence")
    void sequenceFromHandWithConsecutiveValues() {
        Optional<HandView> sequence = CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 3);

        assertTrue(sequence.isPresent());
        assertEquals(3, sequence.get().size());

        //descending as hand is sorted in descending order
        assertEquals(CardValue.FIVE, sequence.get().valueAt(0));
        assertEquals(CardValue.FOUR, sequence.get().valueAt(1));
        assertEquals(CardValue.THREE, sequence.get().valueAt(2));
    }

    @Test
    @DisplayName("Getting a sequence from a hand with a contained shorter sequence should return no result")
    void sequenceFromHandWithShorterSequence() {
        Optional<HandView> sequence = CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, 4);
        assertFalse(sequence.isPresent());
    }

    @Test
    @DisplayName("Getting a sequence from a hand with a contained longer sequence should return a sequence with the requested length")
    void sequenceFromHandWithLongerSequence() {
        Optional<HandView> sequence = CardUtils.getConsecutiveValues(TestHands.Sequences.SEQUENCE_THREE_TO_FIVE, TestHands.DEFAULT_SEQUENCE_SIZE);

        assertTrue(sequence.isPresent());
        assertEquals(TestHands.DEFAULT_SEQUENCE_SIZE, sequence.get().size());

        //descending as hand is sorted in descending order
        assertEquals(CardValue.FIVE, sequence.get().valueAt(0));
        assertEquals(CardValue.FOUR, sequence.get().valueAt(1));
    }
}
