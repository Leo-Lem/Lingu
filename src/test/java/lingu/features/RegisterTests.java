package lingu.features;

import static org.junit.Assert.*;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

import lingu.model.Language;
import lingu.model.Learner;
import lingu.services.*;
import lingu.services.interfaces.*;

public class RegisterTests {

  @Test
  public void whenRegisteringWithCorrectInput_thenAllUserDataIsGathered() throws IOException, InterruptedException {
    Learner learner = runRegister("Leo", 1, 1);

    assertEquals(learner.getName(), "Leo");
    assertEquals(learner.getInterfaceLanguage(), Language.ENGLISH);
    assertEquals(learner.getLanguage(), Language.GERMAN);
  }

  private Learner runRegister(String name, Integer interfaceLanguageChoice, Integer languageChoice)
      throws IOException, InterruptedException {
    OutputStream output = new ByteArrayOutputStream();
    PipedOutputStream input = new PipedOutputStream();

    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);
    Printer printer = new Printer(new PrintStream(output), localizer);
    Prompter prompter = new Prompter(new PipedInputStream(input), printer);
    Translator translator = new JSONFileTranslator(Language.ENGLISH, Language.SPANISH);

    Register register = new Register(printer, prompter, localizer, translator);
    Learner[] learner = new Learner[1];

    AtomicReference<Throwable> exceptionRef = new AtomicReference<>();

    Thread runRegister = new Thread(() -> {
      try {
        learner[0] = register.run();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (Throwable t) {
        exceptionRef.set(t);
      }
    });

    Thread testRegister = new Thread(() -> {
      try {
        while (!output.toString().contains("name"))
          Thread.sleep(10);

        input.write((name + "\n").getBytes());
        input.flush();

        while (!output.toString().contains("interface language"))
          Thread.sleep(10);

        input.write((interfaceLanguageChoice + "\n").getBytes());
        input.flush();

        while (!output.toString().contains("language"))
          Thread.sleep(10);
        ;

        input.write((languageChoice + "\n").getBytes());
        input.flush();
      } catch (Throwable t) {
        exceptionRef.set(t);
      }
    });

    runRegister.start();
    testRegister.start();

    testRegister.join();

    Throwable exception = exceptionRef.get();

    if (exception != null) {
      throw new AssertionError("Test failed with exception", exception);
    }

    runRegister.join();

    return learner[0];
  }

}
