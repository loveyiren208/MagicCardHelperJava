package collection;

import card.AbstractCard;
import function.GameHelper;
import level.Level;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vivian on 7/21/15.
 */
public class AbstractCardsCollection implements Serializable {

    protected AbstractCard[] mCards;

    protected Level[] mLevels;

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

    public AbstractCardsCollection(final String name, final int stars,
                                   final boolean isChangeable, final Date releaseDate, final Date outOfPrintDate) {
        mName = name;
        mStars = stars;
        mIsChangeable = isChangeable;
        mReleaseDate = releaseDate;
        mOutOfPrintDate = outOfPrintDate;

        if (mOutOfPrintDate != null) {
            mIsOutOfPrint = true;
        }
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

    public void setReleaseDate(final String dateString) {
        Date date = GameHelper.getDateFromString(dateString);
        if (date != null) {
            mReleaseDate = date;
        }
    }

    public void setOutOfPrintDate(final String dateString) {
        Date date = GameHelper.getDateFromString(dateString);
        if (date != null) {
            mOutOfPrintDate = date;
            setOutOfPrint();
        }
    }



    public void setAlreadyCollected() {
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

    public void setUpLevels(final Level[] levels) {
        mLevels = levels;
    }

    public void setCards(final AbstractCard[] cards) {
        mCards = cards;
        mTotalTimeInH = getTotalTimeInH();
    }

    public int getTotalTimeInH() {
        int time = 0;
        for (AbstractCard c : mCards) {
            time += c.getTotalSynthesisTimeInH();
        }
        return time;
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
