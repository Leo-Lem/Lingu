package cli.features;

import java.util.concurrent.CancellationException;

import backend.model.*;
import cli.lib.Environment;

public class Settings {
  private final Environment env;

  public Settings(Environment env) {
    this.env = env;
  }

  public Language changeTarget() {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("PICK_TARGET").asLingu().build());

    Language language = env.getPrompter().readLanguage(
        env.getLearner().getName(),
        env.getTranslator().getSupportedLanguages(),
        Language.BASE, env.getLearner().getTarget());

    env.getPrinter().println(env.getLocalizedMessage()
        .set("NEW_TARGET", language).asLingu().build());

    return language;
  }

  public Language changeSource() {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("PICK_SOURCE").asLingu().build());

    Language language = env.getPrompter().readLanguage(
        env.getLearner().getName(),
        env.getTranslator().getSupportedLanguages(),
        Language.BASE, env.getLearner().getSource());

    env.getPrinter().println(env.getLocalizedMessage()
        .set("NEW_SOURCE", language).asLingu().build());

    return language;
  }

  public Language changeLocale() throws CancellationException {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("PICK_LOCALE").asLingu().build());

    Language language = env.getPrompter().readLanguage(
        env.getLearner().getName(),
        env.getLocalizer().getSupportedLanguages(),
        Language.BASE, env.getLearner().getLocale());

    env.getLocalizer().setLanguage(language);

    env.getPrinter().println(env.getLocalizedMessage()
        .set("NEW_LOCALE", language).asLingu().build());

    return language;
  }

  public String changeName() {
    env.getPrinter().println(env.getLocalizedMessage()
        .set("CHOOSE_NAME").asLingu().build());

    String name = env.getPrompter().readNextString("?");

    env.getPrinter().println(env.getLocalizedMessage()
        .set("NEW_NAME", env.getLearner().getName()).asLingu().build());

    return name;
  }

}
