package pokerhands.strategies;

import pokerhands.Card;

import java.util.List;

/**
 * <p>
 * Abstract class to represent the evaluation of a card under a certain ranking strategy. From lowest to highest these
 * ranking strategies are: </p>
 * <p> High Card: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest
 * cards have the same value, the hands are ranked by the next highest, and so on.
 * </p><br>
 * <p>Pair: 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of
 * the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming
 * the pair, in decreasing order.
 * </p><br>
 * <p>Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their
 * highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the
 * same the hands are ranked by the value of the remaining card.
 * </p><br>
 * <p>Three of a Kind: Three of the cards in the hand have the same value. Hands which both contain three of a kind are
 * ranked by the value of the 3 cards.
 * </p><br>
 * <p>Straight: Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
 * </p><br>
 * <p>Flush: Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
 * </p><br>
 * <p>Full House: 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
 * </p><br>
 * <p>Four of a kind: 4 cards with the same value. Ranked by the value of the 4 cards.
 * </p><br>
 * <p>Straight flush: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
 * </p>
 **/
public abstract class PokerHandStrategy {

    public abstract boolean isPermissible(List<Card> hand);

    public abstract List<Card> evaluatePair(List<Card> hand1, List<Card> hand2);
}
