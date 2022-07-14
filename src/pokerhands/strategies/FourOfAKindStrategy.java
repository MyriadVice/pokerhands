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
        //the strategy is permissible if the hand contains a pair of four same values
        return CardUtils.getValuePair(hand, 4) != null;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //in case both of hands satisfy this strategy we rank them against each other comparing the values of the pair
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 4);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 4);

        if (firstHandPair.get(0).getValue().ordinal() > secondHandPair.get(0).getValue().ordinal()) return hand1;
        if (firstHandPair.get(0).getValue().ordinal() < secondHandPair.get(0).getValue().ordinal()) return hand2;

        //else, if both values are the same, we return null, thus resort to the next lower strategy
        return null;
    }
}
