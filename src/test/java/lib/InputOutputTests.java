package lib;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

public abstract class InputOutputTests {
  @FunctionalInterface
  protected interface Running<T> {
    public T perform(PipedOutputStream writeOutput, PipedInputStream readInput) throws Throwable;
  }

  @FunctionalInterface
  protected interface Testing {
    public void perform(PipedInputStream readOutput, PipedOutputStream writeInput) throws Throwable;
  }

  protected <T> T test(Running<T> run, Testing test) throws Throwable {
    return test(10, run, test);
  }

  protected <T> T test(Integer timeout, Running<T> run, Testing test) throws Throwable {
    try (PipedOutputStream writeInput = new PipedOutputStream();
        PipedInputStream readInput = new PipedInputStream(writeInput);
        PipedOutputStream writeOutput = new PipedOutputStream();
        PipedInputStream readOutput = new PipedInputStream(writeOutput)) {

      CompletableFuture<T> runs = CompletableFuture.supplyAsync(() -> {
        try {
          return run.perform(writeOutput, readInput);
        } catch (Throwable t) {
          throw new CompletionException(t);
        }
      });

      CompletableFuture<Void> tests = CompletableFuture.runAsync(() -> {
        try {
          test.perform(readOutput, writeInput);
        } catch (Throwable t) {
          throw new CompletionException(t);
        }
      });

      tests.get(timeout, TimeUnit.SECONDS);
      return runs.get(timeout, TimeUnit.SECONDS);
    }
  }

  protected void waitUntilContains(InputStream stream, Object match, Integer timeout) throws InterruptedException {
    waitFor(stream, str -> str.contains(match.toString()), timeout);
  }

  protected void waitFor(InputStream stream, Function<String, Boolean> condition, Integer timeout)
      throws AssertionError, InterruptedException {
    Thread wait = new Thread(() -> {
      try (Scanner scanner = new Scanner(stream)) {
        while (true) {
          try {
            String line = scanner.nextLine();
            if (line != null && condition.apply(line))
              return;

            Thread.sleep(10);
          } catch (Exception e) {
          }
        }
      }
    });

    wait.start();

    try {
      wait.join(timeout * 1000);
    } catch (InterruptedException e) {
      throw new AssertionError("Wait was interrupted…");
    }

    if (wait.isAlive())
      throw new AssertionError("Failed to satisfy condition: " + condition.toString());
  }

  protected void writeTo(OutputStream stream, Object input) throws IOException {
    stream.write((input.toString() + "\n").getBytes());
    stream.flush();
  }
}
