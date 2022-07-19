package pokerhands.strategies;

import javafx.util.Pair;
import pokerhands.Card;
import pokerhands.utils.CardUtils;

import java.util.List;
import java.util.Optional;

/**
 * A class representing the {@link PokerHandStrategy} for the straight strategy: pokerhands.Hand contains 5 cards with consecutive
 * values. Hands which both contain a straight are ranked by their highest card. (If not, we do not check the other cards
 * but resort to the next lower strategy since two consecutive sequence of same length with same starting value are identical)
 */
public class StraightStrategy extends PokerHandStrategy {

    public StraightStrategy() {
        super("Straight");
    }

    @Override
    public boolean isPermissible(List<Card> hand) {
        return CardUtils.getConsecutiveValues(hand, 5) != null;
    }

    @Override
    public Optional<Pair<List<Card>, PokerHandStrategy>> evaluatePair(List<Card> hand1, List<Card> hand2) {
        List<Card> firstHandConsecutiveValues = CardUtils.getConsecutiveValues(hand1, 5);
        List<Card> secondHandConsecutiveValues = CardUtils.getConsecutiveValues(hand2, 5);

        if (firstHandConsecutiveValues.get(0).getValue().compareTo(secondHandConsecutiveValues.get(0).getValue()) > 0)
            return Optional.of(new Pair<>(hand1, this));
        if (firstHandConsecutiveValues.get(0).getValue().compareTo(secondHandConsecutiveValues.get(0).getValue()) < 0)
            return Optional.of(new Pair<>(hand2, this));

        return Optional.empty();
    }
}
