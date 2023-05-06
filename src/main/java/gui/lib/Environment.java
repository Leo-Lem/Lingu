package gui.lib;

import backend.model.*;
import backend.services.implementations.*;
import backend.services.interfaces.*;

public class Environment {
  private final Localizer localizer;
  private final Translator translator;
  private final WordGenerator wordGenerator;
  private final Persistor<Learner> learnerPersistor;

  private Learner learner;

  public Environment() {
    this(
        null,
        new JSONFileLocalizer(),
        new JSONFileTranslator(),
        new JSONFileWordGenerator(),
        new DerbyDBPersistor());
  }

  public Environment(
      Learner learner,
      Localizer localizer, Translator translator, WordGenerator wordGenerator, Persistor<Learner> learnerPersistor) {
    this.learner = learner;

    this.localizer = localizer;
    this.translator = translator;
    this.wordGenerator = wordGenerator;
    this.learnerPersistor = learnerPersistor;

    if (learner == null) {
      setLearner(
          learnerPersistor.load().orElse(new Learner().setLocale(localizer.getLanguage())));
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

  public Learner setLearner() {
    return setLearner(learner);
  }

  public Learner setLearner(Learner learner) {
    if (learner.getLocale() != null)
      localizer.setLanguage(learner.getLocale());

    if (learner.getName() != null && learner.getSource() != null && learner.getTarget() != null)
      learnerPersistor.save(learner);

    return this.learner = learner;
  }

}
