package function;

import card.AbstractCard;
import collection.AbstractCardsCollection;

import java.util.ArrayList;

/**
 * Created by Vivian on 7/21/15.
 */
public class GameHelper {
    public ArrayList<AbstractCard> findCardsFromNonValuableCollections(final ArrayList<AbstractCardsCollection> collections, final int value) {
        return findCardsFromCollections(collections, value, false);
    }

    public ArrayList<AbstractCard> findCardsFromValuableCollections(final ArrayList<AbstractCardsCollection> collections, final int value) {
        return findCardsFromCollections(collections, value, true);
    }

    public ArrayList<AbstractCard> findCardsFromCollections(final ArrayList<AbstractCardsCollection> collections, final int value, final boolean fromValuableCollection) {
        ArrayList<AbstractCard> neededCards = new ArrayList<AbstractCard>();
        for (AbstractCardsCollection collection : collections) {
            if (collection.isValuable() == fromValuableCollection) {
                AbstractCard[] cards = collection.getCards();
                for (int i = 0; i < cards.length; i++) {
                    if (cards[i].getValue() == value) {
                        neededCards.add(cards[1]);
                    }
                }
            }
        }
        return neededCards;
    }
}
