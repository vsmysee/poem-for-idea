package com.intellij.ide.actions.poem;

import javax.swing.*;
import java.util.List;


public class PoemBuilder {

    public static final int HTML_SIZE = 12;

    public static JComponent build(List<String> poems, boolean pop) {

        if (isHtml(poems) && pop) {
            return HtmlPanel.buildHtmlPanel(poems);
        }

        return SimplePanel.build(poems, pop);

    }


    public static boolean isHtml(List<String> poems) {

        return poems.size() >= HTML_SIZE;

    }


}
