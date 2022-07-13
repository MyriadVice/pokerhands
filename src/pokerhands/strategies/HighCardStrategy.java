package pokerhands.strategies;

import pokerhands.Card;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A class representing the {@link PokerHandStrategy} for the high card strategy: Hands which do not fit any higher
 * category are ranked by the value of their highest card (high card strategy). If the highest cards have the same
 * value, the hands are ranked by the next highest, and so on
 */
public class HighCardStrategy extends PokerHandStrategy {
    @Override
    public boolean isPermissible(List<Card> hand) {
        //for very card it is always possible to evaluate the card under this strategy
        return true;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        //to compare two cards by this strategy, we compare the values of their highest cards. If the values for the
        // highest cards are equal, we continue with the second highest
        Queue<Card> sortedHand1 = new PriorityQueue<>(Collections.reverseOrder());
        sortedHand1.addAll(hand1);
        Queue<Card> sortedHand2 = new PriorityQueue<>(Collections.reverseOrder());
        sortedHand2.addAll(hand2);

        Card fistHandCard;
        Card secondHandCard;
        for (int i = 0; i < sortedHand1.size(); i++) {
            //get highest cards
            fistHandCard = sortedHand1.poll();
            secondHandCard = sortedHand2.poll();

            //compare
            if (fistHandCard.getValue().ordinal() > secondHandCard.getValue().ordinal()) return hand1;
            if (fistHandCard.getValue().ordinal() < secondHandCard.getValue().ordinal()) return hand2;
            //else, continue with next cards
        }

        //no winning card could be found
        return null;
    }
}
