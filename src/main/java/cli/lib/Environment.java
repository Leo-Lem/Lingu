package cli.lib;

import backend.model.*;
import backend.services.interfaces.*;
import backend.services.json.*;

public class Environment {
  private final Localizer localizer;
  private final Translator translator;
  private final WordGenerator wordGenerator;
  private final Persistor learnerPersistor;
  private final Printer printer;
  private final Prompter prompter;

  private Learner learner;

  public Environment() {
    this(new JSONFileLocalizer(), new Printer());
  }

  private Environment(Localizer localizer, Printer printer) {
    this(localizer, printer, new Prompter(printer, localizer));
  }

  private Environment(Localizer localizer, Printer printer, Prompter prompter) {
    this(
        null,
        localizer,
        new JSONFileTranslator(),
        new JSONFileWordGenerator(),
        new JSONFilePersistor("target/learner.json"),
        printer,
        prompter);
  }

  public Environment(
      Learner learner,
      Localizer localizer,
      Translator translator,
      WordGenerator wordGenerator,
      Persistor learnerPersistor,
      Printer printer,
      Prompter prompter) {
    this.learner = learner;

    this.localizer = localizer;
    this.translator = translator;
    this.wordGenerator = wordGenerator;
    this.learnerPersistor = learnerPersistor;
    this.printer = printer;
    this.prompter = prompter;

    if (learner == null) {
      setLearner(
          learnerPersistor
              .load(Learner.class)
              .orElse(new Learner().setLocale(localizer.getLanguage())));
    }
  }

  public Learner getLearner() {
    return learner;
  }

  public Localizer getLocalizer() {
    return localizer;
  }

  public Translator getTranslator() {
    return translator;
  }

  public WordGenerator getWordGenerator() {
    return wordGenerator;
  }

  public Printer getPrinter() {
    return printer;
  }

  public Prompter getPrompter() {
    return prompter;
  }

  public Message getLocalizedMessage() {
    return new Message(localizer);
  }

  public Learner setLearner(Learner learner) {
    if (learner.getLocale() != null)
      localizer.setLanguage(learner.getLocale());

    learnerPersistor.save(learner);

    return this.learner = learner;
  }

}
