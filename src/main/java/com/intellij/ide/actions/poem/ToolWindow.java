package com.intellij.ide.actions.poem;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static com.intellij.ide.actions.poem.Setting.ACTION_GOTO_BACK;
import static com.intellij.ide.actions.poem.Setting.ACTION_GOTO_FORWARD;


public class ToolWindow implements ToolWindowFactory {

    public static JPanel holder;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull com.intellij.openapi.wm.ToolWindow toolWindow) {

        JPanel content = new JPanel(new BorderLayout());
        Box component = buildBox();
        content.add(component);

        final DefaultActionGroup toolbarActions = createToolbarActions();
        final ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("PoemToolBar", toolbarActions, true);
        JComponent tool = toolbar.getComponent();

        JPanel toolPanel = new JPanel();
        toolPanel.add(tool);

        content.add(toolPanel, BorderLayout.NORTH);

        toolWindow.getContentManager().addContent(ContentFactory.SERVICE.getInstance().createContent(content, "", false));

    }


    private DefaultActionGroup createToolbarActions() {
        final ActionManager actionManager = ActionManager.getInstance();
        final DefaultActionGroup group = new DefaultActionGroup();
        group.add(actionManager.getAction(ACTION_GOTO_BACK));
        group.add(actionManager.getAction(ACTION_GOTO_FORWARD));
        return group;
    }

    private static Box buildBox() {

        List<String> poems = Arrays.asList(PoemDB.random().split(";"));
        while (poems.size() > 12) {
            poems = Arrays.asList(PoemDB.random().split(";"));
        }


        Box content = Box.createVerticalBox();

        content.add(Box.createGlue());

        holder = new JPanel();
        JComponent poem = PoemBuilder.build(poems, false);
        holder.add(poem);
        content.add(holder);

        content.add(Box.createGlue());
        return content;

    }


}
