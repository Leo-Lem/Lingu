package backend.model;

import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.*;

import com.fasterxml.jackson.annotation.*;

public class Vocab {

  public static final Integer FINAL_STAGE = 10;
  public static final Map<Integer, TemporalAmount> SRS_INTERVALS;
  static {
    SRS_INTERVALS = new HashMap<>();
    SRS_INTERVALS.put(0, Duration.ZERO);
    SRS_INTERVALS.put(1, Duration.ofHours(8));
    SRS_INTERVALS.put(2, Period.ofDays(1));
    SRS_INTERVALS.put(3, Period.ofDays(2));
    SRS_INTERVALS.put(4, Period.ofDays(4));
    SRS_INTERVALS.put(5, Period.ofDays(8));
    SRS_INTERVALS.put(6, Period.ofWeeks(2));
    SRS_INTERVALS.put(7, Period.ofMonths(1));
    SRS_INTERVALS.put(8, Period.ofMonths(2));
    SRS_INTERVALS.put(9, Period.ofMonths(4));
    SRS_INTERVALS.put(10, Period.ofYears(1));
  }

  private String word;
  private Language source, target;
  private Integer stage;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime nextUp;

  public Vocab(String id, Language source, Language target) {
    this(id, source, target, 0, LocalDateTime.now());
  }

  @JsonCreator
  public Vocab(
      @JsonProperty("word") String word,
      @JsonProperty("source") Language source,
      @JsonProperty("target") Language target,
      @JsonProperty("stage") Integer stage,
      @JsonProperty("nextUp") LocalDateTime nextUp) {
    this.word = word;
    this.source = source;
    this.target = target;
    this.stage = stage;
    this.nextUp = nextUp;
  }

  public String getWord() {
    return this.word;
  }

  public Language getSource() {
    return this.source;
  }

  public Language getTarget() {
    return this.target;
  }

  public Integer getStage() {
    return this.stage;
  }

  public LocalDateTime getNextUp() {
    return this.nextUp;
  }

  @JsonIgnore
  public TemporalAmount getCurrentInterval() {
    return SRS_INTERVALS.getOrDefault(stage, Duration.ZERO);
  }

  public Vocab advanceStage() {
    if (this.stage < FINAL_STAGE)
      stage++;

    nextUp = nextUp.plus(SRS_INTERVALS.getOrDefault(stage, Duration.ZERO));

    return this;
  }

  public Vocab resetStage() {
    stage = 0;

    nextUp = nextUp.plus(SRS_INTERVALS.getOrDefault(stage, Duration.ZERO));

    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Vocab))
      return false;
    Vocab v = (Vocab) o;
    return word.equals(v.word) && source == v.source && target == v.target;
  }

  @Override
  public int hashCode() {
    return Objects.hash(word, source, target);
  }

}
