package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the pair strategy: 2 of the 5 cards in the hand have the same
 * value. Hands which both contain a pair are ranked by the value of the cards forming the pair. If these values are the
 * same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
 */
public class PairStrategy extends PokerHandStrategy {
    @Override
    public boolean isPermissible(List<Card> hand) {
        //this card is permissible if at least one value pair is found
        return CardUtils.getValuePair(hand, 2) != null;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //we can assume both pairs exist, thus are not null
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 2);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 2);

        //in case both pair values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order
        if (firstHandPair.get(0).getValue().ordinal() == secondHandPair.get(0).getValue().ordinal()) {
            List<Card> firstHandRemainingCards = new LinkedList<>(hand1);
            firstHandRemainingCards.removeAll(firstHandPair);

            List<Card> secondHandRemainingCards = new LinkedList<>(hand2);
            secondHandRemainingCards.removeAll(secondHandPair);

            //we assume that the lists keep their order on removal of elements and resort to high card strategy (ranked by
            //values not forming the pair, in decreasing order)
            List<Card> higherRankingHand = new HighCardStrategy().evaluatePair(firstHandRemainingCards, secondHandRemainingCards);
            if (higherRankingHand == firstHandRemainingCards) return hand1;
            if (higherRankingHand == secondHandRemainingCards) return hand2;

            //no winner found
            return null;
        }

        //else, we rank by the value of the pair
        return firstHandPair.get(0).getValue().ordinal() > secondHandPair.get(0).getValue().ordinal() ? hand1 : hand2;
    }
}
