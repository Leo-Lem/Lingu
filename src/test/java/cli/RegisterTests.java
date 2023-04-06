package cli;

import static org.junit.Assert.*;

import java.util.concurrent.CancellationException;

import org.junit.Test;

import backend.model.Language;
import backend.model.Learner;
import cli.features.Register;
import cli.lib.Environment;
import lib.AbstractFeatureTests;

public class RegisterTests extends AbstractFeatureTests<Learner> {

  @Test
  public void whenRegisteringWithCorrectInput_thenAllUserDataIsGathered() throws Throwable {
    Learner learner = executeTest((in, out) -> {
      waitUntilContains(out, "name");
      writeTo(in, "Leo");

      waitUntilContains(out, "interface language");
      writeTo(in, "1");

      waitUntilContains(out, "language");
      writeTo(in, "1");
    });

    assertEquals(learner.getName(), "Leo");
    assertEquals(learner.getLocale(), Language.ENGLISH);
    assertEquals(learner.getSource(), Language.GERMAN);
    assertEquals(learner.getTarget(), Language.SPANISH);
  }

  @Override
  public Learner runFeature(Environment env) throws Throwable {
    try {
      new Register(env).run();
      return env.getLearner();
    } catch (CancellationException e) {
      return null;
    }
  }

}
