package main;

import card.AbstractCard;
import collection.AbstractCardsCollection;
import function.GameHelper;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Vivian on 7/21/15.
 */
public class MagicCardsGame {
    private ArrayList<AbstractCardsCollection> mCollections;

    private GameHelper mHelper = new GameHelper();
    public MagicCardsGame() {
        //mCollections = mHelper.getAllCollections();
    }

    public ArrayList<AbstractCardsCollection> getAllCollections() {
        return mCollections;
    }

    public ArrayList<AbstractCard> findCardsFromNotOutPrintCollection(final int value) {
        return mHelper.findCardsFromNotOutPrintCollections(getAllCollections(), value);
    }

    public void addCardsCollection(String newCollectionPath){
        System.out.println("add card");
        AbstractCardsCollection newCollection = null;
        try {
            newCollection = mHelper.createCollectionFromJSON(newCollectionPath);
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println("Failed to create card collection " + e.toString());
            return;
        }

        if (newCollection != null) {
            mCollections.add(newCollection);
        }
    }

    private void save() {
        mHelper.save(mCollections);
    }

    public static void main(String[] args) {
        MagicCardsGame game = new MagicCardsGame();
        game.addCardsCollection("/Users/Vivian/IdeaProjects/QQMagicCard/res/newCollection");
    }
}
