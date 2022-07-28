package com.intellij.ide.actions.poem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoemDB {

    private static List<String> db = new ArrayList<String>();


    public static String random() {

        if (db.size() == 0) {
            db.addAll(PoemLoader.init());
        }

        Random rand = new Random();
        int index = rand.nextInt(db.size());
        return db.get(index);

    }

}
