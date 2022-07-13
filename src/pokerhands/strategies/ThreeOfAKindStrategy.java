package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;

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
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //in case both of hands satisfy this strategy we rank them against each other comparing the values of the pair
        List<Card> firstHandPair = CardUtils.getValuePair(hand1, 3);
        List<Card> secondHandPair = CardUtils.getValuePair(hand2, 3);

        if (firstHandPair.get(0).getValue().ordinal() > secondHandPair.get(0).getValue().ordinal()) return hand1;
        if (firstHandPair.get(0).getValue().ordinal() < secondHandPair.get(0).getValue().ordinal()) return hand2;

        //else, if both values are the same, we return null, thus resort to the next lower strategy
        return null;
    }
}
