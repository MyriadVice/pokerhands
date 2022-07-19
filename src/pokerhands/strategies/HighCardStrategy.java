package pokerhands.strategies;

import pokerhands.Card;

import java.util.List;

/**
 * A class representing the {@link PokerHandStrategy} for the high card strategy: Hands which do not fit any higher
 * category are ranked by the value of their highest card (high card strategy). If the highest cards have the same
 * value, the hands are ranked by the next highest, and so on
 */
public class HighCardStrategy extends PokerHandStrategy {

    public HighCardStrategy() {
        super("High Card");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        return true;
    }

    @Override
    public List<Card> evaluatePair(List<Card> hand1, List<Card> hand2) {
        for (int i = 0; i < hand1.size(); i++) {
            if (hand1.get(i).getValue().compareTo(hand2.get(i).getValue()) > 0) return hand1;
            if (hand1.get(i).getValue().compareTo(hand2.get(i).getValue()) < 0) return hand2;
        }

        return null;
    }
}
