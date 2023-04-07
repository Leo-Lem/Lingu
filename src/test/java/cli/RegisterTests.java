package cli;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import backend.model.*;
import cli.features.Register;
import lib.FeatureTests;

public class RegisterTests extends FeatureTests {

  @Test
  public void whenRegisteringWithCorrectInput_thenAllUserDataIsGathered() throws Throwable {
    Learner learner = testFeature((env) -> {
      new Register(env).run();
      return env.getLearner();
    }, (out, in) -> {
      // wait and provide name
      writeTo(in, "Leo");

      // wait and select target language
      writeTo(in, "1");

      // wait and select source language
      writeTo(in, "1");
    });

    assertEquals(learner.getName(), "Leo");
    assertEquals(learner.getTarget(), Language.SPANISH);
    assertEquals(learner.getSource(), Language.ENGLISH);
  }

  @Test
  public void whenCancelling_thenInterrupts() throws Throwable {
    try {
      testFeature((env) -> {
        new Register(env).run();
        return null;
      }, (out, in) -> {
        writeTo(in, "x");
      });
    } catch (ExecutionException e) {
      assertEquals(e.getCause().getClass(), InterruptedException.class);
    }
  }

}
