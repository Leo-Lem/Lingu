package cli.features;

import java.util.concurrent.CancellationException;

import cli.lib.*;

public class Menu {
  private final Environment env;

  public Menu(Environment env) {
    this.env = env;
  }

  public Integer select() throws InterruptedException {
    Printer printer = env.getPrinter();
    printer.println(env.getLocalizedMessage()
        .set("WELCOME_BACK", env.getLearner().getName()).asLingu().build());

    printer.println(env.getLocalizedMessage()
        .set("PICK_ACTIVITY").asLingu().build());
    printer.println(env.getLocalizedMessage()
        .set("LEARN_LANGUAGE", env.getLearner().getTarget()).asOption(1).build());
    printer.println(env.getLocalizedMessage()
        .set("CHANGE_TARGET").asOption(2).build());
    printer.println(env.getLocalizedMessage()
        .set("CHANGE_SOURCE").asOption(3).build());
    printer.println(env.getLocalizedMessage()
        .set("CHANGE_LOCALE").asOption(4).build());
    printer.println(env.getLocalizedMessage()
        .set("CHANGE_NAME").asOption(5).build());
    printer.println(env.getLocalizedMessage()
        .set("QUIT").asOption("x").build());

    Integer selection = null;
    try {
      selection = env.getPrompter().readSelection(env.getLearner().getName(), 5);
    } catch (CancellationException e) {
      throw new InterruptedException();
    }

    return selection;
  }

}
