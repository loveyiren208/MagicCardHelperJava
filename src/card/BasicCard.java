package card;

import collection.AbstractCardsCollection;

/**
 * Created by Vivian on 7/22/15.
 */
public class BasicCard extends AbstractCard {

    public BasicCard(final int id, final String name, final int value, final AbstractCardsCollection collection) {
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
