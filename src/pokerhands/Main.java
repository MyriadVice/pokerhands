package pokerhands;

import javafx.util.Pair;
import pokerhands.strategies.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Main class as entry point to the program. We want to determine from two poker card hands which hand wins.
 */
public class Main {
    //both hands as static lists with exactly five cards
    public static List<Card> hand1 = Arrays.asList(
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.H, CardValue.ACE),
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.C, CardValue.EIGHT),
            new Card(CardSuit.C, CardValue.EIGHT)
    );
    public static List<Card> hand2 = Arrays.asList(
            new Card(CardSuit.C, CardValue.EIGHT),
            new Card(CardSuit.S, CardValue.EIGHT),
            new Card(CardSuit.C, CardValue.NINE),
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.C, CardValue.TWO)
    );
    private List<PokerHandStrategy> strategies;

    public static void main(String... args) {
        Main handRanker = new Main();
        Optional<Pair<List<Card>, PokerHandStrategy>> winner = handRanker.rankHands(hand1, hand2);

        if (winner.isPresent()) {
            String handStr = "hand" + ((winner.get().getKey() == hand1) ? "1" : "2");
            System.out.println(handStr + " has won by " + winner.get().getValue().getStrategyName() + "!");
        }

        System.err.println("[Main:main] Could not determine winning hand from hands: \n hand1: "
                + Arrays.toString(hand1.toArray()) + "\n hand2: " + Arrays.toString(hand2.toArray()));
    }

    public Main() {
        prepareHands();
        setupStrategies();
    }

    /**
     * Method used to prepare the two hands we want to rank against each other
     */
    private void prepareHands() {
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);
    }

    /**
     * Method used to set up all possible strategies to rank two hands against each other.
     */
    private void setupStrategies() {
        strategies = Arrays.asList(
                new StraightFlushStrategy(),
                new FourOfAKindStrategy(),
                new FullHouseStrategy(),
                new FlushStrategy(),
                new StraightStrategy(),
                new ThreeOfAKindStrategy(),
                new TwoPairsStrategy(),
                new PairStrategy(),
                new HighCardStrategy()
        );
    }

    /**
     * Method used to rank two input hands passed as two lists of {@link Card}s against each other. Returns the higher
     * ranked hand, thus the winner of the both hands passed as arguments
     **/
    public Optional<Pair<List<Card>, PokerHandStrategy>> rankHands(List<Card> hand1, List<Card> hand2) {
        if (hand1 == null || hand2 == null || hand1.size() == 0 || hand2.size() == 0) return Optional.empty();

        Optional<Pair<List<Card>, PokerHandStrategy>> winnerHand = Optional.empty();

        boolean firstHandPermissible;
        boolean secondHandPermissible;
        PokerHandStrategy strategy = null;

        for (int i = 0; i < strategies.size(); i++) {
            strategy = strategies.get(i);
            firstHandPermissible = strategy.isPermissible(hand1);
            secondHandPermissible = strategy.isPermissible(hand2);

            if (firstHandPermissible && secondHandPermissible) {
                winnerHand = strategy.evaluatePair(hand1, hand2);
                if (!winnerHand.isPresent()) continue;
                break;
            }

            if (firstHandPermissible || secondHandPermissible) {
                return Optional.of(new Pair<>(firstHandPermissible ? hand1 : hand2, strategy));
            }
        }

        return winnerHand;
    }

    public List<PokerHandStrategy> getStrategies() {
        return strategies;
    }
}
