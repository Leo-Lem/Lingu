package lingu.features;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lingu.Environment;
import lingu.model.Learner;

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
    env.setLearner(new Learner("James"));
    return new Settings(env).changeName();
  }

}
