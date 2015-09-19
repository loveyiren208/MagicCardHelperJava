package card;

import level.Level;

/**
 * Created by Vivian on 7/22/15.
 */
public class BasicCard extends AbstractCard {

    public BasicCard(final int id, final String name, final Level level) {
        super(id, name, level);
    }

    @Override
    public long getDirectSynthesisTimeInH() {
        return 0;
    }

    @Override
    public long getTotalSynthesisTimeInH() {
        return 0;
    }
}
