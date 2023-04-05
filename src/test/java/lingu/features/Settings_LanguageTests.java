package lingu.features;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lingu.Environment;
import lingu.model.*;

public class Settings_LanguageTests extends AbstractFeatureTests<Language> {

  @Test
  public void whenChangingLanguage_thenNewLanguageIsReturned() throws Throwable {
    Language language = executeTest((in, out) -> {
      waitUntilContains(out, "Leo");
      writeTo(in, "1");
    });

    assertEquals(language, Language.GERMAN);
  }

  @Override
  protected Language runFeature(Environment env) throws Throwable {
    env.setLearner(new Learner("Leo"));
    return new Settings(env).changeLanguage();
  }

}
