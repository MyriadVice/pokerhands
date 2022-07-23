package pokerhands;

import javafx.util.Pair;
import pokerhands.strategies.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Main class as entry point to the program. We want to determine from two {@link Hand}s of poker {@link Card}s which hand
 * wins. To do that, we create different {@link PokerHandStrategy}s used to determine the value of a hand. The different
 * strategies are ordered hierarchically, meaning if a hand can be ranked by a strategy and the other hand can only be
 * ranked a lower strategy, the later losses by default. In case both hands can be ranked by a strategy, certain mechanisms
 * are applied to determine the winner or we resort to the next lower strategy.
 * To determine the winner, we iterate all strategies from highest to lowest and try to rank both hands. If only one can be ranked
 * by the strategy, we have a winner. In case both can be ranked, we try to determine a winner by this strategies mechanisms
 * for ranking two cards against each other or resort to the next lower strategy, if we still can not determine a winner
 * meaning we continue to iterate the strategies.
 * The winning hand is printed in the console. In case we could not determine a winner, we print an error message.
 */
public class Main {
    //both hands as static lists with exactly five cards
    public static Hand hand1 = new Hand(Arrays.asList(
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.H, CardValue.ACE),
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.C, CardValue.EIGHT),
            new Card(CardSuit.C, CardValue.EIGHT)
    ));
    public static Hand hand2 = new Hand(Arrays.asList(
            new Card(CardSuit.C, CardValue.EIGHT),
            new Card(CardSuit.S, CardValue.EIGHT),
            new Card(CardSuit.C, CardValue.NINE),
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.C, CardValue.TWO)
    ));
    private List<PokerHandStrategy> strategies;

    public static void main(String... args) {
        Main handRanker = new Main();
        Optional<Pair<Hand, PokerHandStrategy>> winner = handRanker.rankHands(hand1, hand2);

        if (winner.isPresent()) {
            String handStr = "hand" + ((winner.get().getKey() == hand1) ? "1" : "2");
            System.out.println(handStr + " has won by " + winner.get().getValue().getStrategyName() + "!");
            return;
        }

        System.err.println("[Main:main] Could not determine winning hand from hands: \n hand1: "
                + hand1 + "\n hand2: " + hand2);
    }

    public Main() {
        setupStrategies();
    }

    public List<PokerHandStrategy> getStrategies() {
        return strategies;
    }

    public Optional<Pair<Hand, PokerHandStrategy>> rankHands(Hand hand1, Hand hand2) {
        if (hand1 == null || hand2 == null || hand1.size() == 0 || hand2.size() == 0) return Optional.empty();

        Optional<HandView> winner = Optional.empty();

        boolean firstHandPermissible;
        boolean secondHandPermissible;
        HandView firstHandView = hand1.createView(0, 5);
        HandView secondHandView = hand2.createView(0, 5);
        PokerHandStrategy strategy = null;

        for (int i = 0; i < strategies.size(); i++) {
            strategy = strategies.get(i);

            firstHandPermissible = strategy.isPermissible(firstHandView);
            secondHandPermissible = strategy.isPermissible(secondHandView);

            if (firstHandPermissible && secondHandPermissible) {
                firstHandView.restore();
                secondHandView.restore();
                winner = strategy.evaluatePair(firstHandView, secondHandView);
                if (!winner.isPresent()) continue;
                break;
            }

            if (firstHandPermissible || secondHandPermissible) {
                return Optional.of(new Pair<>(firstHandPermissible ? firstHandView.getHand() : secondHandView.getHand(), strategy));
            }
        }

        return winner.isPresent() ? Optional.of(new Pair<>(winner.get(), strategy)) : Optional.empty();
    }

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
}
