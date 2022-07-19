package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;

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
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 4);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 4);

        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) > 0) return hand1;
        if (firstHandPair.get(0).getValue().compareTo(secondHandPair.get(0).getValue()) < 0) return hand2;

        return null;
    }
}
