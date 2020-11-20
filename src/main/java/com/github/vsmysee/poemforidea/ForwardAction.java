package com.github.vsmysee.poemforidea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class ForwardAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        JPanel holder = PoemToolWindowFactory.holder;
        holder.remove(0);
        List<String> poems = Arrays.asList(PoemToolWindowFactory.random().split(";"));

        holder.add(PoemBuilder.build(poems).getPoem());
        holder.getParent().repaint();

    }
}
