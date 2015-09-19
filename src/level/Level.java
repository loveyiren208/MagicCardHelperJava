/*
 * Level.java
 *
 * Copyright (c) 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * PROPRIETARY/CONFIDENTIAL
 *
 * Use is subject to license terms.
 */

package level;

import card.AbstractCard;
import collection.AbstractCardsCollection;

public class Level {
    AbstractCard[] mCards;

    // The value of the card in this level
    int mValue;

    // Synthesis card time
    int mTime;

    AbstractCardsCollection mCollection;

    public Level(final int value, final int time, final AbstractCardsCollection collection) {
        mValue = value;
        mTime = time;

        mCollection = collection;
    }

    public AbstractCard[] getCards() {
        return mCards;
    }

    public void setCards(final AbstractCard[] cards) {
        mCards = cards;
    }
}
