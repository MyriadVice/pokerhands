package pokerhands.strategies;

import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the pair strategy. This strategy is permissible on a {@link pokerhands.Hand}
 * if 2 of the 5 cards in the hand have the same {@link pokerhands.CardValue}. Hands which both contain a pair are ranked
 * by the value of the cards forming the pair. If these values are the same, the hands are ranked by the values of the
 * cards not forming the pair, in decreasing order.
 */
public class PairStrategy extends PokerHandStrategy {

    public PairStrategy() {
        super("Pair");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return CardUtils.getValuePair(hand, 2).isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        Optional<HandView> firstHandPair = CardUtils.getValuePair(hand1, 2);
        Optional<HandView> secondHandPair = CardUtils.getValuePair(hand2, 2);

        if (firstHandPair.get().compareValues(secondHandPair.get(), 0, 0) > 0) return Optional.of(hand1);
        if (firstHandPair.get().compareValues(secondHandPair.get(), 0, 0) < 0) return Optional.of(hand2);

        HandView v1 = hand1.createView().remove(firstHandPair.get().getCards());
        HandView v2 = hand2.createView().remove(secondHandPair.get().getCards());
        return new HighCardStrategy().evaluatePair(
                v1, v2
        );
    }
}
