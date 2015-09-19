package card;

import level.Level;

/**
 * Created by Vivian on 7/22/15.
 */
public class SynthesisCard extends AbstractCard {
    private AbstractCard[] mSubCards;
    private long mDirectSynthesisTimeInS;


    public SynthesisCard(final int id, final String name, final Level level) {
        super(id, name, level);
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

    public long getDirectSynthesisTimeInH() {
        return mDirectSynthesisTimeInS;
    }

    @Override
    public long getTotalSynthesisTimeInH() {
        return mDirectSynthesisTimeInS + mSubCards[0].getTotalSynthesisTimeInH() + mSubCards[1].getTotalSynthesisTimeInH() +
                mSubCards[2].getTotalSynthesisTimeInH();
    }

}
