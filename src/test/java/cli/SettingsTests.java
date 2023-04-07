package cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.model.*;
import cli.features.Settings;
import lib.FeatureTests;

public class SettingsTests extends FeatureTests {

  @Test
  public void whenChangingSourceLanguage_thenNewLanguageIsReturned() throws Throwable {
    Language language = testFeature((env) -> {
      env.setLearner(new Learner().setName("Leo"));
      return new Settings(env).changeSource();
    }, (out, in) -> {
      waitUntilContains(out, "PICK_SOURCE", 3);
      writeTo(in, "1");
    });

    assertEquals(language, Language.ENGLISH);
  }

  @Test
  public void whenChangingTargetLanguage_thenNewLanguageIsReturned() throws Throwable {
    Language language = testFeature((env) -> {
      env.setLearner(new Learner().setName("Leo"));
      return new Settings(env).changeTarget();
    }, (out, in) -> {
      waitUntilContains(out, "PICK_TARGET", 3);
      writeTo(in, "1");
    });

    assertEquals(language, Language.ENGLISH);
  }

  @Test
  public void whenChangingName_thenNewNameIsReturned() throws Throwable {
    String name = testFeature((env) -> {
      env.setLearner(new Learner().setName("James"));
      return new Settings(env).changeName();
    }, (out, in) -> {
      waitUntilContains(out, "CHOOSE_NAME", 3);
      writeTo(in, "Leo");
    });

    assertEquals(name, "Leo");
  }

  @Test
  public void whenChangingLocale_thenNewLocaleIsSet() throws Throwable {
    Language language = testFeature((env) -> {
      env.setLearner(new Learner().setName("Leo"));
      return new Settings(env).changeLocale();
    }, (out, in) -> {
      waitUntilContains(out, "PICK_LOCALE", 3);
      writeTo(in, "1");
    });

    assertEquals(language, Language.ENGLISH);
  }

}
