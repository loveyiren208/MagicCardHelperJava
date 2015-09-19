package collection;

import card.BasicCard;

import java.util.Date;

/**
 * Created by Vivian on 7/22/15.
 */
public class SingleValueCardsCollection extends AbstractCardsCollection {
    public SingleValueCardsCollection (final int count, final int value, final String name, final boolean outOfPrint, final int stars,
                                       final Date releaseDate, final Date outOfPrintDate) {
        super(name, stars, true, releaseDate, outOfPrintDate);
        for (int i = 0; i < count; i++) {
            mCards[i] = new BasicCard(i, null, value, this);
        }
    }
}
