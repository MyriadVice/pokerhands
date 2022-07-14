package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the two pairs strategy: he hand contains 2 different pairs.
 * Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are
 * ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
 */
public class TwoPairsStrategy extends PokerHandStrategy {

    public TwoPairsStrategy() {
        super("Two Pairs");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        //this strategy is valid if the hand contains two pairs

        //find first pair
        List<Card> firstPair = CardUtils.getValuePair(hand, 2);
        if (firstPair == null) return false; //no pair found

        //find the second pair
        List<Card> remainingCards = new LinkedList<>(hand);
        remainingCards.removeAll(firstPair);
        List<Card> secondPair = CardUtils.getValuePair(remainingCards, 2);

        return secondPair != null; //firstPair == null is true
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //the cards we will compare for ranking
        Card firstHandCard = null;
        Card secondHandCard = null;

        //now, we need to find which cards to compare
        //first, we try to rank by value of the highest pair
        List<Card> firstHandHighPair = CardUtils.getValuePair(hand1, 2);
        List<Card> secondHandHighPair = CardUtils.getValuePair(hand2, 2);

        firstHandCard = firstHandHighPair.get(0);
        secondHandCard = secondHandHighPair.get(0);

        //in case the highest values are equal, we rank by the value of the other pair instead
        if (firstHandHighPair.get(0).getValue().ordinal() == secondHandHighPair.get(0).getValue().ordinal()) {
            //get second pair of the cards
            List<Card> firstHandRemainingCards = new LinkedList<>(hand1);
            firstHandRemainingCards.removeAll(firstHandHighPair);
            List<Card> firstHandLowPair = CardUtils.getValuePair(firstHandRemainingCards, 2);

            List<Card> secondHandRemainingCards = new LinkedList<>(hand2);
            secondHandRemainingCards.removeAll(secondHandHighPair);
            List<Card> secondHandLowPair = CardUtils.getValuePair(secondHandRemainingCards, 2);

            firstHandCard = firstHandLowPair.get(0);
            secondHandCard = secondHandLowPair.get(0);

            //in case, the two values of the lower pair of the cards are also the equal, we compare by the value of the
            //remaining card
            if (firstHandLowPair.get(0).getValue().ordinal() == secondHandLowPair.get(0).getValue().ordinal()) {
                //remove second pair, thus that only one card remains which is the leftover card
                List<Card> firstHandLeftover = new LinkedList(hand1);
                firstHandLeftover.removeAll(firstHandHighPair);
                firstHandLeftover.removeAll(firstHandLowPair);

                List<Card> secondHandLeftover = new LinkedList(hand2);
                secondHandLeftover.removeAll(secondHandHighPair);
                secondHandLeftover.removeAll(secondHandLowPair);

                //note that now, we only have one card left in the list which is the leftover card
                firstHandCard = firstHandLeftover.get(0);
                secondHandCard = secondHandLeftover.get(0);
            }
        }

        if (firstHandCard.getValue().ordinal() > secondHandCard.getValue().ordinal()) return hand1;
        if (firstHandCard.getValue().ordinal() < secondHandCard.getValue().ordinal()) return hand2;

        //else, resort to next lower strategy
        return null;
    }
}
