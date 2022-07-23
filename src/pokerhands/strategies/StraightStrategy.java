package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the straight strategy. This strategy is permissible on a {@link pokerhands.Hand}
 * if the hand contains 5 {@link Card}s with consecutive {@link pokerhands.CardValue}s. Hands which both are straights are
 * ranked against each other by the value of their highest card.
 */
public class StraightStrategy extends PokerHandStrategy {

    public StraightStrategy() {
        super("Straight");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return CardUtils.getConsecutiveValues(hand, 5).isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        Optional<HandView> firstHandSeq = CardUtils.getConsecutiveValues(hand1, 5);
        Optional<HandView> secondHandSeq = CardUtils.getConsecutiveValues(hand2, 5);

        if (firstHandSeq.get().compareValues(secondHandSeq.get(), 0, 0) > 0)
            return Optional.of(hand1);
        if (firstHandSeq.get().compareValues(secondHandSeq.get(), 0, 0) < 0)
            return Optional.of(hand2);

        return Optional.empty();
    }
}
