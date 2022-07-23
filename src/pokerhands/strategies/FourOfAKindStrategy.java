package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the four of a kind strategy. This strategy is permissible on a
 * {@link pokerhands.Hand} if the {@link pokerhands.Hand} contains 4 {@link Card}s with the same {@link pokerhands.CardValue}.
 * If both hands contain four of a kind, we rank them by the value of these 4 cards.
 **/
public class FourOfAKindStrategy extends PokerHandStrategy {
    public FourOfAKindStrategy() {
        super("Four Of A Kind");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return CardUtils.getValuePair(hand, 4).isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        Optional<HandView> firstHandPair = CardUtils.getValuePair(hand1, 4);
        Optional<HandView> secondHandPair = CardUtils.getValuePair(hand2, 4);

        if (firstHandPair.get().compareValues(secondHandPair.get(), 0, 0) > 0)
            return Optional.of(hand1);
        if (firstHandPair.get().compareValues(secondHandPair.get(), 0, 0) < 0)
            return Optional.of(hand2);

        return Optional.empty();
    }
}
