package pokerhands.strategies;

import javafx.util.Pair;
import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;
import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the four of a kind strategy: 4 cards with the same value.
 * Ranked by the value of the 4 cards.
 **/
public class FourOfAKindStrategy extends PokerHandStrategy {
    public FourOfAKindStrategy() {
        super("Four Of A Kind");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        return CardUtils.getValuePair(hand, 4) != null;
    }

    @Override
    public Optional<Pair<List<Card>, PokerHandStrategy>> evaluatePair(List<Card> hand1, List<Card> hand2) {
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 4);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 4);

        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) > 0)
            return Optional.of(new Pair<>(hand1, this));
        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) < 0)
            return Optional.of(new Pair<>(hand2, this));

        return Optional.empty();
    }
}
