package lingu;

import java.util.concurrent.CancellationException;

import lingu.features.*;
import lingu.model.*;
import lingu.services.*;
import lingu.services.interfaces.Localizer;
import lingu.services.interfaces.Translator;

public class App {
  private Localizer localizer;
  private Translator translator;
  private JSONFileHandler jsonHandler;
  private Printer printer;
  private Prompter prompter;

  private Learner learner;

  public App() {
    this(new JSONFileHandler());
  }

  public App(JSONFileHandler jsonHandler) {
    this.jsonHandler = jsonHandler;

    this.localizer = new JSONFileLocalizer(Language.ENGLISH);
    this.translator = new JSONFileTranslator(Language.ENGLISH, Language.SPANISH);
    this.printer = new Printer(localizer);
    this.prompter = new Prompter(printer);
  }

  public void start() {
    try {
      setLearner(this.jsonHandler.load("target/learner.json", Learner.class).orElse(null));

      if (learner == null)
        setLearner(new Register(printer, prompter, localizer, translator).run());

      while (true) {
        try {
          int selection = selectActivity();

          if (selection == 1)
            setLearner(new Learn(learner, printer, prompter, localizer, translator).run());
          else {
            Settings settings = new Settings(printer, prompter, localizer, translator);
            switch (selection) {
              case 2:
                printer.printAsPrompt(localizer.localize("PICK_INTERFACE_LANGUAGE"), true);
                setLearner(learner.setInterfaceLanguage(settings.changeInterfaceLanguage()));
                break;
              case 3:
                setLearner(learner.setLanguage(settings.changeLanguage()));
                break;
              case 4:
                setLearner(learner.setName(settings.changeName()));
                break;
            }
          }
        } catch (CancellationException e) {
          printer.printEmptyLine();
          printer.printAsLingu(localizer.localize("CANCEL_MESSAGE"));
          printer.printEmptyLine();
        }
      }
    } catch (InterruptedException e) {
      if (learner != null) {
        printer.printEmptyLine();
        printer.printAsLingu(localizer.localizeWithStringArgument("GOODBYE_MESSAGE", learner.getName()));
        printer.printEmptyLine();
      }
    }
  }

  private Integer selectActivity() {
    printer.printHeader("MENU");

    printer.printAsLingu(String.format(localizer.localize("WELCOME_BACK %s"), learner.getName()));
    printer.printAsPrompt(localizer.localize("PICK_ACTIVITY"), true);
    printer.printOption(
        1, String.format(localizer.localize("LEARN_LANGUAGE %s"), localizer.localize(learner.getLanguage().getCode())));
    printer.printOption(2, localizer.localize("CHANGE_INTERFACE_LANGUAGE"));
    printer.printOption(3, localizer.localize("CHANGE_LANGUAGE"));
    printer.printOption(4, localizer.localize("CHANGE_NAME"));

    int selection = prompter.readSelection(4, false);

    printer.printEmptyLine();

    return selection;
  }

  private Learner setLearner(Learner learner) {
    this.localizer.setLanguage(learner.getInterfaceLanguage());
    this.jsonHandler.save(learner, "target/learner.json");
    return this.learner = learner;
  }
}