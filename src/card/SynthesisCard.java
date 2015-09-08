package card;

import collection.AbstractCardsCollection;

/**
 * Created by Vivian on 7/22/15.
 */
public class SynthesisCard extends AbstractCard {
    private AbstractCard[] mSubCards;
    private int mSynthesisTime;

    public SynthesisCard(final int id, final String name, final int value, final AbstractCardsCollection collection) {
        super(id, name, value, collection);
    }

    public SynthesisCard(final int id, final String name, final int value, final AbstractCardsCollection collection, final int time) {
        this(id, name, value, collection);
        mSynthesisTime = time;
    }

    public SynthesisCard(final int id, final String name, final int value, final AbstractCardsCollection collection,
                         final AbstractCard[] cards, final int time) {
        this(id, name, value, collection, time);
        setUpSubCards(cards);
    }

    public SynthesisCard(final int id, final String name, final int value, final AbstractCardsCollection collection, final AbstractCard c1,
                         final AbstractCard c2, final AbstractCard c3, final int time) {
        super(id, name, value, collection);
        setUpSubCards(c1, c2, c3);
        mSynthesisTime = time;

    }

    public void setUpSubCards(final AbstractCard[] cards) {
        mSubCards = cards;
    }

    public void setUpSubCards(final AbstractCard c1, final AbstractCard c2, final AbstractCard c3) {
        mSubCards[0] = c1;
        mSubCards[1] = c2;
        mSubCards[2] = c3;
    }

    public AbstractCard[] getSubCards() {
        return mSubCards;
    }
}
