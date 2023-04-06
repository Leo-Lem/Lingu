package backend.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class VocabTests {

  @Test
  public void whenVocabIsCreated_thenStageIs0() {
    Vocab vocab = new Vocab(null, null, null);

    assertStageIs(0, vocab);
  }

  @Test
  public void givenVocabExists_whenAdvancingStage_thenStageIsAdvanced() {
    Vocab vocab = new Vocab(null, null, null);

    vocab.advanceStage();

    assertStageIs(1, vocab);
  }

  @Test
  public void givenStageIs5_whenAdvancingStage_thenStays5() {
    Vocab vocab = new Vocab(null, null, null);
    for (int i = 0; i < 5; i++)
      vocab.advanceStage();
    assertStageIs(5, vocab);

    vocab.advanceStage();

    assertStageIs(5, vocab);
  }

  private void assertStageIs(int stage, Vocab vocab) {
    assertEquals(stage, (int) vocab.getStage());
  }

}
