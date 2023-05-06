package gui;

import gui.features.*;
import gui.features.Menu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import backend.model.Language;
import backend.model.Learner;
import backend.services.implementations.*;
import backend.services.interfaces.*;

public class Lingu extends JFrame {

  public static void main(String[] args) {
    setTheme();
    (new Lingu()).run();
  }

  private Learner learner;

  private final Localizer localizer;
  private final Translator translator;
  private final WordGenerator wordGenerator;
  private final Persistor<Learner> persistor;

  private Menu menu;
  private Learn learn;
  private Settings settings;
  private Register register;

  private final CardLayout cards = new CardLayout();;

  public Lingu() {
    localizer = new JSONFileLocalizer("localizations-gui");
    translator = new JSONFileTranslator();
    wordGenerator = new JSONFileWordGenerator();
    persistor = new DerbyDBPersistor();

    if (learner == null)
      setLearner(persistor.load().orElse(new Learner().setLocale(localizer.getLanguage())));

    menu = new Menu(localizer, () -> navigateTo("learn"), () -> navigateTo("settings"));
    settings = new Settings(localizer, getTargetLanguages(), getSourceLanguages(), getLocales(), () -> saveSettings());
    learn = new Learn(localizer, () -> submitContinue(), () -> navigateTo("menu"));
    register = new Register(localizer, getTargetLanguages(), getSourceLanguages(), () -> register());

    setTitle("Lingu");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    Container pane = getContentPane();
    pane.setLayout(cards);
    pane.add("menu", menu);
    pane.add("learn", learn);
    pane.add("settings", settings);
    pane.add("register", register);
  }

  public void run() {
    if (learner.getName() == null
        || learner.getTarget() == null
        || learner.getSource() == null)
      navigateTo("register");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);

    setVisible(true);
  }

  private Language[] getTargetLanguages() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(translator.getSupportedLanguages()));
    languages.remove(Language.BASE);
    return languages.toArray(new Language[0]);
  }

  private Language[] getSourceLanguages() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(translator.getSupportedLanguages()));
    languages.remove(Language.BASE);
    languages.remove(learner.getTarget());
    return languages.toArray(new Language[0]);
  }

  private Language[] getLocales() {
    java.util.List<Language> languages = new ArrayList<>(Arrays.asList(localizer.getSupportedLanguages()));
    languages.remove(Language.BASE);
    return languages.toArray(new Language[0]);
  }

  private void register() {
    if (register.readName().length() < 2) {
      register.setBackground(Color.RED);
      return;
    }

    setLearner(learner
        .setName(register.readName())
        .setTarget(register.readTarget())
        .setSource(register.readSource()));

    navigateTo("menu");
  }

  private void saveSettings() {
    setLearner(learner
        .setName(settings.readName())
        .setTarget(settings.readTarget())
        .setSource(settings.readSource())
        .setLocale(settings.readLocale()));

    navigateTo("menu");
  }

  private void submitContinue() {
    // TODO: implement
  }

  private Learner setLearner(Learner learner) {
    if (learner.getLocale() != null)
      localizer.setLanguage(learner.getLocale());

    if (menu != null)
      menu.update(localizer, learner.getName(), learner.getTarget());
    if (settings != null)
      settings.update(localizer, learner.getName(), learner.getTarget(), learner.getSource(), learner.getLocale());
    if (learn != null)
      learn.setLocalizer(localizer);

    // TODO: persist learner

    return this.learner = learner;
  }

  private void navigateTo(String identifier) {
    cards.show(getContentPane(), identifier);
  }

  private static void setTheme() {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
