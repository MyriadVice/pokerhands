package pokerhands;

import pokerhands.strategies.PokerHandStrategy;

import java.util.Arrays;
import java.util.LinkedList;
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

    public static void main(String... args) {
        List<Card> winnerHand = null;

        //for each strategy that could be used to determine the winning hand, we create a dedicated strategy and add it
        //to the list of strategies. The list is ordered descending according to the rank of the hand associated with the
        //strategy e.g. the high card strategy is the lowest thus the last element in the list while the straight flush
        //strategy is the highest and thus the first element of the list
        List<PokerHandStrategy> strategies = new LinkedList<>();
        //todo: add all strategies in descending order

        boolean firstHandPermissible;
        boolean secondHandPermissible;
        PokerHandStrategy strategy;
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

        //in case we could yet not determine a winning hand, there must be an error since the last high card strategy
        //should be save to determine a winner
        if (winnerHand == null) {
            System.err.println("[Main:main] Unknown error. Could not determine winning hand from: \n hand1: "
                    + Arrays.toString(hand1.toArray()) + "\n hand2: " + Arrays.toString(hand2.toArray()));
        } else {
            String handStr = "hand" + ((winnerHand == hand1) ? "1" : "2");
            System.out.println(handStr + " has won!");
        }
    }
}
