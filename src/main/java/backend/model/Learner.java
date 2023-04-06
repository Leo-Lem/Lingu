package backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Learner {

  private String name;
  private Language locale, source, target;
  private Vocabulary vocabulary;

  public Learner() {
    this(null, null, null, null, new Vocabulary());
  }

  @JsonCreator
  public Learner(
      @JsonProperty("name") String name,
      @JsonProperty("locale") Language locale,
      @JsonProperty("source") Language source,
      @JsonProperty("target") Language target,
      @JsonProperty("vocabulary") Vocabulary vocabulary) {
    this.name = name;
    this.locale = locale;
    this.source = source;
    this.target = target;
    this.vocabulary = vocabulary;
  }

  @JsonProperty("name")
  public String getName() {
    return this.name;
  }

  @JsonProperty("locale")
  public Language getLocale() {
    return this.locale;
  }

  @JsonProperty("source")
  public Language getSource() {
    return this.source;
  }

  @JsonProperty("target")
  public Language getTarget() {
    return this.target;
  }

  @JsonProperty("vocabulary")
  public Vocabulary getVocabulary() {
    return this.vocabulary;
  }

  public Learner setName(String name) {
    this.name = name;
    return this;
  }

  public Learner setSource(Language source) {
    this.source = source;
    return this;
  }

  public Learner setTarget(Language target) {
    this.target = target;
    return this;
  }

  public Learner setLocale(Language locale) {
    this.locale = locale;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    else if (o == null || getClass() != o.getClass())
      return false;
    else {
      Learner learner = (Learner) o;
      return name.equals(learner.name) &&
          locale.equals(learner.locale) &&
          source.equals(learner.source) &&
          target.equals(learner.target) &&
          vocabulary.equals(learner.vocabulary);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, locale, source, target, vocabulary);
  }

}
