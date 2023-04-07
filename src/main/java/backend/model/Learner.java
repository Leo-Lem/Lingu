package backend.model;

import java.util.Objects;

public class Learner {

  private String name;
  private Language locale, source, target;
  private Vocabulary vocabulary = new Vocabulary();

  public String getName() {
    return this.name;
  }

  public Language getLocale() {
    return this.locale;
  }

  public Language getSource() {
    return this.source;
  }

  public Language getTarget() {
    return this.target;
  }

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

  public Learner setVocabulary(Vocabulary vocabulary) {
    this.vocabulary = vocabulary;
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
