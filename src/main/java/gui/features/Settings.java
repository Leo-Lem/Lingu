package gui.features;

import javax.swing.*;
import backend.model.Language;
import backend.services.interfaces.Localizer;
import gui.comps.*;

public class Settings extends JPanel {

  private TitleLabel titleLabel = new TitleLabel("");
  private TextField nameField;
  private LanguagePicker targetPicker;
  private LanguagePicker sourcePicker;
  private LanguagePicker localePicker;
  private ActionButton saveButton;

  public Settings(
      Localizer localizer, Language[] targets, Language[] sources, Language[] locales,
      ActionButton.Action save) {
    nameField = new TextField("", "");
    targetPicker = new LanguagePicker("", targets, Language.SPANISH);
    sourcePicker = new LanguagePicker("", sources, Language.ENGLISH);
    localePicker = new LanguagePicker("", locales, Language.ENGLISH);
    saveButton = new ActionButton("", save);
    update(localizer, "", Language.BASE, Language.BASE, Language.BASE);
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

  public Language readLocale() {
    return localePicker.getSelected();
  }

  public void update(Localizer localizer, String name, Language target, Language source, Language locale) {
    titleLabel.setTitle(localizer.localize("SETTINGS_TITLE"));
    nameField.setLabel(localizer.localize("CHOOSE_NEW_NAME"));
    nameField.setInput(name);
    targetPicker.setLabel(localizer.localize("PICK_TARGET_LANGUAGE"));
    targetPicker.setSelected(target);
    sourcePicker.setLabel(localizer.localize("PICK_SOURCE_LANGUAGE"));
    sourcePicker.setSelected(source);
    localePicker.setLabel(localizer.localize("PICK_INTERFACE_LANGUAGE"));
    localePicker.setSelected(locale);
    saveButton.setLabel(localizer.localize("SAVE_ACTION"));
  }

  private void layOut() {
    GroupLayout layout = new GroupLayout(this);

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.LEADING)
                    .addComponent(nameField,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(titleLabel,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(saveButton,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                            .addComponent(targetPicker,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE)
                            .addPreferredGap(
                                LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sourcePicker,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE)
                            .addPreferredGap(
                                LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(localePicker,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 84,
                    Short.MAX_VALUE)
                .addPreferredGap(
                    LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameField,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(
                    GroupLayout.Alignment.TRAILING)
                    .addComponent(targetPicker,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(sourcePicker,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(localePicker,
                        GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap()));

    setLayout(layout);
  }

}
