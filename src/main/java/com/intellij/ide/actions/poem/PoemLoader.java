package com.intellij.ide.actions.poem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PoemLoader {

    public static List<String> init() {
        List<String> db = new ArrayList<>();

        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = ToolWindow.class.getResourceAsStream("/icons/data.txt");
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                db.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return db;
    }
}
