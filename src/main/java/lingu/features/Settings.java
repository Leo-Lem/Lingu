package lingu.features;

import java.util.*;
import java.util.concurrent.CancellationException;

import lingu.Environment;
import lingu.model.*;
import lingu.services.Printer;
import lingu.services.interfaces.Localizer;

public class Settings {
  private final Environment env;

  public Settings(Environment env) {
    this.env = env;
  }

  public Language changeInterfaceLanguage() throws CancellationException {
    Localizer localizer = env.getLocalizer();
    Printer printer = env.getPrinter();

    printer.printlnAsLingu("PICK_INTERFACE_LANGUAGE");

    List<Language> languages = new ArrayList<>(Arrays.asList(localizer.getAvailableLanguages()));
    languages.removeIf(language -> language == localizer.getLanguage());
    for (int i = 0; i < languages.size(); i++)
      printer.printOption(i + 1, languages.get(i).toString());
    printer.printOption("x", "CANCEL");

    int selection = env.getPrompter().readSelection(env.getLearner().getName(), languages.size());

    Language language = languages.get(selection - 1);
    localizer.setLanguage(language);
    printer.printEmptyLine();

    printer.printlnAsLingu(localizer.localizeWithStringArgument("NEW_INTERFACE_LANGUAGE", language.toString()));

    printer.printEmptyLine();

    return language;
  }

  public Language changeLanguage() {
    Localizer localizer = env.getLocalizer();
    Printer printer = env.getPrinter();

    env.getPrinter().printlnAsLingu("PICK_LANGUAGE");

    List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getAvailableLanguages()));
    languages.removeIf(language -> language == localizer.getLanguage() || language == env.getTranslator().getTarget());
    for (int i = 0; i < languages.size(); i++)
      printer.printOption(i + 1, languages.get(i).toString());
    printer.printOption("x", "CANCEL");

    int selection = env.getPrompter().readSelection(env.getLearner().getName(), languages.size());

    Language language = languages.get(selection - 1);

    printer.printlnAsLingu(
        localizer.localizeWithStringArgument("NEW_LANGUAGE", language.toString()));

    return language;
  }

  public String changeName() {
    env.getPrinter().printlnAsLingu("CHOOSE_NAME");
    String name = env.getPrompter().readNextString("?");

    env.getPrinter()
        .printlnAsLingu(env.getLocalizer().localizeWithStringArgument("NEW_NAME", env.getLearner().getName()));

    return name;
  }

}
