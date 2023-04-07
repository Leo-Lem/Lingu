package backend.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.model.Language;
import backend.model.Learner;
import backend.services.interfaces.Persistor;
import backend.services.json.JSONFilePersistor;

public class JSONFilePersistorTests {

  @Test
  public void givenLearnerIsSaved_whenLoadingLearner_thenReturnsMatchingLearner() {
    Persistor<Learner> persistor = new JSONFilePersistor<>(Learner.class, "target/test-learner.json");

    Learner learner = new Learner()
        .setName("Leo")
        .setLocale(Language.ENGLISH)
        .setSource(Language.ENGLISH)
        .setTarget(Language.SPANISH);
    persistor.save(learner);

    Learner loadedLearner = persistor.load().get();

    assertEquals(learner, loadedLearner);
  }

}
