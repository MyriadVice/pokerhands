package pokerhands.strategies;

import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the two pairs strategy. This strategy is permissible on a
 * {@link pokerhands.Hand} if the hand contains 2 potentially different pairs of same {@link pokerhands.CardValue}s. Hands
 * which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are
 * ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
 */
public class TwoPairsStrategy extends PokerHandStrategy {

    public TwoPairsStrategy() {
        super("Two Pairs");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        Optional<HandView> firstPair = CardUtils.getValuePair(hand, 2);
        if (!firstPair.isPresent()) return false;

        Optional<HandView> secondPair = CardUtils.getValuePair(hand.createView().remove(firstPair.get().getCards()), 2);

        return secondPair.isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        Optional<HandView> firstPair;
        Optional<HandView> secondPair;

        HandView firstHandView = hand1.createView();
        HandView secondHandView = hand2.createView();

        for (int i = 0; i < 2; i++) {
            firstPair = CardUtils.getValuePair(firstHandView, 2);
            secondPair = CardUtils.getValuePair(secondHandView, 2);

            if (firstPair.get().compareValues(secondPair.get(), 0, 0) > 0)
                return Optional.of(firstHandView);
            if (firstPair.get().compareValues(secondPair.get(), 0, 0) < 0)
                return Optional.of(secondHandView);

            firstHandView.remove(firstPair.get().getCards());
            secondHandView.remove(secondPair.get().getCards());
        }

        if (firstHandView.compareValues(secondHandView, 0, 0) > 0)
            return Optional.of(hand1);
        if (firstHandView.compareValues(secondHandView, 0, 0) < 0)
            return Optional.of(hand2);

        return Optional.empty();
    }
}
