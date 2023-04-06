package lib;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import backend.model.Language;
import backend.services.interfaces.*;
import backend.services.json.*;
import cli.lib.*;

public abstract class AbstractFeatureTests<T> {

  /**
   * Runs the feature.
   * (!!!: If the learner is accessed in the feature,
   * you need to provide an instance to the environment.)
   *
   * @param env
   * @return
   * @throws Throwable
   */
  protected abstract T runFeature(Environment env) throws Throwable;

  @FunctionalInterface
  protected interface TestRunnable {
    public void perform(PipedOutputStream input, OutputStream output) throws Throwable;
  }

  protected T executeTest(TestRunnable runTest) throws Throwable {
    return executeTest(5, runTest);
  }

  protected T executeTest(Integer timeout, TestRunnable runTest) throws Throwable {
    try (OutputStream output = new ByteArrayOutputStream();
        PipedOutputStream input = new PipedOutputStream()) {

      Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);
      Printer printer = new Printer(new PrintStream(output));
      Prompter prompter = new Prompter(new PipedInputStream(input), printer, localizer);
      Environment env = new Environment(
          null,
          localizer,
          new JSONFileTranslator(),
          new JSONFileWordGenerator(),
          new JSONFilePersistor(""),
          printer,
          prompter);

      AtomicReference<T> resultRef = new AtomicReference<>();
      AtomicReference<Boolean> testFinishedRef = new AtomicReference<>();
      AtomicReference<Throwable> exceptionRef = new AtomicReference<>();

      Thread runRegister = new Thread(() -> {
        try {
          resultRef.set(runFeature(env));
        } catch (Throwable t) {
          exceptionRef.set(t);
        }
      });

      Thread testRegister = new Thread(() -> {
        try {
          testFinishedRef.set(false);

          runTest.perform(input, output);

          testFinishedRef.set(true);
        } catch (Throwable t) {
          exceptionRef.set(t);
        }
      });

      runRegister.start();
      testRegister.start();

      testRegister.join(timeout * 1000);
      runRegister.join(timeout * 1000);

      if (exceptionRef.get() != null)
        throw exceptionRef.get();

      if (!testFinishedRef.get())
        throw new AssertionError("Test did not finish.");

      return resultRef.get();
    }
  }

  protected void waitUntilContains(OutputStream stream, String match) throws InterruptedException {
    waitFor(stream, str -> str.contains(match));
  }

  protected void waitFor(OutputStream stream, Function<String, Boolean> condition) throws InterruptedException {
    while (!condition.apply(stream.toString()))
      Thread.sleep(10);
  }

  protected void writeTo(PipedOutputStream stream, String input) throws IOException {
    stream.write((input + "\n").getBytes());
    stream.flush();
  }

}
