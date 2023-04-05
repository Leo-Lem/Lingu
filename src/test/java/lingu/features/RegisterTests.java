package lingu.features;

import static org.junit.Assert.*;

import java.util.concurrent.CancellationException;

import org.junit.Test;

import lingu.Environment;
import lingu.model.*;

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
    assertEquals(learner.getInterfaceLanguage(), Language.ENGLISH);
    assertEquals(learner.getLanguage(), Language.GERMAN);
  }

  @Override
  public Learner runFeature(Environment env) throws Throwable {
    try {
      return new Register(env).run();
    } catch (CancellationException e) {
      return null;
    }
  }

}
