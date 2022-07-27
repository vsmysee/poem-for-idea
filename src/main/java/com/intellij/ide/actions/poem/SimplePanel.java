package com.intellij.ide.actions.poem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.intellij.ide.actions.poem.Setting.*;

public class SimplePanel {

    public static JComponent build(List<String> poems, boolean pop) {

        JComponent poemRoot = Box.createVerticalBox();

        poemRoot.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        poemRoot.add(title(poems));

        poemRoot.add(new JSeparator());
        poemRoot.add(Box.createVerticalStrut(10));

        List<JPanel> poemPanel = new ArrayList<>();

        int index = 3;

        if (pop) {

            while (index < poems.size()) {

                JPanel panel = new JPanel();

                String left = poems.get(index).trim();
                panel.add(buildJLabel(left, BODY_SIZE));

                if ((index + 1) < poems.size()) {

                    String right = poems.get(index + 1).trim();

                    if (left.length() == right.length()) {
                        panel.add(buildJLabel("，", BODY_SIZE));
                        panel.add(buildJLabel(right, BODY_SIZE));
                        index++;
                    }

                }

                index++;

                poemPanel.add(panel);

            }


        } else {


            while (index < poems.size()) {

                JPanel panel = new JPanel();

                String item = poems.get(index).trim();
                panel.add(buildJLabel(item, BODY_SIZE));

                index++;

                poemPanel.add(panel);

            }

        }


        Box poemContent = Box.createVerticalBox();
        for (JPanel poemLabel : poemPanel) {
            poemContent.add(poemLabel);
        }


        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(new CirclePanel(poems.get(0), 20, 25));
        poemContent.add(bottom);


        poemRoot.add(poemContent);

        return poemRoot;

    }


    private static JComponent title(List<String> poems) {

        Box horizontalBox = Box.createHorizontalBox();

        horizontalBox.add(Box.createHorizontalGlue());

        JPanel title = new JPanel();

        String author = poems.get(1);
        JLabel comp = buildJLabel("", TITLE_SIZE);
        comp.setText("<html><font color=rgb(72,72,72)>" + author + "</font><font color='blue'>《" + poems.get(2) + "》</font></html>");

        horizontalBox.add(title);

        horizontalBox.add(Box.createHorizontalGlue());

        title.add(comp);
        return horizontalBox;
    }


    private static JLabel buildJLabel(String text, int size) {
        JLabel item = new JLabel(text);
        item.setFont(new Font(Setting.FONT, Font.BOLD, size));
        return item;
    }

}
