package card;

import collection.AbstractCardsCollection;

/**
 * Created by Vivian on 7/22/15.
 */
public class SynthesisCard extends AbstractCard {
    private AbstractCard[] mSubCards;
    private long mDirectSynthesisTimeInS;


    public SynthesisCard(final int id, final String name, final int value, final AbstractCardsCollection collection,
                         final AbstractCard[] cards, final int time) {
        super(id, name, value, collection, cards[0].getLevel() + 1);
        mDirectSynthesisTimeInS = time;
        setUpSubCards(cards);
    }

    public SynthesisCard(final int id, final String name, final int value, final AbstractCardsCollection collection, final AbstractCard c1,
                         final AbstractCard c2, final AbstractCard c3, final int time) {
        super(id, name, value, collection, c1.getLevel() + 1);
        setUpSubCards(c1, c2, c3);
        mDirectSynthesisTimeInS = time;
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

    public long getDirectSynthesisTimeInS() {
        return mDirectSynthesisTimeInS;
    }

    @Override
    public long getTotalSynthesisTimeInS() {
        return mDirectSynthesisTimeInS + mSubCards[0].getTotalSynthesisTimeInS() + mSubCards[1].getTotalSynthesisTimeInS() +
                mSubCards[2].getTotalSynthesisTimeInS();
    }

}
