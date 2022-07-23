package pokerhands.strategies;

import pokerhands.Card;
import pokerhands.HandView;

import java.util.Optional;

/**
 * <p>
 * Abstract class to represent the evaluation of a {@link Card} under a certain ranking strategy. Ranking a card by a strategy means
 * checking if this strategy is permissible for ranking this card as well as ranking the card against another card permissible
 * for this strategy. From lowest to highest these ranking strategies are: </p><br>
 * <p>{@link HighCardStrategy}: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest
 * cards have the same value, the hands are ranked by the next highest, and so on.
 * </p><br>
 * <p>{@link PairStrategy}: 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of
 * the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming
 * the pair, in decreasing order.
 * </p><br>
 * <p>{@link TwoPairsStrategy}: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their
 * highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the
 * same the hands are ranked by the value of the remaining card.
 * </p><br>
 * <p>{@link ThreeOfAKindStrategy}: Three of the cards in the hand have the same value. Hands which both contain three of a kind are
 * ranked by the value of the 3 cards.
 * </p><br>
 * <p>{@link StraightStrategy}: pokerhands.Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
 * </p><br>
 * <p>{@link FlushStrategy}: pokerhands.Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
 * </p><br>
 * <p>{@link FullHouseStrategy}: 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
 * </p><br>
 * <p>{@link FourOfAKindStrategy}: 4 cards with the same value. Ranked by the value of the 4 cards.
 * </p><br>
 * <p>{@link StraightFlushStrategy}: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
 * </p>
 **/
public abstract class PokerHandStrategy {
    private final String strategyName;

    protected PokerHandStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public abstract boolean isPermissible(HandView hand);

    public abstract Optional<HandView> evaluatePair(HandView hand1, HandView hand2);
}
