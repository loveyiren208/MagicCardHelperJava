package function;

import card.AbstractCard;
import collection.AbstractCardsCollection;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * Created by Vivian on 7/21/15.
 */
public class GameHelper {

    private static final String ALL_COLLECTIONS_PATH = "AllCollections.ser";

    public ArrayList<AbstractCardsCollection> getAllCollections() {
        ArrayList<AbstractCardsCollection> allCollections = new ArrayList<AbstractCardsCollection>();

        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(ALL_COLLECTIONS_PATH);
            in = new ObjectInputStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return allCollections;
        } catch (IOException e) {
            e.printStackTrace();
            return allCollections;
        }

        try {
            allCollections = (ArrayList<AbstractCardsCollection>)in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return allCollections;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return allCollections;
        }

        return allCollections;
    }

    public void save(final ArrayList<AbstractCardsCollection> collections) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(ALL_COLLECTIONS_PATH);
            out = new ObjectOutputStream(fos);

            out.writeObject(collections);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public AbstractCardsCollection createCollectionFromJSON(final String path) throws JSONException {
        JsonHandler handler = new JsonHandler();
        return handler.createCollectionFromJSON(path);
    }


    /*********************** Find cards functions ********************************/
    public ArrayList<AbstractCard> findCardsFromNotOutPrintCollections(final ArrayList<AbstractCardsCollection> collections, final int value) {
        return findCardsFromCollections(collections, value, false);
    }

    public ArrayList<AbstractCard> findCardsFromOutPrintCollections(final ArrayList<AbstractCardsCollection> collections, final int value) {
        return findCardsFromCollections(collections, value, true);
    }

    public ArrayList<AbstractCard> findCardsFromCollections(final ArrayList<AbstractCardsCollection> collections, final int value, final boolean fromOutPrintCollection) {
        ArrayList<AbstractCard> neededCards = new ArrayList<AbstractCard>();
        for (AbstractCardsCollection collection : collections) {
            if (collection.isOutOfPrint() == fromOutPrintCollection) {
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
    /*********************** Find cards functions End********************************/
}
