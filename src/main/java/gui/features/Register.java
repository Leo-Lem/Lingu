package gui.features;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import backend.model.*;
import gui.lib.*;

public class Register extends JPanel {

  private final Environment env;
  private final NavigateTo navigateTo;

  private JLabel titleLabel;
  private JPanel namePanel;
  private JTextArea nameTextArea;
  private SelectLanguage selectTargetLanguage;
  private SelectLanguage selectSourceLanguage;
  private JButton registerButton;

  public Register(Environment env, NavigateTo navigateTo) {
    this.env = env;
    this.navigateTo = navigateTo;

    setupTitle();
    setupName();
    setupSelectTargetLanguage();
    setupSelectSourceLangauge();
    setupRegisterButton();
    setupLayout();
  }

  private void setupTitle() {
    titleLabel = new JLabel();
    titleLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setText("Welcome to Lingu!");
  }

  private void setupName() {
    nameTextArea = new JTextArea();
    nameTextArea.setColumns(40);
    nameTextArea.setRows(1);

    namePanel = new JPanel();
    namePanel.setBorder(BorderFactory.createTitledBorder("Your Name"));
    namePanel.setToolTipText("Please enter your name");
  }

  private void setupSelectTargetLanguage() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getSupportedLanguages()));
    languages.remove(Language.BASE);

    selectTargetLanguage = new SelectLanguage(languages.toArray(new Language[0]), Language.ENGLISH);
    selectTargetLanguage.setBorder(BorderFactory.createTitledBorder("Language to learn"));
    selectTargetLanguage.setToolTipText("Select the language you want to learn");
  }

  private void setupSelectSourceLangauge() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getSupportedLanguages()));
    languages.remove(Language.BASE);
    languages.remove(selectTargetLanguage.getSelected());

    selectSourceLanguage = new SelectLanguage(languages.toArray(new Language[0]), Language.ENGLISH);
    selectSourceLanguage.setBorder(BorderFactory.createTitledBorder("Translation language"));
    selectSourceLanguage.setToolTipText("Select the language you want to translate from");
  }

  private void setupRegisterButton() {
    registerButton = new JButton();
    registerButton.setText("Register");
    registerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        // TODO: validate inputs and save new learner
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
                    .addComponent(namePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(selectTargetLanguage, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectSourceLanguage, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(selectTargetLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectSourceLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerButton)
                .addContainerGap()));
  }

}
