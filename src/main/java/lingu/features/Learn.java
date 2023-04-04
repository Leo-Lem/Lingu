package lingu.features;

import java.util.concurrent.CancellationException;

import lingu.model.*;
import lingu.services.*;
import lingu.services.interfaces.Localizer;
import lingu.services.interfaces.Translator;

public class Learn {
  private final Printer printer;
  private final Prompter prompter;
  private final Localizer localizer;
  private final Translator translator;

  private Learner learner;

  public Learn(Learner learner, Printer printer, Prompter prompter, Localizer localizer, Translator translator) {
    this.learner = learner;
    this.printer = printer;
    this.prompter = prompter;
    this.localizer = localizer;
    this.translator = translator;
  }

  public Learner run() throws CancellationException, InterruptedException {
    return learner;
  }

}
