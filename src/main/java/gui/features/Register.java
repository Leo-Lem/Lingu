package gui.features;

import javax.swing.*;

import backend.model.Language;
import backend.services.interfaces.Localizer;
import gui.comps.*;

public class Register extends JPanel {

  private TitleLabel titleLabel = new TitleLabel("");
  private TextField nameField = new TextField("", "");
  private LanguagePicker targetPicker;
  private LanguagePicker sourcePicker;
  private ActionButton registerButton;

  public Register(Localizer localizer, Language[] targets, Language[] sources, ActionButton.Action register) {
    targetPicker = new LanguagePicker("", targets, Language.SPANISH);
    sourcePicker = new LanguagePicker("", sources, Language.ENGLISH);
    registerButton = new ActionButton("", register);
    setLocalizer(localizer);
    layOut();
  }

  public String readName() {
    return nameField.getInput();
  }

  public Language readTarget() {
    return targetPicker.getSelected();
  }

  public Language readSource() {
    return sourcePicker.getSelected();
  }

  public void setLocalizer(Localizer localizer) {
    titleLabel.setTitle(localizer.localize("WELCOME_MESSAGE"));
    nameField.setLabel(localizer.localize("YOUR_NAME"));
    targetPicker.setLabel(localizer.localize("PICK_TARGET_LANGUAGE"));
    sourcePicker.setLabel(localizer.localize("PICK_SOURCE_LANGUAGE"));
    registerButton.setLabel(localizer.localize("REGISTER"));
  }

  private void layOut() {
    GroupLayout layout = new GroupLayout(this);

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameField,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleLabel,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(targetPicker,
                            GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sourcePicker,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel,
                    GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameField,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(targetPicker,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sourcePicker,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerButton)
                .addContainerGap()));

    setLayout(layout);
  }

}
