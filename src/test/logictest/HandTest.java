package test.logictest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokerhands.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private Main main;

    @BeforeEach
    void setup() {
        main = new Main();
    }

    @Test
    @DisplayName("Hand view contains correct values on creation with valid arguments")
    void handAndViewAreEqual() {
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.JACK),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.H, CardValue.FIVE)
        ));
        HandView view = hand1.createView(0, hand1.size());

        assertEquals(hand1.size(), view.size());

        for (int i = 0; i < hand1.size(); i++)
            if (view.compareValues(hand1, i, i) != 0 || view.compareSuits(hand1, i, i) != 0) fail();

        assertTrue(true);
    }

    @Test
    @DisplayName("Hand view has correct size on creation with valid arguments and range parameters")
    void correctSizeOnCreation() {
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.JACK),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.H, CardValue.FIVE)
        ));
        HandView view = hand1.createView(2, 5);
        assertEquals(3, view.size());
    }

    @Test
    @DisplayName("Hand view contains correct elements after removal")
    void correctRemainingCardOnRemoval() {
        Hand hand1 = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.S, CardValue.JACK),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.H, CardValue.FIVE)
        ));
        HandView view = hand1.createView(0, hand1.size());

        view.remove(Arrays.asList(
                new Card(CardSuit.S, CardValue.JACK),
                new Card(CardSuit.C, CardValue.EIGHT)
        ));

        Hand expected = new Hand(Arrays.asList(
                new Card(CardSuit.C, CardValue.ACE),
                new Card(CardSuit.D, CardValue.QUEEN),
                new Card(CardSuit.H, CardValue.FIVE)
        ));

        assertEquals(expected.size(), view.size());


        for (int i = 0; i < expected.size(); i++)
            if (view.compareValues(expected, i, i) != 0 || view.compareSuits(expected, i, i) != 0) fail();

        assertTrue(true);
    }
}
