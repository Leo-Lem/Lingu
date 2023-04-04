package lingu.features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;

import lingu.model.*;
import lingu.services.*;
import lingu.services.interfaces.Localizer;
import lingu.services.interfaces.Translator;

public class Settings {
  private final Printer printer;
  private final Prompter prompter;
  private final Localizer localizer;
  private final Translator translator;

  public Settings(Printer printer, Prompter prompter, Localizer localizer, Translator translator) {
    this.printer = printer;
    this.prompter = prompter;
    this.localizer = localizer;
    this.translator = translator;
  }

  public Language changeInterfaceLanguage() throws CancellationException, InterruptedException {
    return Language.ENGLISH;
  }

  public Language changeLanguage() {
    return Language.ENGLISH;
  }

  public String changeName() {
    return "";
  }

}
