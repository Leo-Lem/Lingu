package cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.model.Language;
import backend.model.Learner;
import cli.features.Settings;
import cli.lib.Environment;
import lib.AbstractFeatureTests;

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
    env.setLearner(new Learner().setName("Leo"));
    return new Settings(env).changeTarget();
  }

}
