package gui.comps;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TitleLabel extends JLabel {
  public TitleLabel(String title) {
    setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    setHorizontalAlignment(SwingConstants.CENTER);
  }

  public void setTitle(String title) {
    setText(title);
  }
}
