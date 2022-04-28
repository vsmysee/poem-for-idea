package com.intellij.ide.actions.poem;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class ZoomDialog extends JDialog {

  private JComponent poem;

  private String current;

  private static ZoomDialog instance;

  public static ZoomDialog getInstance(String random) {
    if (instance == null) {
      instance = new ZoomDialog(random);
    }
    return instance;
  }


  public ZoomDialog(String random) {

    current = random;

    List<String> poems = Arrays.asList(random.split(";"));

    setDefaultCloseOperation(2);

    ActionListener closeAction = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ZoomDialog.this.dispose();
        instance = null;
      }
    };

    getRootPane().registerKeyboardAction(closeAction, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        instance = null;
      }
    });


    setContent(poems);

    setVisible(true);


    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          ZoomDialog.this.dispose();
        }
      }
    });

    getRootPane().getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "nextPoem");
    getRootPane().getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "nextPoem");


    getRootPane().getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "lastPoem");
    getRootPane().getInputMap().put(KeyStroke.getKeyStroke("UP"), "lastPoem");

    getRootPane().getActionMap().put("nextPoem", new AbstractAction() {
      public void actionPerformed(ActionEvent e) {

        String random = PoemToolWindowFactory.random();
        current = random;

        List<String> poems = Arrays.asList(random.split(";"));
        refresh(poems);

        BackAction.add(random);

      }
    });

    getRootPane().getActionMap().put("lastPoem", new AbstractAction() {
      public void actionPerformed(ActionEvent e) {

        String random = PoemToolWindowFactory.random();

        try {
          random = BackAction.pop();
          if (random.equals(current)) {
            random = BackAction.pop();
          }
        }
        catch (Exception ex) {
        }

        List<String> poems = Arrays.asList(random.split(";"));
        refresh(poems);

      }
    });

  }

  private void setContent(List<String> poems) {
    poem = PoemBuilder.build(poems, true);
    add(poem);
    pack();

    if (getWidth() < 600) {
      setSize(600, getHeight());
    }

    setLocationRelativeTo(null);

  }


  public void refresh(List<String> poems) {

    remove(poem);

    setContent(poems);
  }


}
