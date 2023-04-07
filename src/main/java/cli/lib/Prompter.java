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
    prompt(name);
    String input = scanner.nextLine();
    cancelIfX(input);
    return input;
  }

  public Boolean readYesOrNo(String name) {
    Boolean isYes = null;

    while (isYes == null) {
      prompt(name);
      String input = scanner.nextLine();

      if (input.contains(localizer.localize("YES_SYMBOL")))
        isYes = true;
      else if (input.contains(localizer.localize("NO_SYMBOL")))
        isYes = false;
      else {
        cancelIfX(input);
        printer.print(new Message(localizer)
            .set("YES_OR_NO_TIP")
            .asInvalidInputError()
            .build());
      }
    }

    return isYes;
  }

  public Language readLanguage(String name, Language[] languages, Language... unsupported) {
    List<Language> supported = new ArrayList<>(Arrays.asList(languages));
    supported.removeAll(Arrays.asList(unsupported));

    supported.stream()
        .forEach(language -> printer.println(new Message(localizer)
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
        prompt(name);
        String input = scanner.nextLine();

        try {
          int index = Integer.parseInt(input);

          if (0 < index && index <= endIndex)
            selection = index;
        } catch (NumberFormatException e) {
          cancelIfX(input);
        }
      } catch (InputMismatchException e) {
      } catch (NoSuchElementException e) {
        throw new CancellationException();
      }

      if (selection == null)
        printer.print(new Message(localizer)
            .set("SELECTION_TIP")
            .asInvalidInputError()
            .build());
    }

    return selection;
  }

  private void prompt(String name) {
    printer.print(new Message(localizer).asLearner(name).build());
  }

  private void cancelIfX(String input) throws CancellationException {
    if (input.equals("x"))
      throw new CancellationException();
  }

}
