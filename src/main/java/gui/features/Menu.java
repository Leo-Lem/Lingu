package gui.features;

import javax.swing.*;

import backend.model.Language;
import backend.services.interfaces.Localizer;

import gui.comps.*;

public class Menu extends JPanel {

  private TitleLabel titleLabel;
  private ActionButton learnButton;
  private ActionButton settingsButton;

  public Menu(Localizer localizer, ActionButton.Action learn, ActionButton.Action toSettings) {
    titleLabel = new TitleLabel("");
    learnButton = new ActionButton("", learn);
    settingsButton = new ActionButton("", toSettings);
    update(localizer, "", Language.ENGLISH);
    layOut();
  }

  public void update(Localizer localizer, String name, Language target) {
    titleLabel.setTitle(localizer.localize("HELLO_MESSAGE", name));
    learnButton.setLabel(localizer.localize("LEARN", localizer.localize(target)));
    settingsButton.setLabel(localizer.localize("SETTINGS_TITLE"));
  }

  private void layOut() {
    GroupLayout layout = new GroupLayout(this);

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel,
                        GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(learnButton,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                            Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsButton,
                            GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel,
                    GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(learnButton,
                        GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(settingsButton,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));

    setLayout(layout);
  }

}
