package lingu.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class VocabTests {

  @Test
  public void whenVocabIsCreated_thenStageIs0() {
    Vocab vocab = new Vocab(null, null);

    assertStageIs(0, vocab);
  }

  @Test
  public void givenVocabExists_whenAdvancingStage_thenStageIsAdvanced() {
    Vocab vocab = new Vocab(null, null);

    vocab.advanceStage();

    assertStageIs(1, vocab);
  }

  @Test
  public void givenStageIsNot0_whenDecreasingStage_thenStageIsDecreased() {
    Vocab vocab = new Vocab(null, null);
    vocab.advanceStage();
    vocab.advanceStage();
    assertStageIs(2, vocab);

    vocab.decreaseStage();

    assertStageIs(1, vocab);
  }

  @Test
  public void givenStageIs0_whenDecreasingStage_thenStays0() {
    Vocab vocab = new Vocab(null, null);

    vocab.decreaseStage();

    assertStageIs(0, vocab);
  }

  @Test
  public void givenStageIs5_whenAdvancingStage_thenStays5() {
    Vocab vocab = new Vocab(null, null);
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
