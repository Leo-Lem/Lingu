package lingu.features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lingu.model.*;
import lingu.services.*;
import lingu.services.interfaces.Localizer;
import lingu.services.interfaces.Translator;

public class Register {
  private final Printer printer;
  private final Prompter prompter;
  private final Localizer localizer;
  private final Translator translator;

  private Learner learner;

  public Register(Printer printer, Prompter prompter, Localizer localizer, Translator translator) {
    this.printer = printer;
    this.prompter = prompter;
    this.localizer = localizer;
    this.translator = translator;
  }

  public Learner run() throws InterruptedException {
    printer.printHeader("Register");

    printer.printAsPrompt("To get started, please enter your name: ", false);
    learner = new Learner(getInitialName());

    printer.printEmptyLine();
    Thread.sleep(1000);

    printer.printAsPrompt("Alrighty, what interface language will it be?", true);
    learner.setInterfaceLanguage(getInitialInterfaceLanguage());
    localizer.setLanguage(learner.getInterfaceLanguage());

    printer.printEmptyLine();
    Thread.sleep(1000);

    printer.printAsPrompt(localizer.localizeWithStringArgument("PICK_LANGUAGE", learner.getName()), true);
    learner.setLanguage(getInitialLanguage());

    printer.printEmptyLine();
    Thread.sleep(1000);

    return learner;
  }

  private String getInitialName() {
    String name = prompter.readNextString();

    printer.printEmptyLine();
    printer.printAsLingu(String.format("Welcome, %s! I'm Lingu, your new language companion.", name));

    return name;
  }

  private Language getInitialInterfaceLanguage() {
    Language[] languages = localizer.getAvailableLanguages();
    for (int i = 0; i < languages.length; i++)
      printer.printOption(i + 1, localizer.localizeLanguage(languages[i]));

    Language interfaceLanguage = languages[prompter.readSelection(languages.length, false) - 1];

    printer.printEmptyLine();
    printer.printAsLingu(
        localizer.localizeWithStringArgument("NEW_INTERFACE_LANGUAGE", localizer.localizeLanguage(interfaceLanguage)));

    return interfaceLanguage;
  }

  private Language getInitialLanguage() {
    List<Language> languages = new ArrayList<>(Arrays.asList(translator.getAvailableLanguages()));
    languages.removeIf(language -> language == localizer.getLanguage());
    for (int i = 0; i < languages.size(); i++)
      printer.printOption(i + 1, localizer.localizeLanguage(languages.get(i)));

    Language language = languages.get(prompter.readSelection(languages.size(), false) - 1);

    printer.printEmptyLine();
    printer.printAsLingu(
        localizer.localizeWithStringArgument("NEW_LANGUAGE", localizer.localizeLanguage(language)));

    return language;
  }
}
