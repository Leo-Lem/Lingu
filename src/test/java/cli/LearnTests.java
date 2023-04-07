package cli;

import static org.junit.Assert.*;

import java.util.concurrent.CancellationException;

import org.junit.Test;

import backend.model.*;
import cli.features.Learn;
import lib.FeatureTests;

public class LearnTests extends FeatureTests {

  @Test
  public void givenUserHasNoVocab_whenLearning_thenCreatesVocab() throws Throwable {
    Vocabulary vocabulary = testFeature((env) -> {
      try {
        new Learn(env).run();
      } catch (CancellationException e) {
      }
      return env.getLearner().getVocabulary();
    }, (out, in) -> {
      writeTo(in, 'x');
    });

    assertTrue(vocabulary.count() > 0);
  }

}
