package lingu.features;

import java.util.concurrent.CancellationException;

import lingu.Environment;
import lingu.services.Printer;
import lingu.services.interfaces.Localizer;

public class Menu {
  private final Environment env;

  public Menu(Environment env) {
    this.env = env;
  }

  public Integer select() throws InterruptedException {
    Localizer localizer = env.getLocalizer();
    Printer printer = env.getPrinter();

    printer.printlnAsLingu(localizer.localizeWithStringArgument("WELCOME_BACK", env.getLearner().getName()));

    printer.printlnAsLingu("PICK_ACTIVITY");
    printer.printOption(1,
        localizer.localizeWithStringArgument("LEARN_LANGUAGE", env.getLearner().getLanguage().toString())
            .toLowerCase());
    printer.printOption(2, "CHANGE_INTERFACE_LANGUAGE");
    printer.printOption(3, "CHANGE_LANGUAGE");
    printer.printOption(4, "CHANGE_NAME");
    printer.printOption("x", "QUIT");

    Integer selection = null;
    while (selection == null) {
      try {
        selection = env.getPrompter().readSelection(env.getLearner().getName(), 4);
      } catch (CancellationException e) {
        throw new InterruptedException();
      }
    }

    return selection;
  }

}
