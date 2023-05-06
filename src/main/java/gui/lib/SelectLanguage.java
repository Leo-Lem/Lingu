package gui.lib;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import java.awt.event.*;

import backend.model.Language;

public class SelectLanguage extends JPanel {

  private final Language[] enabled;
  private Language selected;

  private ButtonGroup group = new ButtonGroup();

  public SelectLanguage(Language[] enabled, Language selected) {
    this.enabled = enabled;
    this.selected = selected;

    setupLayout();
  }

  public Language getSelected() {
    return this.selected;
  }

  private void setupLayout() {
    GroupLayout layout = new GroupLayout(this);

    setLayout(layout);

    ParallelGroup hButtons = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup vButtons = layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE,
        Short.MAX_VALUE);

    for (Language language : this.enabled) {
      JRadioButton button = setupLanguageButton(language);
      hButtons
          .addComponent(button);

      vButtons
          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
          .addComponent(button);
    }

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hButtons)
                .addContainerGap(62, Short.MAX_VALUE)));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vButtons));
  }

  private JRadioButton setupLanguageButton(Language language) {
    JRadioButton button = new JRadioButton();
    button.setText(language.toString());
    button.setToolTipText("Select " + language.toString());
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        selected = language;
      }
    });
    group.add(button);
    return button;
  }

}
