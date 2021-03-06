package com.github.vsmysee.poemforidea;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PoemToolWindowFactory implements ToolWindowFactory {

    private static List<String> db = new ArrayList<>();

    public static JPanel holder;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        JPanel content = new JPanel(new BorderLayout());
        Box component = buildBox();
        content.add(component);

        final DefaultActionGroup toolbarActions = createToolbarActions();
        final ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("PoemToolBar", toolbarActions, true);
        JComponent tool = toolbar.getComponent();

        JPanel toolPanel = new JPanel();
        toolPanel.add(tool);

        content.add(toolPanel,BorderLayout.NORTH);

        toolWindow.getContentManager().addContent(ContentFactory.SERVICE.getInstance().createContent(content, "", false));
    }

    private static final String ACTION_GOTO_BACK = "POEM_BACK";
    private static final String ACTION_GOTO_FORWARD = "POEM_FORWARD";


    private DefaultActionGroup createToolbarActions() {
        final ActionManager actionManager = ActionManager.getInstance();
        final DefaultActionGroup group = new DefaultActionGroup();
        group.add(actionManager.getAction(ACTION_GOTO_BACK));
        group.add(actionManager.getAction(ACTION_GOTO_FORWARD));
        return group;
    }

    private static Box buildBox() {

        if (db.size() == 0) {
            init();
        }

        List<String> poems = Arrays.asList(random().split(";"));

        Box content = Box.createVerticalBox();

        content.add(Box.createGlue());

        holder = new JPanel();
        JComponent poem = PoemBuilder.build(poems).getPoem();
        holder.add(poem);
        content.add(holder);

        content.add(Box.createGlue());
        return content;

    }


    public static String random() {

        Random rand = new Random();
        int index = 0 + rand.nextInt(db.size());
        String poem = db.get(index);

        return poem;
    }


    private static void init() {
        try {

            String data = "";
            try (InputStream is = PoemToolWindowFactory.class.getResourceAsStream("/data.txt")) {
                try (InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                     BufferedReader reader = new BufferedReader(isr)) {
                    data = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                }
            }

            for (String item : data.split("\n")) {
                try {
                    if (!item.trim().equals("")) {
                        db.add(item);
                    }
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
