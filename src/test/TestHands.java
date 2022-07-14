package test;

import pokerhands.Card;
import pokerhands.CardSuit;
import pokerhands.CardValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class containing utility hands for testing purposes
 */
public class TestHands {
    public static final int HAND_SIZE = 5;
    public static final int DEFAULT_PAIR_SIZE = 2;
    public static final int DEFAULT_SEQUENCE_SIZE = 2;

    public static List<Card> EMPTY_HAND = Collections.emptyList();

    public static class ValuePairs {
        //value pairs
        public static List<Card> NO_VALUE_PAIR = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE)
        );
        public static List<Card> VALUE_PAIR_OF_2_ACE = Arrays.asList(
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.ACE)
        );
        public static List<Card> VALUE_PAIR_OF_2_KING_2_QUEEN = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.QUEEN)
        );
        public static List<Card> VALUE_PAIR_3_ACE = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.NINE)
        );
        public static List<Card> VALUE_PAIR_5_ACE = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE)
        );
    }

    //sequences
    public static class Sequences {
        public static List<Card> NO_SEQUENCE = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.NINE)
        );
        public static List<Card> SEQUENCE_THREE_TO_FOUR = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.NINE)
        );
        public static List<Card> SEQUENCE_THREE_TO_FIVE = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.NINE)
        );
        public static List<Card> SEQUENCE_THREE_TO_SIX = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.SIX)
        );
        public static List<Card> FULL_SEQUENCE_THREE_TO_SEVEN = Arrays.asList(
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SEVEN)
        );
        public static List<Card> SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN = Arrays.asList(
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.TEN)
        );
    }

    //suit pairs
    public static class SuitPairs {
        public static List<Card> SUIT_PAIR_H = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.H, CardValue.ACE),
                new Card(CardSuit.H, CardValue.ACE),
                new Card(CardSuit.S, CardValue.ACE),
                new Card(CardSuit.D, CardValue.ACE)
        );
        public static List<Card> SUIT_PAIR_H_AND_S = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.H, CardValue.KING),
                new Card(CardSuit.H, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.JACK),
                new Card(CardSuit.S, CardValue.TEN)
        );
        public static List<Card> SUIT_PAIR_3_H = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.H, CardValue.KING),
                new Card(CardSuit.H, CardValue.KING),
                new Card(CardSuit.H, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.QUEEN)
        );
        public static List<Card> FULL_SUIT_PAIR_S = Arrays.asList(
                new Card(CardSuit.S, CardValue.ACE),
                new Card(CardSuit.S, CardValue.KING),
                new Card(CardSuit.S, CardValue.KING),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.QUEEN)
        );
    }

    public static void setup() {
        //perhaps this could be done better with refactoring ...

        //sort all hands (except for empty hand which is implicitly sorted)
        Collections.sort(ValuePairs.NO_VALUE_PAIR);
        Collections.reverse(ValuePairs.NO_VALUE_PAIR);

        Collections.sort(ValuePairs.VALUE_PAIR_OF_2_ACE);
        Collections.reverse(ValuePairs.VALUE_PAIR_OF_2_ACE);

        Collections.sort(ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN);
        Collections.reverse(ValuePairs.VALUE_PAIR_OF_2_KING_2_QUEEN);

        Collections.sort(ValuePairs.VALUE_PAIR_3_ACE);
        Collections.reverse(ValuePairs.VALUE_PAIR_3_ACE);

        Collections.sort(ValuePairs.VALUE_PAIR_5_ACE);
        Collections.reverse(ValuePairs.VALUE_PAIR_5_ACE);

        Collections.sort(Sequences.NO_SEQUENCE);
        Collections.reverse(Sequences.NO_SEQUENCE);

        Collections.sort(Sequences.SEQUENCE_THREE_TO_FIVE);
        Collections.reverse(Sequences.SEQUENCE_THREE_TO_FIVE);

        Collections.sort(Sequences.SEQUENCE_THREE_TO_SIX);
        Collections.reverse(Sequences.SEQUENCE_THREE_TO_SIX);

        Collections.sort(Sequences.SEQUENCE_THREE_TO_FOUR);
        Collections.reverse(Sequences.SEQUENCE_THREE_TO_FOUR);

        Collections.sort(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN);
        Collections.reverse(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN);

        Collections.sort(Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN);
        Collections.reverse(Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN);

        Collections.sort(SuitPairs.SUIT_PAIR_H);
        Collections.reverse(SuitPairs.SUIT_PAIR_H);

        Collections.sort(SuitPairs.SUIT_PAIR_H_AND_S);
        Collections.reverse(SuitPairs.SUIT_PAIR_H_AND_S);

        Collections.sort(SuitPairs.SUIT_PAIR_3_H);
        Collections.reverse(SuitPairs.SUIT_PAIR_3_H);

        Collections.sort(SuitPairs.FULL_SUIT_PAIR_S);
        Collections.reverse(SuitPairs.FULL_SUIT_PAIR_S);
    }
}
