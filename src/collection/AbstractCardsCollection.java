package collection;

import card.AbstractCard;

import java.util.Date;

/**
 * Created by Vivian on 7/21/15.
 */
public class AbstractCardsCollection {

    protected AbstractCard[] mCards;
    private String mName;
    private int mCount;
    private boolean hasCollected = false;
    private int mCollectTimes = 0;
    private boolean mOutOfPrint = false;
    private int mStars;
    private boolean mIsChangeable;
    private Date mReleaseDate;
    private Date mOutOfPrintDate;

    public AbstractCardsCollection(final int count, final String name, final boolean outOfPrint, final int stars, final boolean isChangeable, final Date releaseDate, final Date outOfPrintDate) {
        mCount = count;
        mName = name;
        mOutOfPrint = outOfPrint;
        mStars = stars;
        mIsChangeable = isChangeable;
        mReleaseDate = releaseDate;
        mOutOfPrintDate = outOfPrintDate;
    }

    public int getNumberOfCards() {
        return mCards.length;
    }

    public void isFinished() {
        hasCollected = true;
    }

    public void setCollectTime (final int time) {
        mCollectTimes = time;
    }

    public boolean isValuable() {
        return mOutOfPrint;
    }

    public AbstractCard[] getCards() {
        return mCards;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(mName);
        return builder.toString();
    }
}
