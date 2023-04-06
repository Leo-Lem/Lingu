package backend.services;

// import java.io.*;

import org.junit.Test;

// import backend.model.Language;

// TODO: generalize the input and output redirection

public class PrompterTests {

  // private final OutputStream out = new ByteArrayOutputStream();
  // private final PipedOutputStream in = new PipedOutputStream();
  // private final Prompter prompter;

  public PrompterTests() throws Exception {
    // prompter = new Prompter(
    // new PipedInputStream(in),
    // new Printer(new PrintStream(out), new JSONFileLocalizer(Language.ENGLISH)));
  }

  @Test
  public void whenReadingString_thenReturnsString() throws Throwable {
    // String name = "Leo", str = "Hello";
  }

  @Test
  public void whenCancelling_thenThrowsCancelException() throws Throwable {

  }

  @Test
  public void whenInputtingInvalid_thenGivesFeedbackAndDoesNotCrash() throws Throwable {

  }

}
