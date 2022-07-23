package test.logictest;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.CardValue;
import pokerhands.Hand;
import pokerhands.Main;
import pokerhands.strategies.PokerHandStrategy;
import test.TestHandPair;
import test.TestHands;

import java.util.Optional;

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
        CardValue last = Main.hand1.valueAt(0);
        for (int i = 1; i < Main.hand1.size(); i++) {
            if (Main.hand1.valueAt(i).compareTo(last) > 0) fail();
            last = Main.hand1.valueAt(i);
        }

        //hand2 sorted check
        last = Main.hand2.valueAt(0);
        for (int i = 1; i < Main.hand2.size(); i++) {
            if (Main.hand2.valueAt(i).compareTo(last) > 0) fail();
            last = Main.hand2.valueAt(i);
        }

        assertTrue(true);
    }

    @Test
    @DisplayName("Check all special cases with null or empty hands, checking should return correct results")
    void checkSpecialCases() {
        int j = TestHands.TestHandPairs.SpecialCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.SpecialCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkSpecialCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a high card hand against another hand of arbitrary rank, checking should return correct result")
    void checkDifferingHighCardCases() {
        int j = TestHands.TestHandPairs.DifferingCases.HighCardCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.HighCardCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingHighCardCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a pair hand against a differently ranked hand, checking should return correct result")
    void checkDifferingPairCases() {
        int j = TestHands.TestHandPairs.DifferingCases.PairCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.PairCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingPairCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a two paired hand against a differently ranked hand, checking should return correct result")
    void checkDifferingTwoPairCases() {
        int j = TestHands.TestHandPairs.DifferingCases.TwoPairCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.TwoPairCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingTwoPairCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a three of a kind hand against a differently ranked hand, checking should return correct result")
    void checkDifferingThreeOfKindCases() {
        int j = TestHands.TestHandPairs.DifferingCases.ThreeOfAKindCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.ThreeOfAKindCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingThreeOfKindCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a straight hand against a differently ranked hand, checking should return correct result")
    void checkDifferingStraightCases() {
        int j = TestHands.TestHandPairs.DifferingCases.StraightCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.StraightCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingStraightCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a flush hand against a differently ranked hand, checking should return correct result")
    void checkDifferingFlushCases() {
        int j = TestHands.TestHandPairs.DifferingCases.FlushCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.FlushCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingFlushCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a full house hand against a differently ranked hand, checking should return correct result")
    void checkDifferingFullHouseCases() {
        int j = TestHands.TestHandPairs.DifferingCases.FullHouseCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.FullHouseCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingFullHouseCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a four of a kind hand against a differently ranked hand, checking should return correct result")
    void checkDifferingFourOfKindCases() {
        int j = TestHands.TestHandPairs.DifferingCases.FourOfAKindCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.FourOfAKindCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingFourOfKindCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }

    @Test
    @DisplayName("Check all cases for evaluating a straight flush hand against a differently ranked hand, checking should return correct result")
    void checkDifferingStraightFlushCases() {
        int j = TestHands.TestHandPairs.DifferingCases.StraightFlushCases.ALL.size();
        int i = 0;
        for (TestHandPair testHandPair : TestHands.TestHandPairs.DifferingCases.StraightFlushCases.ALL) {
            Optional<Pair<Hand, PokerHandStrategy>> result = main.rankHands(testHandPair.hand1, testHandPair.hand2);
            assertEquals(testHandPair.expectedResult, result.isPresent() ? result.get().getKey() : null);
            System.out.println("[MainTest:checkDifferingStraightFlushCases] Passed test (" + (++i) + "/" + j + ")");
        }
    }
}
