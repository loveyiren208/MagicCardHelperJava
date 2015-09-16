/*
 * JsonHandler.java
 *
 * Copyright (c) 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * PROPRIETARY/CONFIDENTIAL
 *
 * Use is subject to license terms.
 */

package function;

import card.AbstractCard;
import card.BasicCard;
import card.SynthesisCard;
import collection.AbstractCardsCollection;
import collection.NormalCardsCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JsonHandler {

    // The static key strings for collection
    private static final String COLLECTION_NAME = "CollectionName";
    private static final String COUNT = "Count";
    private static final String IS_OUT_PRINT = "IsOutPrint";
    private static final String STARS = "Stars";
    private static final String IS_CHANGEABLE = "IsChangeable";
    private static final String RELEASE_DATE = "ReleaseDate";
    private static final String OUT_PRINT_DATE = "OutPrintDate";
    private static final String TOTAL_TIME = "TotalTime"; // The value is in hour
    private static final String CARDS = "Cards";

    // The static key strings for card
    private static final String CARD_NAME = "CardName";
    private static final String CARD_VALUE = "Value";
    private static final String SYNTHESIS_TIME = "Time"; // The value is in hour
    private static final String SUB_CARDS_IDS = "SubCardsIDs"; // The value is 3 cards' ids
    private static final int SYNTHESIS_NUMBER = 3;

    private AbstractCardsCollection createOneCollectionFromJSONHelper(final ArrayList<AbstractCardsCollection> allCollections, final JSONObject oneCollectionJSON){
        AbstractCardsCollection newCollection = null;

        String collectionName = oneCollectionJSON.getString(COLLECTION_NAME);

        if (isDuplicateCollection(allCollections, collectionName)) {
            return newCollection;
        }

        int count = oneCollectionJSON.getInt(COUNT);
        boolean isOutPrint = oneCollectionJSON.getBoolean(IS_OUT_PRINT);
        int starts = oneCollectionJSON.getInt(STARS);
        boolean isChangeable = oneCollectionJSON.getBoolean(IS_CHANGEABLE);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = null;
        try {
            releaseDate = formatter.parse(oneCollectionJSON.getString(RELEASE_DATE));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date outPrintDate = null;
        if (isOutPrint) {
            try {
                outPrintDate = formatter.parse(oneCollectionJSON.getString(OUT_PRINT_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int totalTimeInH = oneCollectionJSON.getInt(TOTAL_TIME);


        newCollection = new NormalCardsCollection(count, collectionName, isOutPrint, starts, isChangeable, releaseDate, outPrintDate, totalTimeInH);

        // Set up cards in the new Collection
        AbstractCard[] cards = new AbstractCard[count];
        JSONArray array = oneCollectionJSON.getJSONArray(CARDS);

        for (int i = 0; i < array.length(); i++) {
            JSONObject cardObj = array.getJSONObject(i);

            String cardName = cardObj.getString(CARD_NAME);
            int value = cardObj.getInt(CARD_VALUE);
            int time = cardObj.getInt(SYNTHESIS_TIME);

            JSONArray cardsIds = cardObj.getJSONArray(SUB_CARDS_IDS);
            AbstractCard[] subCards = null;

            if (cardsIds.length() != 0) {
                subCards = new AbstractCard[SYNTHESIS_NUMBER];
                for (int j = 0; j < cardsIds.length(); j++) {
                    int subCardId = cardsIds.getInt(j);
                    subCards[j] = cards[subCardId];
                }
            }

            // If there is no subcards, it means the card is basic card
            if (subCards == null) {
                cards[i] = new BasicCard(i, cardName, value, newCollection);
            } else {
                cards[i] = new SynthesisCard(i, cardName, value, newCollection, subCards, time);
            }
        }

        newCollection.setCards(cards);

        return newCollection;
    }

    public ArrayList<AbstractCardsCollection> createManyCollectionsFromJSON(final ArrayList<AbstractCardsCollection> allCollections, final String path) {
        ArrayList<AbstractCardsCollection> newCollections = new ArrayList<AbstractCardsCollection>();
        JSONArray array = new JSONArray(getStringFromJSON(path));

        for (int i = 0; i < array.length(); i++) {
            AbstractCardsCollection c = createOneCollectionFromJSONHelper(allCollections, array.getJSONObject(i));
            if (c != null) {
                newCollections.add(c);
            }
        }
        return newCollections;
    }

    public AbstractCardsCollection createOneCollectionFromJSON(final ArrayList<AbstractCardsCollection> allCollections, final String path) {
        JSONObject object = new JSONObject(getStringFromJSON(path));
        return createOneCollectionFromJSONHelper(allCollections, object);
    }

    private String getStringFromJSON(final String path) {
        File file = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] data = new byte[(int) file.length()];
        try {
            fis.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = null;
        try {
            str = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    private boolean isDuplicateCollection(final ArrayList<AbstractCardsCollection> allCollections, final String collectionName) {
        if (allCollections == null || allCollections.size() <= 0) {
            return false;
        }

        for (AbstractCardsCollection c : allCollections) {
            if (c.getName().equals(collectionName)) {
                return true;
            }
        }

        return false;
    }
}
