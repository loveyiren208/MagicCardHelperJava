package card;

import collection.AbstractCardsCollection;

import java.io.Serializable;

/**
 * Created by Vivian on 7/21/15.
 */
public abstract class AbstractCard implements Serializable{
    private int mValue;
    private AbstractCardsCollection mMyCollection;
    private String mName;

    private int mId;
    // Basic card level is 0. Synthesis cards' level > 0;
    private int mLevel;

    public AbstractCard(final int id, final String name, final int value, final AbstractCardsCollection collection, final int level) {
        mId = id;
        mValue = value;
        mName = name;
        mMyCollection = collection;
        mLevel = level;
        System.out.println(" value " + value);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: " + mId + "\n");
        builder.append("name: " + mName + "\n");
        builder.append("value: " + mValue + "\n");
        builder.append("level: " + mLevel + "\n");
        builder.append("collection: " + mMyCollection.getName() + "\n");
        builder.append("isChangeable: " + isChangable() + "\n");
        builder.append("isOutOfPrint: " + isOutOfPrint() + "\n");

        return builder.toString();
    }
}
