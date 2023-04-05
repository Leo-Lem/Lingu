package lingu.services;

import java.io.PrintStream;

import lingu.services.interfaces.Localizer;

public class Printer {
  private final PrintStream out;
  private final Localizer localizer;

  public Printer(Localizer localizer) {
    this(System.out, localizer);
  }

  public Printer(PrintStream out, Localizer localizer) {
    this.out = out;
    this.localizer = localizer;
  }

  public void printEmptyLine() {
    out.println();
  }

  public void printlnAsLingu(String message) {
    printAsLingu(appendln(localizer.localize(message)));
  }

  public void printAsLingu(String message) {
    printAsPerson("Lingu", localizer.localize(message));
  }

  public void printInputPrompt(String name) {
    printAsPerson(name, "");
  }

  public void printOption(Integer identifier, String option) {
    printOption(String.valueOf(identifier), option);
  }

  public void printOption(String identifier, String option) {
    printlnSlowly("  (" + identifier + ") " + localizer.localize(option));
  }

  public void printInvalidInput(String tip) {
    printSlowly("\033[1A[\033[K" + localizer.localize("INVALID_INPUT") + ": " + localizer.localize(tip) + "] ");
  }

  private void printAsPerson(String name, String message) {
    printSlowly("«" + name + "» ");
    printSlowly(message, 20);
  }

  private void printlnSlowly(String message) {
    printSlowly(appendln(message));
  }

  private void printSlowly(String message) {
    printSlowly(message, 2);
  }

  private void printSlowly(String message, Integer delay) {
    try {
      for (int i = 0; i < message.length(); i++) {
        out.print(message.charAt(i));
        Thread.sleep(delay);
      }
    } catch (InterruptedException e) {
    }
  }

  private String appendln(String str) {
    return str + "\n";
  }

}
