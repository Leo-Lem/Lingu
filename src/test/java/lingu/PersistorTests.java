package lingu;

import static org.junit.Assert.*;

import org.junit.*;
import lingu.model.Learner;
import lingu.services.LearnerPersistor;
import lingu.services.Persistor;

public class PersistorTests {

  @Test
  public void givenLearnerIsSaved_whenLoadingLearner_thenReturnsMatchingLearner() {
    Persistor<Learner> persistor = new LearnerPersistor();
    Learner learner = new Learner("Leo");
    persistor.save(learner);

    Learner loadedLearner = persistor.load().get();

    assertEquals(learner, loadedLearner);
  }

}
