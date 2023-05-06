package gui.features;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import backend.model.Language;
import gui.lib.*;

public class Settings extends JPanel {

  private final Environment env;
  private final NavigateTo navigateTo;

  private JLabel title;
  private JPanel namePanel;
  private JTextArea nameField;
  private SelectLanguage selectTargetLanguage;
  private SelectLanguage selectSourceLanguage;
  private SelectLanguage selectInterfaceLanguage;
  private JButton save;

  public Settings(Environment env, NavigateTo navigateTo) {
    this.env = env;
    this.navigateTo = navigateTo;

    setupTitle();
    setupName();
    setupTargetLanguage();
    setupSourceLanguage();
    setupInterfaceLanguage();
    setupSave();
    setupLayout();
  }

  private void setupTitle() {
    title = new JLabel();
    title.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setText("Adjust your settings");
  }

  private void setupName() {
    nameField = new JTextArea();
    nameField.setColumns(30);
    nameField.setRows(1);

    namePanel = new JPanel();
    namePanel.setBorder(BorderFactory.createTitledBorder("Your Name"));
    namePanel.setToolTipText("Please enter your name");
    namePanel.add(nameField);
  }

  private void setupTargetLanguage() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getSupportedLanguages()));
    languages.remove(Language.BASE);

    selectTargetLanguage = new SelectLanguage(languages.toArray(new Language[0]), env.getLearner().getTarget());
    selectTargetLanguage.setBorder(BorderFactory.createTitledBorder("Language to learn"));
    selectTargetLanguage.setToolTipText("Select the language you want to learn");
  }

  private void setupSourceLanguage() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getSupportedLanguages()));
    languages.remove(Language.BASE);
    languages.remove(selectTargetLanguage.getSelected());

    selectSourceLanguage = new SelectLanguage(languages.toArray(new Language[0]), Language.ENGLISH);
    selectSourceLanguage.setBorder(BorderFactory.createTitledBorder("Translation language"));
    selectSourceLanguage.setToolTipText("Select the language you want to translate from");
  }

  private void setupInterfaceLanguage() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(env.getLocalizer().getSupportedLanguages()));
    languages.remove(Language.BASE);

    selectInterfaceLanguage = new SelectLanguage(languages.toArray(new Language[0]), Language.ENGLISH);
    selectInterfaceLanguage.setBorder(BorderFactory.createTitledBorder("Interface language"));
    selectInterfaceLanguage.setToolTipText("Select the interface language");
  }

  private void setupSave() {
    save = new JButton();
    save.setText("Save");
    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        // TODO: save learner
        navigateTo.execute("menu");
      }
    });
  }

  private void setupLayout() {
    GroupLayout layout = new GroupLayout(this);

    setLayout(layout);

    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(namePanel,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(save,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(selectTargetLanguage,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectSourceLanguage,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectInterfaceLanguage,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namePanel,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(selectTargetLanguage,
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectSourceLanguage,
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectInterfaceLanguage,
                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save)
                .addContainerGap()));
  }

}
