package cli.lib;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CancellationException;

import backend.model.*;
import backend.services.interfaces.Localizer;

public class Prompter {
  private final Scanner scanner;
  private final Printer printer;
  private final Localizer localizer;

  public Prompter(Printer printer, Localizer localizer) {
    this(System.in, printer, localizer);
  }

  public Prompter(InputStream in, Printer printer, Localizer localizer) {
    this.scanner = new Scanner(in);
    this.printer = printer;
    this.localizer = localizer;
  }

  public String readNextString(String name) throws CancellationException {
    printer.print(new Message(localizer).asLearner(name).build());

    String input = scanner.nextLine();

    if (input.equals("x"))
      throw new CancellationException();

    return input;
  }

  public Boolean readYesOrNo(String name) {
    Boolean englishAsInterfaceLanguage = null;

    while (englishAsInterfaceLanguage == null) {
      printer.print(new Message(localizer)
          .asLearner(name)
          .build());
      String input = scanner.nextLine();

      if (input.contains(localizer.localize("YES_SYMBOL")))
        englishAsInterfaceLanguage = true;
      else if (input.contains(localizer.localize("NO_SYMBOL")))
        englishAsInterfaceLanguage = false;
      else
        printer.print(new Message(localizer)
            .set("INVALID_INPUT_TIP_YES_OR_NO")
            .asInvalidInputError()
            .build());
    }

    return englishAsInterfaceLanguage;
  }

  public Language readLanguage(String name, Language[] languages, Language... unsupported) {
    List<Language> supported = new ArrayList<>(Arrays.asList(languages));
    supported.removeAll(Arrays.asList(unsupported));

    supported.stream()
        .forEach(
            language -> printer.println(new Message(localizer)
                .set(language)
                .asOption(supported.indexOf(language) + 1)
                .build()));
    printer.println(new Message(localizer)
        .set("CANCEL")
        .asOption("x")
        .build());

    return supported.get(readSelection(name, supported.size() + 1) - 1);
  }

  public Integer readSelection(String name, Integer endIndex) throws CancellationException {
    Integer selection = null;

    while (selection == null) {
      try {
        printer.print(
            new Message(localizer)
                .asLearner(name)
                .build());
        String input = scanner.nextLine();

        try {
          int index = Integer.parseInt(input);

          if (0 < index && index <= endIndex)
            selection = index;
        } catch (NumberFormatException e) {
          if (input.contains("x"))
            throw new CancellationException();
        }
      } catch (InputMismatchException e) {
      } catch (NoSuchElementException e) {
        throw new CancellationException();
      }

      if (selection == null)
        printer.print(new Message(localizer)
            .set("INVALID_INPUT_TIP_SELECTION")
            .asInvalidInputError()
            .build());
    }

    return selection;
  }

}
