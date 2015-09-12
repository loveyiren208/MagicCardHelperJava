package collection;

import card.BasicCard;
import card.SynthesisCard;

import java.util.Date;

/**
 * Created by Vivian on 7/23/15.
 */
public class NormalCardsCollection extends AbstractCardsCollection {
    public NormalCardsCollection(final int count, final String name, final boolean outOfPrint, final int stars,
                                 final boolean isChangeable, final Date releaseDate, final Date outOfPrintDate, final long totalTime) {
        super(count, name, outOfPrint, stars, isChangeable, releaseDate, outOfPrintDate, totalTime);
    }

    public void setUpBasicCards(final int value, final int count) {
        for (int i = 0 ; i < count; i++) {
            mCards[i] = new BasicCard(i, null, value, this);
        }
    }

    public void setUpSysthesisCard(final int id, final int value, final int subId1, final int subId2, final int subId3, final int time) {
        mCards[id] = new SynthesisCard(id, null, value, this, mCards[subId1], mCards[subId2], mCards[subId3], time);
    }

    public void setUpSysthesisRelationship(final int targetId, final int subId1, final int subId2, final int subId3) {
        ((SynthesisCard)mCards[targetId]).setUpSubCards(mCards[subId1], mCards[subId2], mCards[subId3]);
    }
}
