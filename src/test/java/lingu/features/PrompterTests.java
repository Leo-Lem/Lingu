package lingu.features;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import lingu.model.Language;
import lingu.services.JSONFileLocalizer;
import lingu.services.Printer;
import lingu.services.Prompter;

public class PrompterTests {

  private final OutputStream out = new ByteArrayOutputStream();
  private final PipedOutputStream in = new PipedOutputStream();
  private final Prompter prompter;

  public PrompterTests() throws Exception {
    prompter = new Prompter(
        new PipedInputStream(in),
        new Printer(new PrintStream(out), new JSONFileLocalizer(Language.ENGLISH)));
  }

  @Test
  public void whenReadingString_thenReturnsString() throws Throwable {
    String name = "Leo", str = "Hello";
  }

  @Test
  public void whenCancelling_thenThrowsCancelException() throws Throwable {

  }

  @Test
  public void whenInputtingInvalid_thenGivesFeedbackAndDoesNotCrash() throws Throwable {

  }

}
