package function;

import card.AbstractCard;
import collection.AbstractCardsCollection;
import main.MagicCardsGame;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vivian on 7/21/15.
 */
public class GameHelper {

    private static final String ALL_COLLECTIONS_PATH = "res/AllCollections.ser";

    public void changeToAnotherJson(final String oldPath) {
        new JsonHandler().changeJsonToAnotherJson(oldPath);
    }

    public static Date getDateFromString(final String dateString) {
        Date date = null;
        try {
            MagicCardsGame.DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Wrong date input: " + dateString);
        }

        return date;
    }


    public ArrayList<AbstractCardsCollection> getAllCollections() {
        ArrayList<AbstractCardsCollection> allCollections = new ArrayList<AbstractCardsCollection>();

        FileInputStream fis = null;
        ObjectInputStream in = null;


        File file = new File(ALL_COLLECTIONS_PATH);
        System.out.println(" file exist " + file.exists());
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return allCollections;
        }

        try {
            in = new ObjectInputStream(fis);
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

    public AbstractCardsCollection createOneCollectionFromJSON(final ArrayList<AbstractCardsCollection> allCollections, final String path) throws JSONException {
        JsonHandler handler = new JsonHandler();
        return handler.createOneCollectionFromJSON(allCollections, path);
    }

    public ArrayList<AbstractCardsCollection> createManyCollectionsFromJSON(final ArrayList<AbstractCardsCollection> allCollections, final String path) throws JSONException {
        JsonHandler handler = new JsonHandler();
        return handler.createManyCollectionsFromJSON(allCollections, path);
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
                        neededCards.add(cards[i]);
                    }
                }
            }
        }
        return neededCards;
    }
    /*********************** Find cards functions End********************************/
}
