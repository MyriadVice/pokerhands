package pokerhands.strategies;

import javafx.util.Pair;
import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;
import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the three of a kind strategy: Three of the cards in the hand
 * have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards.
 **/
public class ThreeOfAKindStrategy extends PokerHandStrategy {

    public ThreeOfAKindStrategy() {
        super("Three Of A Kind");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        //the strategy is permissible if the hand contains a pair of three same values
        return CardUtils.getValuePair(hand, 3) != null;
    }

    @Override
    public Optional<Pair<List<Card>, PokerHandStrategy>> evaluatePair(List<Card> hand1, List<Card> hand2) {
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 3);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 3);

        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) > 0)
            return Optional.of(new Pair<>(hand1, this));
        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) < 0)
            return Optional.of(new Pair<>(hand2, this));

        return Optional.empty();
    }
}
