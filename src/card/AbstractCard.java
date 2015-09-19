package card;

import level.Level;

import java.io.Serializable;

/**
 * Created by Vivian on 7/21/15.
 */
public abstract class AbstractCard implements Serializable{
    private int mValue;
    private String mName;

    private int mId;
    // Basic card level is 0. Synthesis cards' level > 0;
    private Level mLevel;

    public AbstractCard(final int id, final String name, final Level level) {
        mId = id;
        mName = name;
        mLevel = level;
    }

    public int getValue() {
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public Level getLevel() {
        return mLevel;
    }



    public abstract long getDirectSynthesisTimeInH();
    public abstract long getTotalSynthesisTimeInH();


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: " + mId + "\n");
        builder.append("name: " + mName + "\n");
        builder.append("value: " + mValue + "\n");
        builder.append("level: " + mLevel + "\n");

        return builder.toString();
    }
}
