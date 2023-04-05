package lingu.features;

import java.util.concurrent.CancellationException;

import lingu.Environment;
import lingu.model.*;

public class Learn {
  private final Environment env;

  private Learner learner;

  public Learn(Environment env) {
    this.env = env;
  }

  public Learner run() throws CancellationException {
    return learner;
  }

}
