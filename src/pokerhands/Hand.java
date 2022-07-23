package pokerhands;

import java.util.Collections;
import java.util.List;

public class Hand {
    protected final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
        Collections.reverse(cards);
    }

    public int compareValues(Hand other, int thisIndex, int otherIndex) {
        return this.cards.get(thisIndex).getValue().compareTo(other.cards.get(otherIndex).getValue());
    }

    public int compareSuits(Hand other, int thisIndex, int otherIndex) {
        return this.cards.get(thisIndex).getSuit().compareTo(other.cards.get(otherIndex).getSuit());
    }

    public List<Card> getCards() {
        return cards;
    }

    public HandView createView(int from, int to) {
        return new HandView(this, from, to);
    }

    public HandView createView() {
        return new HandView(this, 0, this.size());
    }

    public int size() {
        return cards.size();
    }

    public CardValue valueAt(int index) {
        return cards.get(index).getValue();
    }

    public CardSuit suitAt(int index) {
        return cards.get(index).getSuit();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Card c : cards) stringBuilder.append(c).append(" ");
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "]");
        return stringBuilder.toString();
    }
}
