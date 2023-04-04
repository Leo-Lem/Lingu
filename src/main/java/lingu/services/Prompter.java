package lingu.services;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class Prompter {
  private final Scanner SCANNER;
  private final Printer PRINTER;

  public Prompter(Printer printer) {
    this(System.in, printer);
  }

  public Prompter(InputStream in, Printer printer) {
    this.SCANNER = new Scanner(in);
    this.PRINTER = printer;
  }

  public String readNextString() {
    return SCANNER.next();
  }

  public Boolean readYesOrNo() {
    Boolean englishAsInterfaceLanguage = null;

    while (englishAsInterfaceLanguage == null) {
      String input = SCANNER.next();

      if (input.contains("y"))
        englishAsInterfaceLanguage = true;
      else if (input.contains("n"))
        englishAsInterfaceLanguage = false;
      else
        PRINTER.printInvalidInput("INVALID_INPUT_TIP_YES_OR_NO");
    }

    return englishAsInterfaceLanguage;
  }

  public Integer readSelection(Integer endIndex, Boolean hasCancellationOption) throws CancellationException {
    Integer selection = null;

    while (selection == null) {
      try {
        int index = SCANNER.nextInt();

        if (hasCancellationOption && index == endIndex)
          throw new CancellationException();
        else if (index > 0 && index <= endIndex)
          selection = index;
      } catch (InputMismatchException e) {
        SCANNER.next();
      }

      if (selection == null)
        PRINTER.printInvalidInput("INVALID_INPUT_TIP_SELECTION");
    }

    return selection;
  }
}
