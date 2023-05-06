package gui.comps;

import java.awt.event.*;
import javax.swing.JButton;

public class ActionButton extends JButton {

  public ActionButton(String label, Action action) {
    setLabel(label);
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        action.execute();
      }
    });
  }

  public void setLabel(String label) {
    setText(label);
  }

  @FunctionalInterface
  public interface Action {
    void execute();
  }

}
