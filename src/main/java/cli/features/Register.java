package cli.features;

import java.util.concurrent.CancellationException;

import backend.model.*;
import cli.lib.Environment;

public class Register {
  private final Environment env;

  public Register(Environment env) {
    this.env = env;
  }

  public void run() throws InterruptedException {
    try {
      Learner learner = env.getLearner();

      if (learner.getName() == null)
        env.setLearner(learner.setName(getInitialName()));

      if (learner.getTarget() == null)
        env.setLearner(learner.setTarget(getInitialTarget()));

      if (learner.getSource() == null)
        env.setLearner(learner.setSource(getInitialSource()));
    } catch (CancellationException e) {
      throw new InterruptedException("User did not finish registering. Quitting…");
    }
  }

  private String getInitialName() throws CancellationException {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("HELLO_MESSAGE").asLingu().build());

    String name = env.getPrompter().readNextString("?");

    env.getPrinter().println(env.getLocalizedMessage()
        .set("WELCOME_MESSAGE", name).asLingu().build());

    return name;
  }

  private Language getInitialTarget() throws CancellationException {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("PICK_TARGET").asLingu().build());

    Language language = env.getPrompter().readLanguage(
        env.getLearner().getName(), env.getTranslator().getSupportedLanguages(), Language.BASE);

    env.getPrinter().println(env.getLocalizedMessage()
        .set("NEW_TARGET", language).asLingu().build());

    return language;
  }

  private Language getInitialSource() throws CancellationException {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("PICK_SOURCE").asLingu().build());

    Language language = env.getPrompter().readLanguage(
        env.getLearner().getName(), env.getLocalizer().getSupportedLanguages(), Language.BASE);

    env.getLocalizer().setLanguage(language);

    env.getPrinter().println(env.getLocalizedMessage()
        .set("NEW_SOURCE", language).asLingu().build());

    return language;
  }

}
