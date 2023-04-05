package lingu.features;

import static org.junit.Assert.*;

import org.junit.Test;

import lingu.Environment;
import lingu.model.Learner;

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
    env.setLearner(new Learner("Leo"));
    return new Menu(env).select();
  }

}