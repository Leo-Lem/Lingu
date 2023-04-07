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
  public void givenStageIsAtFinal_whenAdvancingStage_thenStaysAt10() {
    Vocab vocab = new Vocab(null, null, null);
    for (int i = 0; i < Vocab.FINAL_STAGE; i++)
      vocab.advanceStage();
    assertStageIs(Vocab.FINAL_STAGE, vocab);

    vocab.advanceStage();

    assertStageIs(Vocab.FINAL_STAGE, vocab);
  }

  private void assertStageIs(int stage, Vocab vocab) {
    assertEquals(stage, (int) vocab.getStage());
  }

}
