package pokerhands.strategies;

import javafx.util.Pair;
import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Pair<List<Card>, PokerHandStrategy>> evaluatePair(List<Card> hand1, List<Card> hand2) {
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 2);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 2);

        List<Card> firstHandRemainingCards = new LinkedList<>(hand1);
        List<Card> secondHandRemainingCards = new LinkedList<>(hand2);

        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) == 0) {
            firstHandRemainingCards.removeAll(firstHandPair);
            secondHandRemainingCards.removeAll(secondHandPair);
        }

        Optional<Pair<List<Card>, PokerHandStrategy>> higherRankingHand = new HighCardStrategy().evaluatePair(firstHandRemainingCards, secondHandRemainingCards);
        if (!higherRankingHand.isPresent()) return higherRankingHand;

        if (higherRankingHand.get().getKey() == firstHandRemainingCards) return Optional.of(new Pair<>(hand1, this));
        if (higherRankingHand.get().getKey() == secondHandRemainingCards) return Optional.of(new Pair<>(hand2, this));

        return Optional.empty();
    }
}
