package collection;

import card.AbstractCard;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vivian on 7/21/15.
 */
public class AbstractCardsCollection implements Serializable {

    protected AbstractCard[] mCards;
    private String mName;
    private int mCount;
    private boolean hasCollected = false;
    private int mCollectTimes = 0;
    private boolean mIsOutOfPrint = false;
    private int mStars;
    private boolean mIsChangeable;
    private Date mReleaseDate;
    private Date mOutOfPrintDate;

    private long mTotalTimeInH;

    public AbstractCardsCollection(final int count, final String name, final boolean outOfPrint, final int stars,
                                   final boolean isChangeable, final Date releaseDate, final Date outOfPrintDate,
                                   final long totalTimeInH) {
        mCount = count;
        mName = name;
        mIsOutOfPrint = outOfPrint;
        mStars = stars;
        mIsChangeable = isChangeable;
        mReleaseDate = releaseDate;
        mOutOfPrintDate = outOfPrintDate;
        mTotalTimeInH = totalTimeInH;
    }

    public String getName() {
        return mName;
    }

    public int getNumberOfCards() {
        if (mCards == null) {
            return 0;
        } else {
            return mCards.length;
        }
    }

    public void isAlreadyCollected() {
        hasCollected = true;
    }

    public void setCollectTimes(final int time) {
        mCollectTimes = time;
    }

    public boolean isOutOfPrint() {
        return mIsOutOfPrint;
    }

    public void setOutOfPrint() {
         mIsOutOfPrint = true;
    }
    public void setCards(final AbstractCard[] cards) {
        mCards = cards;
    }

    public AbstractCard[] getCards() {
        return mCards;
    }

    public boolean isIsChangeable() {
        return mIsChangeable;
    }

    public long getTotalTimeinH() {
        return mTotalTimeInH;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(mName);
        return builder.toString();
    }
}
