package pokerhands;

import javafx.util.Pair;
import pokerhands.strategies.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Main class as entry point to the program. We want to determine from two poker card hands which hand wins.
 */
public class Main {
    //both hands as static lists with exactly five cards
    private static List<Card> hand1 = Arrays.asList(
            new Card(CardSuit.C, CardValue.ACE),
            new Card(CardSuit.H, CardValue.JACK),
            new Card(CardSuit.C, CardValue.NINE),
            new Card(CardSuit.D, CardValue.SEVEN),
            new Card(CardSuit.S, CardValue.NINE)
    );
    private static List<Card> hand2 = Arrays.asList(
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.H, CardValue.ACE),
            new Card(CardSuit.C, CardValue.KING),
            new Card(CardSuit.D, CardValue.QUEEN),
            new Card(CardSuit.S, CardValue.THREE)
    );
    private List<PokerHandStrategy> strategies;

    public static void main(String... args) {

        //create a main instance, both hands are sorted and all strategies prepares
        Main handRanker = new Main();

        //get the winning hand
        Pair<List<Card>, PokerHandStrategy> winner = handRanker.rankHands(hand1, hand2);

        //in case we could not determine a winning hand, both hands are equal to each other in values (since otherwise
        //the high card strategy would have determined a winner)
        if (winner == null) {
            System.err.println("[Main:main] Could not determine winning hand from hands: \n hand1: "
                    + Arrays.toString(hand1.toArray()) + "\n hand2: " + Arrays.toString(hand2.toArray()));
        } else {
            String handStr = "hand" + ((winner.getKey() == hand1) ? "1" : "2");
            System.out.println(handStr + " has won by " + winner.getValue().getStrategyName() + "!");
        }
    }

    public Main() {
        prepareHands();
        setupStrategies();
    }

    /**
     * Method used to prepare the two hands we want to rank against each other
     */
    private void prepareHands() {
        //we sort the hands by their card values in descending order prior to processing, thus we can assume that the
        //hands are always sorted when passed to the strategies as long as we do not modify them
        Collections.sort(hand1);
        Collections.reverse(hand1);

        Collections.sort(hand2);
        Collections.reverse(hand2);
    }

    /**
     * Method used to set up all possible strategies to rank two hands against each other.
     */
    private void setupStrategies() {
        //for each strategy that could be used to determine the winning hand, we create a dedicated strategy and add it
        //to the list of strategies. The list is ordered descending according to the rank of the hand associated with the
        //strategy e.g. the high card strategy is the lowest thus the last element in the list while the straight flush
        //strategy is the highest and thus the first element of the list
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
    public Pair<List<Card>, PokerHandStrategy> rankHands(List<Card> hand1, List<Card> hand2) {
        //check for malformed input
        if (hand1 == null || hand2 == null || hand1.size() == 0 || hand2.size() == 0) return null;

        List<Card> winnerHand = null;

        boolean firstHandPermissible;
        boolean secondHandPermissible;
        PokerHandStrategy strategy = null;
        //now we iterate over all strategies from the highest rank strategy to the lowest rank strategy
        for (int i = 0; i < strategies.size(); i++) {
            //we get the next strategy and see if at least one of the hands could be evaluated by this strategy
            strategy = strategies.get(i);
            firstHandPermissible = strategy.isPermissible(hand1);
            secondHandPermissible = strategy.isPermissible(hand2);

            //in case both hands can be evaluated by this strategy, we evaluated both hands against each other according
            //to this strategies ruleset for this
            if (firstHandPermissible && secondHandPermissible) {
                winnerHand = strategy.evaluatePair(hand1, hand2);
                //in case for some reason both hands are equally evaluated under this strategy, we resort to the next lower strategy
                if (winnerHand == null) continue;

                break; //else winner determined, done
            }

            //in case only one hand could be evaluated by this strategy, the other hand falls into a lower rank since
            //we would have already handled the other case earlier on
            if (firstHandPermissible || secondHandPermissible) {
                winnerHand = firstHandPermissible ? hand1 : hand2;
                break;
            }
        }

        return winnerHand != null ? new Pair<>(winnerHand, strategy) : null;
    }

    public List<PokerHandStrategy> getStrategies() {
        return strategies;
    }
}
