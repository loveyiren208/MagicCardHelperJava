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
import level.Level;
import main.MagicCardsGame;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class JsonHandler {

    // The static key strings for collection
    private static final String COLLECTION_NAME = "CollectionName";
    private static final String STARS = "Stars";
    private static final String IS_CHANGEABLE = "IsChangeable";
    private static final String RELEASE_DATE = "ReleaseDate";
    private static final String OUT_PRINT_DATE = "OutPrintDate";
    private static final String CARDS = "Cards";
    private static final String COLLECTION_LEVELS = "Levels";

    private static final String LEVEL_CARDS = "LevelCards";

    // The static key strings for card
    private static final String CARD_NAME = "CardName";
    private static final String CARD_VALUE = "Value";
    private static final String SYNTHESIS_TIME = "Time"; // The value is in hour
    private static final String SUB_CARDS_IDS = "SubCardsIDs"; // The value is 3 cards' ids
    private static final int SYNTHESIS_NUMBER = 3;

    private static final String SYNTHESIS_CARD_LEVEL = "SynthesisCardLevel";
    private static final String SYNTHESIS_CARD_ID = "SynthesisCardId";
    private static final String SYNTHESIS_CARDS = "SynthesisCards";



    private AbstractCardsCollection createOneCollectionFromJSONHelper(final ArrayList<AbstractCardsCollection> allCollections, final JSONObject oneCollectionJSON){
        AbstractCardsCollection newCollection = null;

        String collectionName = oneCollectionJSON.getString(COLLECTION_NAME);

        if (isDuplicateCollection(allCollections, collectionName)) {
            return newCollection;
        }

        boolean isOutPrint = false;
        int starts = 0;
        if (oneCollectionJSON.has(STARS)) {
            starts = oneCollectionJSON.getInt(STARS);
        }

        boolean isChangeable = oneCollectionJSON.getBoolean(IS_CHANGEABLE);

        Date releaseDate = null;
        try {
            releaseDate = MagicCardsGame.DATE_FORMAT.parse(oneCollectionJSON.getString(RELEASE_DATE));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date outPrintDate = null;

        String outPrintDateString = null;
        if (oneCollectionJSON.has(OUT_PRINT_DATE) && (!oneCollectionJSON.get(OUT_PRINT_DATE).equals(JSONObject.NULL))) {
            System.out.println(" out print date " + oneCollectionJSON.get(OUT_PRINT_DATE).getClass());
            oneCollectionJSON.getString(OUT_PRINT_DATE);
        }

        if (outPrintDateString != null) {
            try {
                outPrintDate = MagicCardsGame.DATE_FORMAT.parse(oneCollectionJSON.getString(OUT_PRINT_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        JSONArray array = oneCollectionJSON.getJSONArray(CARDS);
        AbstractCard[] cards = new AbstractCard[array.length()];

        newCollection = new NormalCardsCollection(collectionName, starts, isChangeable, releaseDate, outPrintDate);

//        for (int i = 0; i < array.length(); i++) {
//            JSONObject cardObj = array.getJSONObject(i);
//
//            String cardName = cardObj.getString(CARD_NAME);
//            int value = cardObj.getInt(CARD_VALUE);
//            JSONArray cardsIds = null;
//
//            if (cardObj.has(SUB_CARDS_IDS)) {
//                cardsIds = cardObj.getJSONArray(SUB_CARDS_IDS);
//            }
//
//            AbstractCard[] subCards = null;
//
//            if (cardsIds == null) {
//                cards[i] = new BasicCard(i, cardName, value, newCollection);
//
//            } else {
//                subCards = new AbstractCard[SYNTHESIS_NUMBER];
//                for (int j = 0; j < cardsIds.length(); j++) {
//                    int subCardId = cardsIds.getInt(j);
//                    subCards[j] = cards[subCardId];
//                }
//
//                if (cardsIds.length() == 0) {
//                    cards[i] = new BasicCard(i, cardName, value, newCollection);
//                } else {
//                    int time = cardObj.getInt(SYNTHESIS_TIME);
//                    cards[i] = new SynthesisCard(i, cardName, value, newCollection, subCards, time);
//                }
//            }
//        }

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


    public void changeJsonToAnotherJson(final String oldJsonPath) {
        JSONArray oldArray = new JSONArray(getStringFromJSON(oldJsonPath));

        JSONArray newArray = new JSONArray();
        for (int i = 0; i < oldArray.length(); i++) {
            JSONObject newCollection = handleOneOldCollection(oldArray.getJSONObject(i));
            newArray.put(newCollection);
        }

        try {
            FileWriter file = new FileWriter("res/newFormatCollections");

            file.write(newArray.toString(4));

            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject handleOneOldCollection(JSONObject oldCollection) {
        JSONObject newCollection = new JSONObject();

        if (hasValidValue(oldCollection, COLLECTION_NAME)) {
            newCollection.put(COLLECTION_NAME, oldCollection.get(COLLECTION_NAME));
        }

        if (hasValidValue(oldCollection, STARS)) {
            newCollection.put(STARS, oldCollection.get(STARS));
        }

        if (hasValidValue(oldCollection, IS_CHANGEABLE)) {
            newCollection.put(IS_CHANGEABLE, oldCollection.get(IS_CHANGEABLE));
        }

        if (hasValidValue(oldCollection, RELEASE_DATE)) {
            newCollection.put(RELEASE_DATE, oldCollection.get(RELEASE_DATE));
        }

        if (hasValidValue(oldCollection, OUT_PRINT_DATE)) {
            newCollection.put(OUT_PRINT_DATE, oldCollection.get(OUT_PRINT_DATE));
        }

        JSONArray oldCards = null;
        if (hasValidValue(oldCollection, CARDS)) {
            oldCards = oldCollection.getJSONArray(CARDS);
        }

        int startValue = -1;
        JSONArray levels = new JSONArray();

        JSONObject level = null;
        JSONArray levelCards = null;
        for (int i = 0; i < oldCards.length(); i++) {
            JSONObject oldCard = oldCards.getJSONObject(i);
            int v = oldCard.getInt(CARD_VALUE);

            if(v != startValue) {
                if (level != null) {
                    level.put(LEVEL_CARDS, levelCards);
                    levels.put(level);
                }

                level = new JSONObject();
                levelCards = new JSONArray();

                level.put(CARD_VALUE, v);

                if (hasValidValue(oldCard, SYNTHESIS_TIME) && oldCard.getInt(SYNTHESIS_TIME) != 0) {
                    level.put(SYNTHESIS_TIME, oldCard.getInt(SYNTHESIS_TIME));
                }
                startValue = v;
            }

            JSONObject newCard = new JSONObject();

            if (hasValidValue(oldCard, CARD_NAME)) {
                newCard.put(CARD_NAME, oldCard.getString(CARD_NAME));
            }

            if (hasValidValue(oldCard, SUB_CARDS_IDS) && oldCard.getJSONArray(SUB_CARDS_IDS).length() != 0) {
                JSONArray ids = oldCard.getJSONArray(SUB_CARDS_IDS);

                JSONArray newIds = new JSONArray();
                for (int j = 0; j < ids.length(); j++) {
                    int cardId = ids.getInt(j);

                    if (levels.length() != 0 ) {
                        for (int k = 0; k < levels.length(); k++) {
                            JSONObject lev = levels.getJSONObject(k);

                            int number = lev.getJSONArray(LEVEL_CARDS).length();

                            if (cardId - number < 0)  {
                                JSONObject group = new JSONObject();
                                group.put(SYNTHESIS_CARD_LEVEL, k);
                                group.put(SYNTHESIS_CARD_ID, cardId);
                                newIds.put(group);
                            } else {
                                cardId -= number;
                            }

                        }

                    }
                }
                newCard.put(SYNTHESIS_CARDS, newIds);
            }
            levelCards.put(newCard);
        }

        // Add last level to levels
        if (level != null) {
            level.put(LEVEL_CARDS, levelCards);
            levels.put(level);
        }


        newCollection.put(COLLECTION_LEVELS, levels);

        return newCollection;
    }

    public boolean hasValidValue(JSONObject obj, String key) {
        return obj.has(key) && (!obj.get(key).equals(JSONObject.NULL) && (obj.get(key) != (null)));
    }

    private AbstractCardsCollection createOneCollectionFromNewJSON(final ArrayList<AbstractCardsCollection> allCollections, final JSONObject oneCollectionJSON) {
        AbstractCardsCollection newCollection = null;

        String collectionName = oneCollectionJSON.getString(COLLECTION_NAME);

        if (isDuplicateCollection(allCollections, collectionName)) {
            return newCollection;
        }

        int starts = 0;

        if (hasValidValue(oneCollectionJSON, STARS)) {
            starts = oneCollectionJSON.getInt(STARS);
        }

        boolean isChangable = true;
        if (hasValidValue(oneCollectionJSON, IS_CHANGEABLE)) {
            isChangable = oneCollectionJSON.getBoolean(IS_CHANGEABLE);
        }

        Date releaseDate = null;
        try {
            releaseDate = MagicCardsGame.DATE_FORMAT.parse(oneCollectionJSON.getString(RELEASE_DATE));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date outPrintDate = null;
        String outPrintDateString = null;
        if (hasValidValue(oneCollectionJSON, OUT_PRINT_DATE)) {
            outPrintDateString = oneCollectionJSON.getString(OUT_PRINT_DATE);
        }

        if (outPrintDateString != null) {
            try {
                outPrintDate = MagicCardsGame.DATE_FORMAT.parse(oneCollectionJSON.getString(OUT_PRINT_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        newCollection = new AbstractCardsCollection(collectionName, starts, isChangable, releaseDate, outPrintDate);

        JSONArray levelsJSON = oneCollectionJSON.getJSONArray(COLLECTION_LEVELS);

        ArrayList<Level> levels = new ArrayList<Level>();
        // For every level. from 0 to N
        for (int i = 0; i < levelsJSON.length(); i++) {
            JSONObject leJSON = levelsJSON.getJSONObject(i);

            int value = 0;
            if (hasValidValue(leJSON, CARD_VALUE)) {
                value = leJSON.getInt(CARD_VALUE);
            }

            int time = 0;

            if (hasValidValue(leJSON, SYNTHESIS_TIME)) {
                time = leJSON.getInt(SYNTHESIS_TIME);
            }

            Level level = new Level(value, time, newCollection);

            levels.add(level);

            // For every card in this level.
            JSONArray cardsJSON = leJSON.getJSONArray(LEVEL_CARDS);
            ArrayList<AbstractCard> cardsInLevel = new ArrayList<AbstractCard>();
            for (int j = 0; j < cardsJSON.length(); j++) {
                JSONObject cardJSON = cardsJSON.getJSONObject(j);
                String cardName = cardJSON.getString(CARD_NAME);

                AbstractCard card = null;

                JSONArray sysCardsJSON = null;
                if (hasValidValue(cardJSON, SYNTHESIS_CARDS) && cardJSON.getJSONArray(SYNTHESIS_CARDS).length() != 0) {
                    sysCardsJSON = cardJSON.getJSONArray(SYNTHESIS_CARDS);
                    card = new SynthesisCard(j, cardName, level);

                    AbstractCard[] subCards = new AbstractCard[SYNTHESIS_NUMBER];

                    // For the subcards for this card
                    for (int k = 0; k < sysCardsJSON.length(); k++) {
                        JSONObject cardGoup = sysCardsJSON.getJSONObject(k);

                        int subCardLevel = cardGoup.getInt(SYNTHESIS_CARD_LEVEL);
                        int subCardId = cardGoup.getInt(SYNTHESIS_CARD_ID);

                        subCards[k] = levels.get(subCardLevel).getCards()[subCardId];
                    }
                    ((SynthesisCard)card).setUpSubCards(subCards);
                } else {
                    card = new BasicCard(j, cardName, level);
                }

                cardsInLevel.add(card);
            }

            AbstractCard[] cardsForLevl = new AbstractCard[cardsInLevel.size()];
            level.setCards(cardsForLevl);
        }

        Level[] levelsForCollection = new Level[levels.size()];

        newCollection.setUpLevels(levelsForCollection);

        return newCollection;
    }
}
