package cli;

import java.util.concurrent.CancellationException;

import cli.features.*;
import cli.lib.*;

public class Lingu {

  public static void main(String[] args) {
    Lingu lingu = new Lingu();
    lingu.start();
  }

  private final Environment env;

  public Lingu() {
    this(new Environment());
  }

  public Lingu(Environment env) {
    this.env = env;
  }

  public void start() {
    Printer printer = env.getPrinter();

    try {
      new Register(env).run();

      while (true) {
        try {
          printer.println();
          int selection = new Menu(env).select();

          if (selection == 1)
            new Learn(env).run();
          else {
            Settings settings = new Settings(env);
            switch (selection) {
              case 2:
                env.setLearner(env.getLearner().setTarget(settings.changeTarget()));
                break;
              case 3:
                env.setLearner(env.getLearner().setSource(settings.changeSource()));
                break;
              case 4:
                env.setLearner(env.getLearner().setLocale(settings.changeLocale()));
                break;
              case 5:
                env.setLearner(env.getLearner().setName(settings.changeName()));
                break;
            }
          }
        } catch (CancellationException e) {
          printer.println(env.getLocalizedMessage()
              .set("CANCEL_MESSAGE").asLingu().build());
          printer.println();
        }
      }
    } catch (InterruptedException e) {
      if (env.getLearner().getName() != null) {
        printer.println(env.getLocalizedMessage()
            .set("GOODBYE_MESSAGE", env.getLearner().getName()).asLingu().build());
        printer.println();
      }
    }
  }

}