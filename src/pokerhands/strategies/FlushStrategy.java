package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the flush strategy. This strategy is permissible on a card if the
 * {@link pokerhands.Hand} contains 5 {@link Card}s of the same {@link pokerhands.CardSuit}. Hands which are both flushes
 * are ranked using the rules for {@link HighCardStrategy}.
 */
public class FlushStrategy extends PokerHandStrategy {

    public FlushStrategy() {
        super("Flush");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return CardUtils.getSuitPair(hand, 5).isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        return new HighCardStrategy().evaluatePair(hand1, hand2);
    }
}
