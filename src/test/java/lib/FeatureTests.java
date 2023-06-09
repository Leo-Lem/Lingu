package lib;

import java.io.*;

import backend.model.*;
import backend.services.implementations.*;
import backend.services.interfaces.*;
import cli.lib.*;

public abstract class FeatureTests extends InputOutputTests {
  @FunctionalInterface
  protected interface RunningFeature<T> {
    public T perform(Environment env) throws Throwable;
  }

  protected <T> T testFeature(RunningFeature<T> runFeature, Testing test) throws Throwable {
    return testFeature(10, runFeature, test);
  }

  protected <T> T testFeature(Integer timeout, RunningFeature<T> runFeature, Testing test) throws Throwable {
    Running<T> run = (writeOuput, readInput) -> {
      Learner learner = new Learner()
          .setName("Leo")
          .setLocale(Language.BASE)
          .setSource(Language.ENGLISH)
          .setTarget(Language.SPANISH);
      Localizer localizer = new JSONFileLocalizer(Language.BASE, "localizations");
      Translator translator = new JSONFileTranslator();
      WordGenerator wordGenerator = new JSONFileWordGenerator();
      Persistor<Learner> persistor = new InMemoryPersistor<>();
      Printer printer = new Printer(new PrintStream(writeOuput));
      Prompter prompter = new Prompter(readInput, printer, localizer);
      Environment env = new Environment(learner, localizer, translator, wordGenerator, persistor, printer, prompter);

      return runFeature.perform(env);
    };

    return super.test(timeout, run, test);
  }
}
