package test.logictest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.Card;
import pokerhands.Main;
import test.TestHandPair;
import test.TestHands;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    private Main main;

    @BeforeEach
    void setup() {
        main = new Main();
    }

    @Test
    @DisplayName("Main strategies should be non-empty and hands should be sorted")
    void mainDataCorrect() {
        assertNotNull(main.getStrategies());
        assertNotNull(Main.hand1);
        assertNotNull(Main.hand2);

        assertTrue(main.getStrategies().size() > 0);
        assertTrue(Main.hand1.size() > 0);
        assertTrue(Main.hand2.size() > 0);

        //hand1 sorted check
        Card last = Main.hand1.get(0);
        for (int i = 1; i < Main.hand1.size(); i++) {
            if (Main.hand1.get(i).getValue().ordinal() > last.getValue().ordinal()) fail();
            last = Main.hand1.get(i);
        }

        //hand2 sorted check
        last = Main.hand2.get(0);
        for (int i = 1; i < Main.hand2.size(); i++) {
            if (Main.hand2.get(i).getValue().ordinal() > last.getValue().ordinal()) fail();
            last = Main.hand2.get(i);
        }

        assertTrue(true); //succeed
    }

    @Test
    @DisplayName("Check all special cases with null or empty hands, checking should return correct")
    void checkSpecialCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.SpecialCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkSpecialCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all high card cases, checking should return correct result")
    void checkHighCardCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.HighCardCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkHighCardCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all pair cases, checking should return correct result")
    void checkPairCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.PairCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkPairCardCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all two pair cases, checking should return correct result")
    void checkTwoPairCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.TwoPairsCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkTwoPairCardCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all three of a kind cases, checking should return correct result")
    void checkThreeOfAKindCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.ThreeOfAKindCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkThreeOfAKindCases] Passed test " + (i++) + ".");
        }
    }

    //... todo: all the other cases for input of hands of same rank

    @Test
    @DisplayName("Check all high card cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingHighCardCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.HighCardCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingHighCardCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all pair cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingPairCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.PairCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingPairCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all two pairs cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingTwoPairCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.TwoPairCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingTwoPairCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all three of a kind cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingThreeOfKindCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.ThreeOfAKindCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingThreeOfKindCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all straight cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingStraightCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.StraightCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingStraightCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all flush cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingFlushCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.FlushCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingFlushCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all full house cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingFullHouseCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.FullHouseCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingFullHouseCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all four of a kind cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingFourOfKindCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.FourOfAKindCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingFourOfKindCases] Passed test " + (i++) + ".");
        }
    }

    @Test
    @DisplayName("Check all straight flush cases for evaluating a high card against a differently ranked card, checking should return correct result")
    void checkDifferingStraightFlushCases() {
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.StraightFlushCases.ALL) {
            if (testHandPair.expectedResult == null) {
                assertNull(main.rankHands(testHandPair.hand1, testHandPair.hand2));
            } else {
                assertEquals(testHandPair.expectedResult, main.rankHands(testHandPair.hand1, testHandPair.hand2).getKey());
            }
            System.out.println("[MainTest:checkDifferingStraightFlushCases] Passed test " + (i++) + ".");
        }
    }
}
