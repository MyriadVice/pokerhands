package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;
import pokerhands.utils.CardUtils;

import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the full house strategy. This strategy is permissible if the
 * {@link pokerhands.Hand} contains 3 {@link Card}s of the same {@link pokerhands.CardValue}, with the
 * remaining 2 cards forming a pair. If both hands can be ranked by this strategy, we rank them by the value of the 3-card-pair.
 */
public class FullHouseStrategy extends PokerHandStrategy {

    public FullHouseStrategy() {
        super("Full House");
    }

    @Override
    public boolean isPermissible(HandView hand) {
        Optional<HandView> pairOfThree = CardUtils.getValuePair(hand, 3);
        if (!pairOfThree.isPresent()) return false;

        Optional<HandView> pairOfTwo = CardUtils.getValuePair(hand.createView().remove(pairOfThree.get().getCards()), 2);

        return pairOfTwo.isPresent();
    }

    @Override
    public Optional<HandView> evaluatePair(HandView hand1, HandView hand2) {
        Optional<HandView> firstHandPairOfThree = CardUtils.getValuePair(hand1, 3);
        Optional<HandView> secondHandPairOfThree = CardUtils.getValuePair(hand2, 3);

        if (firstHandPairOfThree.get().compareValues(secondHandPairOfThree.get(), 0, 0) > 0)
            return Optional.of(hand1);
        if (firstHandPairOfThree.get().compareValues(secondHandPairOfThree.get(), 0, 0) < 0)
            return Optional.of(hand2);

        return Optional.empty();
    }
}
