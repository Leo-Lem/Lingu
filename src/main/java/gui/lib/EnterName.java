package gui.lib;

import javax.swing.*;
import java.awt.event.*;

public class EnterName extends JPanel {

  private String name = "";

  private JTextArea field;

  public EnterName() {
    setupField();
    setup();
  }

  public String getName() {
    return this.name;
  }

  private void setupField() {
    field = new JTextArea();
    field.setColumns(30);
    field.setRows(1);
    field.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent evt) {
        name = field.getText();
      }
    });
    field.getDocument().putProperty("filterNewlines", Boolean.TRUE);
  }

  private void setup() {
    setBorder(BorderFactory.createTitledBorder("Your Name"));
    setToolTipText("Please enter your name");
    add(field);
  }

}
