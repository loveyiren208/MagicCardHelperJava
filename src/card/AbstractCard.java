package card;

import collection.AbstractCardsCollection;

/**
 * Created by Vivian on 7/21/15.
 */
public abstract class AbstractCard {
    private int mValue;
    private AbstractCardsCollection mMyCollection;
    private String mName;

    private int mId;
    private int mLevel;

    public AbstractCard(final int id, final String name, final int value, final AbstractCardsCollection collection, final int level) {
        mId = id;
        mValue = value;
        mName = name;
        mMyCollection = collection;
        mLevel = level;
    }

    public int getValue() {
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public int getLevel() {
        return mLevel;
    }

    public AbstractCardsCollection getMyCollection() {
        return mMyCollection;
    }

    public abstract long getDirectSynthesisTimeInS();
    public abstract long getTotalSynthesisTimeInS();
    public boolean isChangable() {
        return mMyCollection.isIsChangeable();
    }

    public boolean isOutOfPrint() {
        return mMyCollection.isOutOfPrint();
    }
}
