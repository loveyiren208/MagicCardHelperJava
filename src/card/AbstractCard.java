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

    public AbstractCard(final int id, final String name, final int value, final AbstractCardsCollection collection) {
        mId = id;
        mValue = value;
        mName = name;
        mMyCollection = collection;
    }

    public int getValue() {
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public AbstractCardsCollection getMyCollection() {
        return mMyCollection;
    }

}
