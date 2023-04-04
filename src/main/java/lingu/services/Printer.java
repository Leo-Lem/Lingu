package lingu.services;

import java.io.PrintStream;

import lingu.services.interfaces.Localizer;

public class Printer {
  private final PrintStream OUT;
  private final Localizer LOCALIZER;

  public Printer(Localizer localizer) {
    this(System.out, localizer);
  }

  public Printer(PrintStream out, Localizer localizer) {
    this.OUT = out;
    this.LOCALIZER = localizer;
  }

  public void printHeader(String page) {
    printEmptyLine();
    printAsHeader("");
    printAsHeader("Lingu");
    printAsHeader(LOCALIZER.localize("SUBTITLE"));
    printAsHeader(LOCALIZER.localize(page));
    printAsHeader("");
    printEmptyLine();
  }

  public void printEmptyLine() {
    OUT.println();
  }

  public void printAsHeader(String header) {
    OUT.println("*** " + header);
  }

  public void printAsLingu(String message) {
    OUT.println("«Lingu» " + message);
  }

  public void printAsPromptWithName(String name) {
    OUT.print("« " + name + "» ");
  }

  public void printAsPrompt(String prompt, boolean withLinebreak) {
    if (withLinebreak)
      OUT.println("> " + prompt);
    else
      OUT.print("> " + prompt);
  }

  public void printOption(Integer index, String option) {
    OUT.println("    (" + index + ") " + option);
  }

  public void printInvalidInput(String tip) {
    OUT.print("(" + LOCALIZER.localize("INVALID_INPUT") + ") " + LOCALIZER.localize(tip) + ": ");
  }
}
