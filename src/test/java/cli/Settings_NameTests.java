package cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.model.Learner;
import cli.features.Settings;
import cli.lib.Environment;
import lib.AbstractFeatureTests;

public class Settings_NameTests extends AbstractFeatureTests<String> {

  @Test
  public void whenChangingLanguage_thenNewLanguageIsReturned() throws Throwable {
    String name = executeTest((in, out) -> {
      waitUntilContains(out, "?");
      writeTo(in, "Leo");
    });

    assertEquals(name, "Leo");
  }

  @Override
  protected String runFeature(Environment env) throws Throwable {
    env.setLearner(new Learner().setName("James"));
    return new Settings(env).changeName();
  }

}
