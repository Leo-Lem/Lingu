package lingu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lingu.lib.JSONFileHandler;
import lingu.model.Learner;

public class JSONFileHandlerTests {

  @Test
  public void givenLearnerIsSaved_whenLoadingLearner_thenReturnsMatchingLearner() {
    JSONFileHandler handler = new JSONFileHandler();

    String pathname = "target/testing.json";
    Learner learner = new Learner("Leo");
    handler.save(learner, pathname);

    Learner loadedLearner = handler.load(pathname, Learner.class).get();

    assertEquals(learner, loadedLearner);
  }

}
