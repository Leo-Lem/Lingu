package lingu;

import lingu.model.Learner;
import lingu.services.*;
import lingu.services.interfaces.*;

public class Environment {
  private final Localizer localizer;
  private final Translator translator;
  private final Printer printer;
  private final Prompter prompter;

  private Learner learner;

  public Environment(Learner learner, Localizer localizer, Translator translator) {
    this(learner, localizer, translator, new Printer(localizer));
  }

  private Environment(Learner learner, Localizer localizer, Translator translator, Printer printer) {
    this(learner, localizer, translator, printer, new Prompter(printer));
  }

  public Environment(Learner learner, Localizer localizer, Translator translator, Printer printer, Prompter prompter) {
    this.learner = learner;

    this.localizer = localizer;
    this.translator = translator;
    this.printer = printer;
    this.prompter = prompter;
  }

  public Learner getLearner() {
    return learner;
  }

  public Printer getPrinter() {
    return printer;
  }

  public Prompter getPrompter() {
    return prompter;
  }

  public Localizer getLocalizer() {
    return localizer;
  }

  public Translator getTranslator() {
    return translator;
  }

  public Learner setLearner(Learner learner) {
    this.localizer
        .setLanguage(learner.getInterfaceLanguage());

    this.translator
        .setSource(learner.getInterfaceLanguage())
        .setTarget(learner.getLanguage());

    return this.learner = learner;
  }

}
