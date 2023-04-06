package cli;

import static org.junit.Assert.*;

import org.junit.Test;

import backend.model.Learner;
import cli.features.Menu;
import cli.lib.Environment;
import lib.AbstractFeatureTests;

public class MenuTests extends AbstractFeatureTests<Integer> {

  @Test
  public void whenChoosingValid_thenReturnsSelection() throws Throwable {
    for (int i = 1; i <= 4; i++) {
      final int choice = i;

      int selection = executeTest((in, out) -> {
        waitUntilContains(out, "Do you want to");
        writeTo(in, String.valueOf(choice));
      });

      assertEquals(selection, choice);
    }
  }

  @Override
  protected Integer runFeature(Environment env) throws Throwable {
    env.setLearner(new Learner()).setName("Leo");
    return new Menu(env).select();
  }

}