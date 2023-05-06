package gui.features;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import backend.model.Language;
import gui.lib.*;

public class Register extends JPanel {

  private final Environment env;
  private final NavigateTo navigateTo;

  private JLabel title;
  private JPanel enterName;
  private SelectLanguage selectTargetLanguage;
  private SelectLanguage selectSourceLanguage;
  private JButton register;

  public Register(Environment env, NavigateTo navigateTo) {
    this.env = env;
    this.navigateTo = navigateTo;

    setupTitle();
    setupEnterName();
    setupSelectTargetLanguage();
    setupSelectSourceLangauge();
    setupRegisterButton();
    setupLayout();
  }

  private void setupTitle() {
    title = new JLabel();
    title.setFont(new java.awt.Font("Helvetica Neue", 0, 36));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setText("Welcome to Lingu!");
  }

  private void setupEnterName() {
    enterName = new EnterName();
  }

  private void setupSelectTargetLanguage() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getSupportedLanguages()));
    languages.remove(Language.BASE);

    selectTargetLanguage = new SelectLanguage(languages.toArray(new Language[0]), Language.GERMAN);
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
    register = new JButton();
    register.setText("Register");
    register.addActionListener(new ActionListener() {
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
                    .addComponent(enterName,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(register,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectTargetLanguage,
                            GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectSourceLanguage,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title,
                    GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enterName,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(selectTargetLanguage,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectSourceLanguage,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(register)
                .addContainerGap()));
  }

}
