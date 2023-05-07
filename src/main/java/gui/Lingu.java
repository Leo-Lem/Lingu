package gui;

import gui.features.*;
import gui.features.Menu;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import javax.swing.*;

import backend.model.Language;
import backend.model.Learner;
import backend.model.Vocab;
import backend.model.Vocabulary;
import backend.services.implementations.*;
import backend.services.interfaces.*;

public class Lingu extends JFrame {

  public static void main(String[] args) {
    setTheme();
    (new Lingu()).run();
  }

  private Learner learner;
  private Iterator<Vocab> vocabIterator;
  private Optional<Vocab> currentVocab = Optional.empty();

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

    learner = persistor.load().orElse(new Learner().setLocale(localizer.getLanguage()));

    menu = new Menu(localizer, () -> navigateTo("learn"), () -> navigateTo("settings"));
    settings = new Settings(localizer, getTargetLanguages(), getSourceLanguages(), getLocales(), () -> saveSettings());
    learn = new Learn(localizer, () -> submit(), () -> next(), () -> navigateTo("menu"));
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
    update();

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

    learner
        .setName(register.readName())
        .setTarget(register.readTarget())
        .setSource(register.readSource());

    learner.setVocabulary(generateVocabulary(10));
    vocabIterator = learner.getVocabulary().iterator();
    currentVocab = Optional.of(vocabIterator.next());

    update();

    navigateTo("menu");
  }

  private void saveSettings() {
    learner
        .setName(settings.readName())
        .setTarget(settings.readTarget())
        .setSource(settings.readSource())
        .setLocale(settings.readLocale());

    update();

    navigateTo("menu");
  }

  private void submit() {
    String localizedResult = getAnswerFeedback(learn.readAnswer(), currentVocab.get());
    learn.update(localizer, currentVocab.get().getWord(), learner.getTarget(), localizedResult, true);
    persistor.save(learner);
  }

  private void next() {
    if (!vocabIterator.hasNext()) {
      navigateTo("menu");
      return;
    }

    currentVocab = Optional.of(vocabIterator.next());
    learn.clearAnswer();
    learn.update(localizer, currentVocab.get().getWord(), learner.getTarget(), "", false);
  }

  private void update() {
    if (learner.getLocale() != null)
      localizer.setLanguage(learner.getLocale());

    if (menu != null && learner.getTarget() != null)
      menu.update(localizer, learner.getName(), learner.getTarget());
    if (settings != null && learner.getTarget() != null && learner.getSource() != null && learner.getLocale() != null)
      settings.update(localizer, learner.getName(), learner.getTarget(), learner.getSource(), learner.getLocale());
    if (learn != null && learner.getTarget() != null && learner.getSource() != null)
      learn.update(
          localizer, currentVocab.isPresent() ? currentVocab.get().getWord() : "", learner.getTarget(), "", false);

    if (learner.getName() != null && learner.getTarget() != null && learner.getSource() != null)
      persistor.save(learner);
  }

  private String getAnswerFeedback(String answer, Vocab vocab) {
    Optional<String> translation = translator.translate(vocab.getWord(), vocab.getSource(), vocab.getTarget());

    if (!translation.isPresent())
      throw new RuntimeException("Failed to get translation");

    String localizedResult;

    if (answer.equalsIgnoreCase(translation.get())) {
      vocab.advanceStage();
      localizedResult = localizer.localize("ANSWER_IS_CORRECT",
          vocab.getNextUp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")));
    } else {
      vocab.resetStage();
      localizedResult = localizer.localize("ANSWER_IS_WRONG", translation.get());
    }

    learner.getVocabulary().add(vocab);

    return localizedResult;
  }

  private Vocabulary generateVocabulary(Integer count) {
    Vocabulary vocabulary = new Vocabulary();

    String[] ids = wordGenerator.generateWords(Language.BASE, count);

    for (String id : ids) {
      Optional<String> word = translator.translate(id, Language.BASE, learner.getSource());

      if (word.isPresent()) {
        Vocab vocab = new Vocab(word.get(), learner.getSource(), learner.getTarget());

        if (!vocabulary.contains(vocab))
          vocabulary.add(vocab);
      }
    }

    return vocabulary;
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
