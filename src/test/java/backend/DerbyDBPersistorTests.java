package backend;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.model.Language;
import backend.model.Learner;
import backend.model.Vocab;
import backend.model.Vocabulary;
import backend.services.implementations.DerbyDBPersistor;
import backend.services.interfaces.Persistor;

public class DerbyDBPersistorTests {

  @Test
  public void givenLearnerIsSaved_whenLoadingLearner_thenReturnsMatchingLearner() {
    Persistor<Learner> persistor = new DerbyDBPersistor();

    Vocab vocab = new Vocab("hello", Language.ENGLISH, Language.SPANISH);
    Learner learner = new Learner()
        .setName("Leo")
        .setLocale(Language.ENGLISH)
        .setSource(Language.ENGLISH)
        .setTarget(Language.SPANISH)
        .setVocabulary(new Vocabulary(vocab));

    persistor.save(learner);

    Learner loadedLearner = persistor.load().get();

    assertEquals(learner, loadedLearner);
  }

}
