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
        //System.out.println("collection count: " + mCollections.size());
    }

    public ArrayList<AbstractCardsCollection> getAllCollections() {
        return mCollections;
    }

    public ArrayList<AbstractCard> findCardsFromNotOutPrintCollection(final int value) {
        return mHelper.findCardsFromNotOutPrintCollections(getAllCollections(), value);
    }

    public ArrayList<AbstractCard> findCardsFromOutPrintCollection(final int value) {
        return mHelper.findCardsFromOutPrintCollections(getAllCollections(), value);
    }

    public void addCardsCollection(final ArrayList<AbstractCardsCollection> allCollections, final String newCollectionPath){
        System.out.println("add card");
        AbstractCardsCollection newCollection = null;
        try {
            newCollection = mHelper.createOneCollectionFromJSON(allCollections, newCollectionPath);
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

    public void addManyCardsCollections(final ArrayList<AbstractCardsCollection> allCollections, final String newCollectionPath){
        System.out.println("add card");
        ArrayList<AbstractCardsCollection> newCollections = null;
        try {
            newCollections = mHelper.createManyCollectionsFromJSON(allCollections, newCollectionPath);
        } catch (JSONException e) {
            e.printStackTrace();
            System.err.println("Failed to create card collection " + e.toString());
            return;
        }

        if (newCollections != null) {
            mCollections.addAll(newCollections);
        }

        save();
    }

    private void save() {
        mHelper.save(mCollections);
       System.out.println("saved, total collection count is " + mCollections.size());
    }

    public static void main(String[] args) {
        MagicCardsGame game = new MagicCardsGame();
       game.addManyCardsCollections(game.getAllCollections(), "res/AlreadyStoredCollections");
        game.addCardsCollection(game.getAllCollections(), "res/newCollection");

       game.find(game);
        game.find2(game);
    }

    public void find(MagicCardsGame game) {

        ArrayList<AbstractCard> array = game.findCardsFromNotOutPrintCollection(450);

        for (AbstractCard c : array) {
            System.out.println(c.toString());
        }
    }


    public void find2(MagicCardsGame game) {

        ArrayList<AbstractCard> array = game.findCardsFromOutPrintCollection(450);

        for (AbstractCard c : array) {
            System.out.println(c.toString());
        }
    }
}
