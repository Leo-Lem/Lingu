package lingu.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vocab {

  private String word;
  private Language target;
  private Integer stage;

  @JsonCreator
  public Vocab(
      @JsonProperty("word") String word,
      @JsonProperty("language") Language target,
      @JsonProperty("stage") Integer stage) {
    this.word = word;
    this.target = target;
    this.stage = stage;
  }

  public Vocab(String word, Language target) {
    this(word, target, 0);
  }

  @JsonProperty("word")
  public String getWord() {
    return this.word;
  }

  @JsonProperty("language")
  public Language getTarget() {
    return this.target;
  }

  @JsonProperty("stage")
  public Integer getStage() {
    return this.stage;
  }

  public void advanceStage() {
    if (this.stage < 5)
      stage++;
  }

  public void decreaseStage() {
    if (this.stage > 0)
      stage--;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Vocab))
      return false;
    Vocab v = (Vocab) o;
    return word.equals(v.word) && target == v.target && stage.equals(v.stage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(word, target, stage);
  }

}
