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
        mCollections = mHelper.getAllCollections();
        System.out.println("collection count: " + mCollections.size());
    }

    public ArrayList<AbstractCardsCollection> getAllCollections() {
        return mCollections;
    }

    public ArrayList<AbstractCard> findCardsFromNotOutPrintCollection(final int value) {
        return mHelper.findCardsFromNotOutPrintCollections(getAllCollections(), value);
    }

    public void addCardsCollection(final ArrayList<AbstractCardsCollection> allCollections, final String newCollectionPath){
        System.out.println("add card");
        AbstractCardsCollection newCollection = null;
        try {
            newCollection = mHelper.createCollectionFromJSON(allCollections, newCollectionPath);
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println("Failed to create card collection " + e.toString());
            return;
        }

        if (newCollection != null) {
            mCollections.add(newCollection);
        }

        save();
    }

    private void save() {
        mHelper.save(mCollections);
    }

    public static void main(String[] args) {
        MagicCardsGame game = new MagicCardsGame();
        game.addCardsCollection(game.getAllCollections(), "res/newCollection");

        game.find(game);

    }

    public void find(MagicCardsGame game) {

        ArrayList<AbstractCard> array = game.findCardsFromNotOutPrintCollection(150);

        for (AbstractCard c : array) {
            System.out.println(c.toString());
        }
    }
}
