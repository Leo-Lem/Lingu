package gui.comps;

import java.awt.event.*;
import javax.swing.JButton;

public class ActionButton extends JButton {

  public ActionButton(String label, Action action) {
    setLabel(label);
    setAction(action);
  }

  public void setLabel(String label) {
    setText(label);
  }

  public void setAction(Action action) {
    for (ActionListener listener : getActionListeners())
      removeActionListener(listener);

    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        action.execute();
      }
    });
  }

  @FunctionalInterface
  public interface Action {
    void execute();
  }

}
