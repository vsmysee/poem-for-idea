package com.github.vsmysee.poemforidea;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PoemToolWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        toolWindow.getContentManager().addContent(ContentFactory.SERVICE.getInstance().createContent(buildBox(), "", false));
    }

    private static Box buildBox() {

        List<String> db = new ArrayList<>();

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

        List<String> poems = Arrays.asList(db.get(0).split(";"));

        Box content = Box.createVerticalBox();

        content.add(Box.createGlue());

        JPanel holder = new JPanel();
        holder.add(PoemBuilder.build(poems).getPoem());
        content.add(holder);

        content.add(Box.createGlue());
        return content;

    }

}
