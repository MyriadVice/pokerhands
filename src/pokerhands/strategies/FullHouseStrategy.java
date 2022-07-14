package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the full house strategy: 3 cards of the same value, with the
 * remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
 */
public class FullHouseStrategy extends PokerHandStrategy {

    public FullHouseStrategy() {
        super("Full House");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        //this strategy is permissible if  we have 3 cards of the same value with the remaining 2 cards forming a pair
        //thus, we have a value pair of 3 and a value pair of 2

        //find the pair of three
        List<Card> pairOfThree = CardUtils.getValuePair(hand, 3);
        if (pairOfThree == null) return false;

        //find the pair of two
        List<Card> remainingCards = new LinkedList<>(hand);
        remainingCards.removeAll(pairOfThree);
        List<Card> pairOfTwo = CardUtils.getValuePair(remainingCards, 2);

        return pairOfTwo != null; //pairOfThree != null is true
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //Ranked by the value of the 3 cards
        List<Card> firstHandPairOfThree = CardUtils.getValuePair(hand1, 3);
        List<Card> secondHandPairOfThree = CardUtils.getValuePair(hand2, 3);

        //we assume the list to be not null
        if (firstHandPairOfThree.get(0).getValue().ordinal() > secondHandPairOfThree.get(0).getValue().ordinal())
            return hand1;
        if (firstHandPairOfThree.get(0).getValue().ordinal() < secondHandPairOfThree.get(0).getValue().ordinal())
            return hand2;

        //else, resort to next lower strategy
        return null;
    }
}
