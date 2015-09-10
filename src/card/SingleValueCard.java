/*
 * SingleValueCard.java
 *
 * Copyright (c) 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * PROPRIETARY/CONFIDENTIAL
 *
 * Use is subject to license terms.
 */

package card;

import collection.AbstractCardsCollection;

public class SingleValueCard extends AbstractCard {
    public SingleValueCard(int id, String name, int value, AbstractCardsCollection collection) {
        super(id, name, value, collection, 0);
    }

    @Override
    public long getDirectSynthesisTimeInS() {
        return 0;
    }

    @Override
    public long getTotalSynthesisTimeInS() {
        return 0;
    }
}
