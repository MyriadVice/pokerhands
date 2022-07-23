package pokerhands;

import java.util.Collection;
import java.util.LinkedList;

//we use this class to get a mutable view on a hand without changing its core content or loosing easy access to the main hands reference
public class HandView extends Hand {
    private final Hand hand;
    private final int fromIndex;
    private final int toIndex;

    public HandView(Hand hand, int fromIndex, int toIndex) {
        super(new LinkedList<>());
        for (int i = fromIndex; i < toIndex; i++)
            cards.add(new Card(hand.cards.get(i).getSuit(), hand.cards.get(i).getValue()));
        this.hand = hand;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    public Hand getHand() {
        return hand;
    }

    public HandView remove(Collection<Card> cards) {
        LinkedList<Card> l = new LinkedList<Card>(cards);
        for (Card c : l) this.cards.remove(c);
        return this;
    }

    public HandView narrowView(int from, int to) {
        LinkedList<Card> toRemove = new LinkedList<>();
        for (int i = 0; i < this.cards.size(); i++) {
            if (i < from || i >= to) toRemove.add(this.cards.get(i));
        }
        this.cards.removeAll(toRemove);
        return this;
    }

    public HandView restore() {
        this.cards.clear();
        for (int i = fromIndex; i < toIndex; i++)
            this.cards.add(new Card(hand.cards.get(i).getSuit(), hand.cards.get(i).getValue()));
        return this;
    }

    @Override
    public HandView createView() {
        return new HandView(this.hand, 0, this.size());
    }
}
