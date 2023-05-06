package cli;

import static org.junit.Assert.*;

import java.io.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import backend.model.*;
import backend.services.implementations.JSONFileLocalizer;
import cli.lib.*;
import lib.InputOutputTests;

// import backend.model.Language;

public class PrompterTests extends InputOutputTests {

  @Test
  public void whenReadingString_thenReturnsString() throws Throwable {
    String str = "Hello";

    String readStr = test(
        (out, in) -> getPrompter(out, in).readNextString("Leo"),
        (out, in) -> writeTo(in, str));

    assertEquals(str, readStr);
  }

  @Test
  public void whenCancelling_thenThrowsCancelException() throws Throwable {
    try {
      test((out, in) -> getPrompter(out, in).readNextString("Leo"), (out, in) -> writeTo(in, "x"));
      test((out, in) -> getPrompter(out, in).readSelection("Leo", 3), (out, in) -> writeTo(in, "x"));
    } catch (ExecutionException e) {
      assertEquals(e.getCause().getClass(), CancellationException.class);
    }
  }

  @Test
  public void whenReadingYesOrNo_thenReturnsCorrectValue() throws Throwable {
    Boolean isYes = test(
        (out, in) -> getPrompter(out, in).readYesOrNo("Leo"),
        (out, in) -> writeTo(in, "YES_SYMBOL"));

    assertTrue(isYes);

    isYes = test(
        (out, in) -> getPrompter(out, in).readYesOrNo("Leo"),
        (out, in) -> writeTo(in, "NO_SYMBOL"));

    assertFalse(isYes);
  }

  private Prompter getPrompter(OutputStream out, InputStream in) {
    Printer printer = new Printer(new PrintStream(out));
    return new Prompter(in, printer, new JSONFileLocalizer(Language.BASE, "localizations"));
  }

}
