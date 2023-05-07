package gui.comps;

import javax.swing.*;

public class TextField extends JPanel {

  private JTextArea field = new JTextArea();

  public TextField(String input, String title) {
    field.setColumns(30);
    field.setRows(1);
    field.getDocument().putProperty("filterNewlines", Boolean.TRUE);
    setInput(input);
    add(field);
    setLabel(title);
  }

  public String getInput() {
    return field.getText();
  }

  public void setInput(String input) {
    field.setText(input);
  }

  @Override
  public void setEnabled(boolean isEnabled) {
    field.setEnabled(isEnabled);
  }

  public void setLabel(String title) {
    setBorder(BorderFactory.createTitledBorder(title));
  }

}
