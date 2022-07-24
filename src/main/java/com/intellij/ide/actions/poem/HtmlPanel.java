package com.intellij.ide.actions.poem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HtmlPanel {

    public static JComponent buildHtmlPanel(List<String> poems) {
        Box horizontalBox = Box.createHorizontalBox();

        String title = poems.get(2);

        for (int i = poems.size() - 1; i > 2; i--) {
            horizontalBox.add(convertToHtml(poems.get(i), false));
        }

        Box box = Box.createVerticalBox();
        CirclePanel song = new CirclePanel(poems.get(0));
        song.setMaximumSize(new Dimension(16, 16));
        box.add(buildAuthor("   "));
        box.add(song);
        box.add(buildAuthor(" "));
        box.add(buildAuthor(poems.get(1)));


        horizontalBox.add(box);
        horizontalBox.add(Box.createHorizontalStrut(15));


        horizontalBox.add(convertToHtml(title, true));

        horizontalBox.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        return horizontalBox;
    }


    private static JLabel buildAuthor(String poem) {
        String[] split = poem.split("");
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<font color='grey'>");

        for (String item : split) {
            sb.append(item);
            sb.append("<br/>");
        }

        sb.append("</font>");
        sb.append("</html>");
        JLabel label = buildJLabel(sb.toString(), 25);
        return label;
    }

    private static JLabel convertToHtml(String poem, boolean title) {
        String[] split = poem.split("");
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        if (title) {
            sb.append("<font color='blue'>");
        }

        for (String item : split) {
            sb.append(item);
            sb.append("<br/>");
        }

        if (title) {
            sb.append("</font>");
        }
        sb.append("</html>");
        JLabel label = buildJLabel(sb.toString(), title ? 25 : 35);
        return label;
    }


    private static JLabel buildJLabel(String text, int size) {
        JLabel item = new JLabel(text);
        item.setFont(new Font(Setting.FONT, Font.BOLD, size));
        return item;
    }


}
