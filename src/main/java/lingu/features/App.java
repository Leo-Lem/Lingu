package lingu.features;

import java.util.concurrent.CancellationException;

import lingu.Environment;
import lingu.model.*;
import lingu.services.*;

public class App {

  private final Environment env;
  private final JSONFileHandler jsonFileHandler;

  public App() {
    this(
        new Environment(
            null, new JSONFileLocalizer(Language.ENGLISH), new JSONFileTranslator(Language.ENGLISH, Language.SPANISH)),
        new JSONFileHandler());
  }

  public App(Environment env, JSONFileHandler jsonFileHandler) {
    this.env = env;
    this.jsonFileHandler = jsonFileHandler;
  }

  public void start() {
    Printer printer = env.getPrinter();

    try {
      setLearner(this.jsonFileHandler.load("target/learner.json", Learner.class).orElse(null));

      if (env.getLearner() == null)
        setLearner(new Register(env).run());

      while (true) {
        try {
          printer.printEmptyLine();
          int selection = new Menu(env).select();
          printer.printEmptyLine();

          if (selection == 1)
            setLearner(new Learn(env).run());
          else {
            Settings settings = new Settings(env);
            switch (selection) {
              case 2:
                setLearner(env.getLearner().setInterfaceLanguage(settings.changeInterfaceLanguage()));
                break;
              case 3:
                setLearner(env.getLearner().setLanguage(settings.changeLanguage()));
                break;
              case 4:
                setLearner(env.getLearner().setName(settings.changeName()));
                break;
            }
          }
        } catch (CancellationException e) {
          printer.printlnAsLingu(env.getLocalizer().localize("CANCEL_MESSAGE"));
          printer.printEmptyLine();
        }
      }
    } catch (InterruptedException e) {
      if (env.getLearner() != null) {
        printer.printlnAsLingu(
            env.getLocalizer().localizeWithStringArgument("GOODBYE_MESSAGE", env.getLearner().getName()));
        printer.printEmptyLine();
      }
    }
  }

  private Learner setLearner(Learner learner) {
    this.env.setLearner(learner);

    this.jsonFileHandler
        .save(learner, "target/learner.json");

    return learner;
  }

}