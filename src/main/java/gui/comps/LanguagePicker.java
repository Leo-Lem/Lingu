package gui.comps;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import java.awt.event.*;

import backend.model.Language;

public class LanguagePicker extends JPanel {

  private Language[] enabled;
  private Language selected;

  private ButtonGroup group = new ButtonGroup();
  private JRadioButton[] buttons;

  public LanguagePicker(String title, Language[] enabled, Language selected) {
    setLabel(title);
    setEnabled(enabled);
    setSelected(selected);
    setLayout(createLayout());
  }

  public Language getSelected() {
    return this.selected;
  }

  public void setSelected(Language selected) {
    this.selected = selected;
    buttons = createButtons();
  }

  public void setEnabled(Language[] enabled) {
    this.enabled = enabled;
  }

  public void setLabel(String title) {
    setBorder(BorderFactory.createTitledBorder(title));
  }

  private GroupLayout createLayout() {
    GroupLayout layout = new GroupLayout(this);

    ParallelGroup hButtons = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    SequentialGroup vButtons = layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE,
        Short.MAX_VALUE);

    for (JRadioButton button : buttons) {
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

    return layout;
  }

  private JRadioButton[] createButtons() {
    JRadioButton[] buttons = new JRadioButton[enabled.length];

    for (int i = 0; i < enabled.length; i++)
      buttons[i] = setupLanguageButton(enabled[i]);

    return buttons;
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
    button.setSelected(language.equals(selected));
    group.add(button);
    return button;
  }

}
