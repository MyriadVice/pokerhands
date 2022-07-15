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

    //test hand pairs
    public static class TestHandPairs {
        //special cases
        public static class SpecialCases {
            public static TestHandPair EMPTY_HANDS = new TestHandPair(EMPTY_HAND, EMPTY_HAND, 0);
            public static TestHandPair NULL_HANDS = new TestHandPair(null, null, 0);

            public static TestHandPair NULL_CASE_0 = new TestHandPair(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, EMPTY_HAND, 0);
            public static TestHandPair NULL_CASE_1 = new TestHandPair(EMPTY_HAND, Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, 0);

            public static TestHandPair NULL_CASE_2 = new TestHandPair(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, null, 0);
            public static TestHandPair NULL_CASE_3 = new TestHandPair(null, Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, 0);

            public static TestHandPair NULL_CASE_4 = new TestHandPair(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT, 0);

            //at bottom of class fo avoid illegal forward referencing
            public static List<TestHandPair> ALL = Arrays.asList(
                    EMPTY_HANDS,
                    NULL_HANDS,
                    NULL_CASE_0,
                    NULL_CASE_1,
                    NULL_CASE_2,
                    NULL_CASE_3,
                    NULL_CASE_4
            );
        }

        //high card
        public static class HighCardCases {
            //same values
            public static TestHandPair HIGH_CARD_0 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 0);

            //first value higher (hand1)
            public static TestHandPair HIGH_CARD_1 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX),
                    new Card(CardSuit.C, CardValue.FOUR)
            ), 1);

            //mid value higher (hand1)
            public static TestHandPair HIGH_CARD_2 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX),
                    new Card(CardSuit.S, CardValue.FOUR)

            ), 1);

            //last value higher (hand1)
            public static TestHandPair HIGH_CARD_3 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.TWO)
            ), 1);

            //first value higher (hand2)
            public static TestHandPair HIGH_CARD_4 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX),
                    new Card(CardSuit.C, CardValue.FOUR)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 2);

            //mid value higher (hand2)
            public static TestHandPair HIGH_CARD_5 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.SIX),
                    new Card(CardSuit.C, CardValue.FOUR)

            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 2);

            //last value higher (hand2)
            public static TestHandPair HIGH_CARD_6 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.TWO)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 2);

            public static List<TestHandPair> ALL = Arrays.asList(
                    HIGH_CARD_0,
                    HIGH_CARD_1,
                    HIGH_CARD_2,
                    HIGH_CARD_3,
                    HIGH_CARD_4,
                    HIGH_CARD_5,
                    HIGH_CARD_6
            );
        }

        //pair
        public static class PairCases {
            //one pair
            public static TestHandPair PAIR_0 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.ACE),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 2);

            public static TestHandPair PAIR_1 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.ACE),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 1);

            //same values
            public static TestHandPair PAIR_2 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.ACE),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.ACE),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 0);

            //one higher (hand1, high pair)
            public static TestHandPair PAIR_3 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.ACE),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.QUEEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 1);

            //one higher (hand1, mid pair)
            public static TestHandPair PAIR_4 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.QUEEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.EIGHT),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 1);

            //one higher (hand1, low pair)
            public static TestHandPair PAIR_5 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.EIGHT)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.SIX),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 1);

            //one higher (hand2, high pair)
            public static TestHandPair PAIR_6 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.ACE),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 2);

            //one higher (hand1, mid pair)
            public static TestHandPair PAIR_7 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.TEN),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.QUEEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.SIX)
            ), 2);

            //one higher (hand1, low pair)
            public static TestHandPair PAIR_8 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.SIX),
                    new Card(CardSuit.C, CardValue.SIX)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.ACE),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.EIGHT)
            ), 2);

            //equal pairs, first leftover card differs (hand1)
            public static TestHandPair PAIR_9 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.FIVE)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.NINE),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.FIVE)
            ), 1);

            //equal pairs, second leftover card differs (hand1)
            public static TestHandPair PAIR_10 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.FOUR)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.FOUR)
            ), 1);

            //equal pairs, last leftover card differs (hand1)
            public static TestHandPair PAIR_11 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.FIVE)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.EIGHT),
                    new Card(CardSuit.C, CardValue.FOUR)
            ), 1);

            //equal pairs, first leftover card differs (hand2)
            public static TestHandPair PAIR_12 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.NINE),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.FIVE)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.TEN),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.FIVE)
            ), 2);

            //equal pairs, second leftover card differs (hand2)
            public static TestHandPair PAIR_13 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.NINE),
                    new Card(CardSuit.H, CardValue.FIVE),
                    new Card(CardSuit.C, CardValue.THREE)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.NINE),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.THREE)
            ), 2);

            //equal pairs, last leftover card differs (hand2)
            public static TestHandPair PAIR_14 = new TestHandPair(Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.NINE),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.THREE)
            ), Arrays.asList(
                    new Card(CardSuit.C, CardValue.QUEEN),
                    new Card(CardSuit.D, CardValue.QUEEN),
                    new Card(CardSuit.S, CardValue.NINE),
                    new Card(CardSuit.H, CardValue.SEVEN),
                    new Card(CardSuit.C, CardValue.FIVE)
            ), 2);

            public static List<TestHandPair> ALL = Arrays.asList(
                    PAIR_0,
                    PAIR_1,
                    PAIR_2,
                    PAIR_3,
                    PAIR_4,
                    PAIR_5,
                    PAIR_6,
                    PAIR_7,
                    PAIR_8,
                    PAIR_9,
                    PAIR_10,
                    PAIR_11,
                    PAIR_12,
                    PAIR_13,
                    PAIR_14
            );
        }

        //... todo: rest of equally ranked hand cases

        //all test combinations of two hands of different ranks, ordered by the rank of the first hand
        public static class DifferingCases {
            //high card
            public static class HighCardCases {
                public static TestHandPair CASE_0 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.HIGH_CARD, 0);
                public static TestHandPair CASE_1 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.PAIR, 2);
                public static TestHandPair CASE_2 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.TWO_PAIRS, 2);
                public static TestHandPair CASE_3 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.THREE_OF_A_KIND, 2);
                public static TestHandPair CASE_4 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.STRAIGHT, 2);
                public static TestHandPair CASE_5 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.FLUSH, 2);
                public static TestHandPair CASE_6 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.FULL_HOUSE, 2);
                public static TestHandPair CASE_7 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_8 = new TestHandPair(SpecialHands.HIGH_CARD, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_0,
                        CASE_1,
                        CASE_2,
                        CASE_3,
                        CASE_4,
                        CASE_5,
                        CASE_6,
                        CASE_7,
                        CASE_8
                );
            }

            //pair
            public static class PairCases {
                public static TestHandPair CASE_9 = new TestHandPair(SpecialHands.PAIR, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_10 = new TestHandPair(SpecialHands.PAIR, SpecialHands.PAIR, 0);
                public static TestHandPair CASE_11 = new TestHandPair(SpecialHands.PAIR, SpecialHands.TWO_PAIRS, 2);
                public static TestHandPair CASE_12 = new TestHandPair(SpecialHands.PAIR, SpecialHands.THREE_OF_A_KIND, 2);
                public static TestHandPair CASE_13 = new TestHandPair(SpecialHands.PAIR, SpecialHands.STRAIGHT, 2);
                public static TestHandPair CASE_14 = new TestHandPair(SpecialHands.PAIR, SpecialHands.FLUSH, 2);
                public static TestHandPair CASE_15 = new TestHandPair(SpecialHands.PAIR, SpecialHands.FULL_HOUSE, 2);
                public static TestHandPair CASE_16 = new TestHandPair(SpecialHands.PAIR, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_17 = new TestHandPair(SpecialHands.PAIR, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_9,
                        CASE_10,
                        CASE_11,
                        CASE_12,
                        CASE_13,
                        CASE_14,
                        CASE_15,
                        CASE_16,
                        CASE_17
                );
            }

            //two pairs
            public static class TwoPairCases {
                public static TestHandPair CASE_18 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_19 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_20 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.TWO_PAIRS, 0);
                public static TestHandPair CASE_21 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.THREE_OF_A_KIND, 2);
                public static TestHandPair CASE_22 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.STRAIGHT, 2);
                public static TestHandPair CASE_23 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.FLUSH, 2);
                public static TestHandPair CASE_24 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.FULL_HOUSE, 2);
                public static TestHandPair CASE_25 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_26 = new TestHandPair(SpecialHands.TWO_PAIRS, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_18,
                        CASE_19,
                        CASE_20,
                        CASE_21,
                        CASE_22,
                        CASE_23,
                        CASE_24,
                        CASE_25,
                        CASE_26
                );
            }

            //three of kind
            public static class ThreeOfAKindCases {
                public static TestHandPair CASE_27 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_28 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_29 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.TWO_PAIRS, 1);
                public static TestHandPair CASE_30 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.THREE_OF_A_KIND, 0);
                public static TestHandPair CASE_31 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.STRAIGHT, 2);
                public static TestHandPair CASE_32 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.FLUSH, 2);
                public static TestHandPair CASE_33 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.FULL_HOUSE, 2);
                public static TestHandPair CASE_34 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_35 = new TestHandPair(SpecialHands.THREE_OF_A_KIND, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_27,
                        CASE_28,
                        CASE_29,
                        CASE_30,
                        CASE_31,
                        CASE_32,
                        CASE_33,
                        CASE_34,
                        CASE_35
                );
            }

            //straight
            public static class StraightCases {
                public static TestHandPair CASE_36 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_37 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_38 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.TWO_PAIRS, 1);
                public static TestHandPair CASE_39 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.THREE_OF_A_KIND, 1);
                public static TestHandPair CASE_40 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.STRAIGHT, 0);
                public static TestHandPair CASE_41 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.FLUSH, 2);
                public static TestHandPair CASE_42 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.FULL_HOUSE, 2);
                public static TestHandPair CASE_43 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_44 = new TestHandPair(SpecialHands.STRAIGHT, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_36,
                        CASE_37,
                        CASE_38,
                        CASE_39,
                        CASE_40,
                        CASE_41,
                        CASE_42,
                        CASE_43,
                        CASE_44
                );
            }

            //flush
            public static class FlushCases {
                public static TestHandPair CASE_45 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_46 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_47 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.TWO_PAIRS, 1);
                public static TestHandPair CASE_48 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.THREE_OF_A_KIND, 1);
                public static TestHandPair CASE_49 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.STRAIGHT, 1);
                public static TestHandPair CASE_50 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.FLUSH, 0);
                public static TestHandPair CASE_51 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.FULL_HOUSE, 2);
                public static TestHandPair CASE_52 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_53 = new TestHandPair(SpecialHands.FLUSH, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_45,
                        CASE_46,
                        CASE_47,
                        CASE_48,
                        CASE_49,
                        CASE_50,
                        CASE_51,
                        CASE_52,
                        CASE_53
                );
            }

            //full house
            public static class FullHouseCases {
                public static TestHandPair CASE_54 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_55 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_56 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.TWO_PAIRS, 1);
                public static TestHandPair CASE_57 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.THREE_OF_A_KIND, 1);
                public static TestHandPair CASE_58 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.STRAIGHT, 1);
                public static TestHandPair CASE_59 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.FLUSH, 1);
                public static TestHandPair CASE_60 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.FULL_HOUSE, 0);
                public static TestHandPair CASE_61 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.FOUR_OF_A_KIND, 2);
                public static TestHandPair CASE_62 = new TestHandPair(SpecialHands.FULL_HOUSE, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_54,
                        CASE_55,
                        CASE_56,
                        CASE_57,
                        CASE_58,
                        CASE_59,
                        CASE_60,
                        CASE_61,
                        CASE_62
                );
            }

            //four of kind
            public static class FourOfAKindCases {
                public static TestHandPair CASE_63 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_64 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_65 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.TWO_PAIRS, 1);
                public static TestHandPair CASE_66 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.THREE_OF_A_KIND, 1);
                public static TestHandPair CASE_67 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.STRAIGHT, 1);
                public static TestHandPair CASE_68 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.FLUSH, 1);
                public static TestHandPair CASE_69 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.FULL_HOUSE, 1);
                public static TestHandPair CASE_70 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.FOUR_OF_A_KIND, 0);
                public static TestHandPair CASE_71 = new TestHandPair(SpecialHands.FOUR_OF_A_KIND, SpecialHands.STRAIGHT_FLUSH, 2);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_63,
                        CASE_64,
                        CASE_65,
                        CASE_66,
                        CASE_67,
                        CASE_68,
                        CASE_69,
                        CASE_70,
                        CASE_71
                );
            }

            //straight flush
            public static class StraightFlushCases {
                public static TestHandPair CASE_72 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.HIGH_CARD, 1);
                public static TestHandPair CASE_73 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.PAIR, 1);
                public static TestHandPair CASE_74 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.TWO_PAIRS, 1);
                public static TestHandPair CASE_75 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.THREE_OF_A_KIND, 1);
                public static TestHandPair CASE_76 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.STRAIGHT, 1);
                public static TestHandPair CASE_77 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.FLUSH, 1);
                public static TestHandPair CASE_78 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.FULL_HOUSE, 1);
                public static TestHandPair CASE_79 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.FOUR_OF_A_KIND, 1);
                public static TestHandPair CASE_80 = new TestHandPair(SpecialHands.STRAIGHT_FLUSH, SpecialHands.STRAIGHT_FLUSH, 0);

                public static List<TestHandPair> ALL = Arrays.asList(
                        CASE_72,
                        CASE_73,
                        CASE_74,
                        CASE_75,
                        CASE_76,
                        CASE_77,
                        CASE_78,
                        CASE_79,
                        CASE_80
                );
            }
        }
    }

    //special hand types
    public static class SpecialHands {
        //opposite to the other defined test hands, for these it is guaranteed that they satisfy their rank and none of the above ranks

        //high card
        public static List<Card> HIGH_CARD = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.TEN),
                new Card(CardSuit.H, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //pair
        public static List<Card> PAIR = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.ACE),
                new Card(CardSuit.S, CardValue.TEN),
                new Card(CardSuit.H, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //two pairs
        public static List<Card> TWO_PAIRS = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.ACE),
                new Card(CardSuit.S, CardValue.TEN),
                new Card(CardSuit.H, CardValue.TEN),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //three of kind
        public static List<Card> THREE_OF_A_KIND = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.H, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //straight
        public static List<Card> STRAIGHT = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.KING),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.H, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        );
        //flush
        public static List<Card> FLUSH = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //full house
        public static List<Card> FULL_HOUSE = Arrays.asList(
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.H, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //four of kind
        public static List<Card> FOUR_OF_A_KIND = Arrays.asList(
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.H, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.SIX)
        );
        //straight flush
        public static List<Card> STRAIGHT_FLUSH = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.KING),
                new Card(CardSuit.C, CardValue.QUEEN),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN)
        );
    }

    //value pairs
    public static class ValuePairs {
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
        public static List<Card> VALUE_PAIR_OF_3_JACK_2_TEN = Arrays.asList(
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.JACK),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.TEN)
        );
        public static List<Card> VALUE_PAIR_3_ACE = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.NINE)
        );
        public static List<Card> VALUE_PAIR_4_ACE = Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.C, CardValue.QUEEN)
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
        public static List<Card> FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT = Arrays.asList(
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SEVEN)
        );
        public static List<Card> FULL_SEQUENCE_THREE_TO_SEVEN_DIFF_SUIT = Arrays.asList(
                new Card(CardSuit.C, CardValue.THREE),
                new Card(CardSuit.C, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.FIVE),
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
        public static List<Card> FULL_SUIT_PAIR_S_NO_SEQ = Arrays.asList(
                new Card(CardSuit.S, CardValue.ACE),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.TEN),
                new Card(CardSuit.S, CardValue.EIGHT),
                new Card(CardSuit.S, CardValue.SIX)
        );
        public static List<Card> FULL_SUIT_PAIR_S_FULL_SEQ = Arrays.asList(
                new Card(CardSuit.S, CardValue.ACE),
                new Card(CardSuit.S, CardValue.KING),
                new Card(CardSuit.S, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.JACK),
                new Card(CardSuit.S, CardValue.TEN)
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

        Collections.sort(ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN);
        Collections.reverse(ValuePairs.VALUE_PAIR_OF_3_JACK_2_TEN);

        Collections.sort(ValuePairs.VALUE_PAIR_3_ACE);
        Collections.reverse(ValuePairs.VALUE_PAIR_3_ACE);

        Collections.sort(ValuePairs.VALUE_PAIR_5_ACE);
        Collections.reverse(ValuePairs.VALUE_PAIR_5_ACE);

        Collections.sort(ValuePairs.VALUE_PAIR_4_ACE);
        Collections.reverse(ValuePairs.VALUE_PAIR_4_ACE);

        Collections.sort(Sequences.NO_SEQUENCE);
        Collections.reverse(Sequences.NO_SEQUENCE);

        Collections.sort(Sequences.SEQUENCE_THREE_TO_FIVE);
        Collections.reverse(Sequences.SEQUENCE_THREE_TO_FIVE);

        Collections.sort(Sequences.SEQUENCE_THREE_TO_SIX);
        Collections.reverse(Sequences.SEQUENCE_THREE_TO_SIX);

        Collections.sort(Sequences.SEQUENCE_THREE_TO_FOUR);
        Collections.reverse(Sequences.SEQUENCE_THREE_TO_FOUR);

        Collections.sort(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT);
        Collections.reverse(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_SAME_SUIT);

        Collections.sort(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_DIFF_SUIT);
        Collections.reverse(Sequences.FULL_SEQUENCE_THREE_TO_SEVEN_DIFF_SUIT);

        Collections.sort(Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN);
        Collections.reverse(Sequences.SEQUENCES_THREE_TO_FIVE_NINE_TO_TEN);

        Collections.sort(SuitPairs.SUIT_PAIR_H);
        Collections.reverse(SuitPairs.SUIT_PAIR_H);

        Collections.sort(SuitPairs.SUIT_PAIR_H_AND_S);
        Collections.reverse(SuitPairs.SUIT_PAIR_H_AND_S);

        Collections.sort(SuitPairs.SUIT_PAIR_3_H);
        Collections.reverse(SuitPairs.SUIT_PAIR_3_H);

        Collections.sort(SuitPairs.FULL_SUIT_PAIR_S_NO_SEQ);
        Collections.reverse(SuitPairs.FULL_SUIT_PAIR_S_NO_SEQ);

        Collections.sort(SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ);
        Collections.reverse(SuitPairs.FULL_SUIT_PAIR_S_FULL_SEQ);
    }
}
