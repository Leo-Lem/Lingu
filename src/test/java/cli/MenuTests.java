package cli;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import backend.model.*;
import cli.features.Menu;
import lib.FeatureTests;

public class MenuTests extends FeatureTests {

  @Test
  public void whenChoosing_thenReturnsSelection() throws Throwable {
    for (int i = 1; i <= 4; i++) {
      final int choice = i;

      int selection = testFeature((env) -> {
        env.setLearner(new Learner().setName("Leo").setTarget(Language.SPANISH));
        return new Menu(env).select();
      }, (out, in) -> {
        waitUntilContains(out, "WELCOME_BACK", 3);
        writeTo(in, choice);
      });

      assertEquals(selection, choice);
    }
  }

  @Test
  public void whenTypingX_thenThrowsInterrupted() throws Throwable {
    try {
      testFeature((env) -> {
        env.setLearner(new Learner().setName("Leo").setTarget(Language.SPANISH));
        return new Menu(env).select();
      }, (out, in) -> {
        writeTo(in, "x");
      });
    } catch (ExecutionException e) {
      assertEquals(e.getCause().getClass(), InterruptedException.class);
    }
  }

}