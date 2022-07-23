package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the high card strategy. This strategy is generally permissible on a
 * {@link pokerhands.Hand}. Hands which are ranked against each other using this strategy are ranked comparing the {@link pokerhands.CardValue}
 * of their highest {@link Card}s. If the highest cards have the same value, the hands are ranked by the next highest, and so on.
 */
public class HighCardStrategy extends PokerHandStrategy {

    public HighCardStrategy() {
        super("High Card");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return true;
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        for (int i = 0; i < hand1.size(); i++) {
            if (hand1.compareValues(hand2, i, i) > 0)
                return Optional.of(hand1);
            if (hand1.compareValues(hand2, i, i) < 0)
                return Optional.of(hand2);
        }

        return Optional.empty();
    }
}
