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

    public PairStrategy() {
        super("Pair");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        return CardUtils.getValuePair(hand, 2) != null;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 2);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 2);

        List<Card> firstHandRemainingCards = new LinkedList<>(hand1);
        List<Card> secondHandRemainingCards = new LinkedList<>(hand2);

        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) == 0) {
            firstHandRemainingCards.removeAll(firstHandPair);
            secondHandRemainingCards.removeAll(secondHandPair);
        }

        List<Card> higherRankingHand = new HighCardStrategy().evaluatePair(firstHandRemainingCards, secondHandRemainingCards);
        if (higherRankingHand == firstHandRemainingCards) return hand1;
        if (higherRankingHand == secondHandRemainingCards) return hand2;

        return null;
    }
}
