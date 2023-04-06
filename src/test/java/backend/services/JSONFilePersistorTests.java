package backend.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.model.Learner;
import backend.services.json.JSONFilePersistor;

public class JSONFilePersistorTests {

  @Test
  public void givenLearnerIsSaved_whenLoadingLearner_thenReturnsMatchingLearner() {
    JSONFilePersistor handler = new JSONFilePersistor("data/test-learner.json");

    Learner learner = new Learner().setName("Leo");
    handler.save(learner);

    Learner loadedLearner = handler.load(Learner.class).get();

    assertEquals(learner, loadedLearner);
  }

}
