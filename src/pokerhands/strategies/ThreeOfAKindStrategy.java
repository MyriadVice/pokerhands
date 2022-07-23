package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the three of a kind strategy. This strategy is permissible on a
 * {@link pokerhands.Hand} if the hand contains three {@link Card}s of the same {@link pokerhands.CardValue}. Hands which
 * both contain three cards of the same value are ranked against each other by the value of these 3-card-pair.
 **/
public class ThreeOfAKindStrategy extends PokerHandStrategy {

    public ThreeOfAKindStrategy() {
        super("Three Of A Kind");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        return CardUtils.getValuePair(hand, 3).isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        Optional<HandView> firstHandPair = CardUtils.getValuePair(hand1, 3);
        Optional<HandView> secondHandPair = CardUtils.getValuePair(hand2, 3);

        if (firstHandPair.get().compareValues(secondHandPair.get(), 0, 0) > 0)
            return Optional.of(hand1);
        if (firstHandPair.get().compareValues(secondHandPair.get(), 0, 0) < 0)
            return Optional.of(hand2);

        return Optional.empty();
    }
}
