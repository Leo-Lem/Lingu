package lingu.services;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class Prompter {
  private final Scanner scanner;
  private final Printer printer;

  public Prompter(Printer printer) {
    this(System.in, printer);
  }

  public Prompter(InputStream in, Printer printer) {
    this.scanner = new Scanner(in);
    this.printer = printer;
  }

  public String readNextString(String name) {
    printer.printInputPrompt(name);
    return scanner.nextLine();
  }

  public Boolean readYesOrNo(String name) {
    Boolean englishAsInterfaceLanguage = null;

    while (englishAsInterfaceLanguage == null) {
      printer.printInputPrompt(name);
      String input = scanner.nextLine();

      if (input.contains("y"))
        englishAsInterfaceLanguage = true;
      else if (input.contains("n"))
        englishAsInterfaceLanguage = false;
      else
        printer.printInvalidInput("INVALID_INPUT_TIP_YES_OR_NO");
    }

    return englishAsInterfaceLanguage;
  }

  public Integer readSelection(String name, Integer endIndex) throws CancellationException {
    Integer selection = null;

    while (selection == null) {
      try {
        printer.printInputPrompt(name);
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
        printer.printInvalidInput("INVALID_INPUT_TIP_SELECTION");
    }

    return selection;
  }
}
