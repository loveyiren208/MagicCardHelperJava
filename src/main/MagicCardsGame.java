package main;

import card.AbstractCard;
import collection.AbstractCardsCollection;
import function.GameHelper;

import java.util.ArrayList;

/**
 * Created by Vivian on 7/21/15.
 */
public class MagicCardsGame {
    private ArrayList<AbstractCardsCollection> mCollections;

    private GameHelper mHelper = new GameHelper();
    public MagicCardsGame() {

    }

    public ArrayList<AbstractCardsCollection> getAllCollections() {
        return mCollections;
    }

    public ArrayList<AbstractCard> findCardsFromNonValuableCollection(final int value) {
        return mHelper.findCardsFromNonValuableCollections(getAllCollections(), value);
    }

    public void addCardsCollection(){

    }
}