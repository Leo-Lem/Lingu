package cli;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.model.*;
import backend.services.implementations.JSONFileLocalizer;
import backend.services.interfaces.*;
import cli.lib.*;
import lib.InMemoryPersistor;

public class EnvironmentTests {

  @Test
  public void whenGettingLocalizedMessage_thenReturnsMessage() {
    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);
    Environment env = new Environment(null, localizer, null, null, new InMemoryPersistor<>(), null, null);

    Message message = env.getLocalizedMessage();
    assertEquals(message, new Message(localizer));
  }

  @Test
  public void whenSettingLearner_thenUpdatesLocalizerLocale() {
    Localizer localizer = new JSONFileLocalizer(Language.ENGLISH);
    Environment env = new Environment(null, localizer, null, null, new InMemoryPersistor<>(), null, null);

    env.setLearner(new Learner().setName("Leo").setLocale(Language.GERMAN));
    assertEquals(localizer.getLanguage(), Language.GERMAN);
  }

  @Test
  public void whenSettingLearner_thenSaveslearner() {
    Persistor<Learner> persistor = new InMemoryPersistor<>();
    Environment env = new Environment(null, new JSONFileLocalizer(), null, null, persistor, null, null);

    Learner learner = new Learner().setName("Leo").setLocale(Language.GERMAN);
    env.setLearner(learner);
    assertEquals(persistor.load().get(), learner);
  }

}
