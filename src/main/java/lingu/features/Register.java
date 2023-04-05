package lingu.features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;

import lingu.Environment;
import lingu.model.*;
import lingu.services.Printer;
import lingu.services.interfaces.Localizer;

public class Register {
  private final Environment env;
  private Learner learner;

  public Register(Environment env) {
    this.env = env;
  }

  public Learner run() throws CancellationException {
    Printer printer = env.getPrinter();

    learner = new Learner(getInitialName());

    printer.printEmptyLine();

    learner.setInterfaceLanguage(getInitialInterfaceLanguage());
    env.getLocalizer().setLanguage(learner.getInterfaceLanguage());

    printer.printEmptyLine();

    learner.setLanguage(getInitialLanguage());

    printer.printEmptyLine();

    return learner;
  }

  private String getInitialName() {
    Printer printer = env.getPrinter();

    printer.printlnAsLingu("To get started, please enter your name:");
    String name = env.getPrompter().readNextString("?");

    printer.printEmptyLine();
    printer.printlnAsLingu(String.format("Welcome, %s! I'm Lingu, your new language companion.", name));

    return name;
  }

  private Language getInitialInterfaceLanguage() throws CancellationException {
    Localizer localizer = env.getLocalizer();
    Printer printer = env.getPrinter();

    printer.printlnAsLingu("Alrighty, what interface language will it be?");

    Language[] languages = localizer.getAvailableLanguages();
    for (int i = 0; i < languages.length; i++)
      printer.printOption(i + 1, localizer.localize(languages[i].toString()));

    Language interfaceLanguage = languages[env.getPrompter().readSelection(learner.getName(), languages.length) - 1];

    printer.printEmptyLine();
    printer.printlnAsLingu(
        localizer.localizeWithStringArgument("NEW_INTERFACE_LANGUAGE", interfaceLanguage.toString()));

    return interfaceLanguage;
  }

  private Language getInitialLanguage() throws CancellationException {
    Localizer localizer = env.getLocalizer();
    Printer printer = env.getPrinter();

    printer.printlnAsLingu("PICK_LANGUAGE");

    List<Language> languages = new ArrayList<>(Arrays.asList(env.getTranslator().getAvailableLanguages()));
    languages.removeIf(language -> language == localizer.getLanguage());
    for (int i = 0; i < languages.size(); i++)
      printer.printOption(i + 1, localizer.localize(languages.get(i).toString()));

    Language language = languages.get(env.getPrompter().readSelection(learner.getName(), languages.size()) - 1);

    printer.printEmptyLine();
    printer.printAsLingu(localizer.localizeWithStringArgument("NEW_LANGUAGE", language.toString()));

    return language;
  }
}
