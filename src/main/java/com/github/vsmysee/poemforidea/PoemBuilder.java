package com.github.vsmysee.poemforidea;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PoemBuilder {

    private static final int titleSize = 25;
    private static final int bodySize = 35;


    private static JComponent title(List<String> poems) {

        Box horizontalBox = Box.createHorizontalBox();

        horizontalBox.add(Box.createHorizontalGlue());

        JPanel title = new JPanel();

        JLabel comp = buildJLabel(poems.get(1), titleSize);
        comp.setText("<html><font color='blue'>" + comp.getText() + "</font></html>");

        horizontalBox.add(title);

        horizontalBox.add(Box.createHorizontalGlue());

        title.add(comp);
        return horizontalBox;
    }

    public static PoemPanel build(List<String> poems) {

        JComponent poemRoot = Box.createVerticalBox();

        PoemPanel poemPanel = new PoemPanel(poemRoot);

        poemRoot.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        poemRoot.add(title(poems));

        poemRoot.add(new JSeparator());
        poemRoot.add(Box.createVerticalStrut(10));

        int index = 2;

        while (index < poems.size()) {

            JPanel panel = new JPanel();

            String left = poems.get(index).trim();
            panel.add(buildJLabel(left, bodySize));

            index++;

            poemPanel.add(panel);

        }

        Box poemContent = Box.createVerticalBox();
        for (JPanel poemLabel : poemPanel.getPoemLabels()) {
            poemContent.add(poemLabel);
        }


        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(new CirclePanel(poems.get(0), 20, 25));
        poemContent.add(bottom);

        poemPanel.add(bottom);

        poemRoot.add(poemContent);

        return poemPanel;
    }

    private static JLabel buildJLabel(String text, int size) {
        JLabel item = new JLabel(text);
        item.setFont(new Font(Setting.FONT, Font.BOLD, size));
        return item;
    }


}
