package cli.lib;

import java.io.PrintStream;

public class Printer {
  private final PrintStream out;

  public Printer() {
    this(System.out);
  }

  public Printer(PrintStream out) {
    this.out = out;
  }

  public void println() {
    println("");
  }

  public void println(String str) {
    print(str + "\n");
  }

  public void println(String str, Long delay) {
    print(str + "\n", delay);
  }

  public void print(String str) {
    print(str, 10L);
  }

  public void print(String str, Long delay) {
    try {
      for (int i = 0; i < str.length(); i++) {
        out.print(str.charAt(i));
        Thread.sleep(delay);
      }
    } catch (InterruptedException e) {
    }
  }

}
