package lingu.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Learner {

  private String name;
  private Language language;
  private Vocabulary vocabulary;
  private LocalDate streakStart;
  private Language interfaceLanguage;

  public Learner(String name) {
    this(name, Language.SPANISH, new Vocabulary(), LocalDate.now(), Language.ENGLISH);
  }

  @JsonCreator
  public Learner(
      @JsonProperty("name") String name,
      @JsonProperty("language") Language language,
      @JsonProperty("vocabulary") Vocabulary vocabulary,
      @JsonProperty("streakStart") LocalDate streakStart,
      @JsonProperty("interfaceLanguage") Language interfaceLanguage) {
    this.name = name;
    this.language = language;
    this.vocabulary = vocabulary;
    this.streakStart = streakStart;
    this.interfaceLanguage = interfaceLanguage;
  }

  @JsonProperty("name")
  public String getName() {
    return this.name;
  }

  @JsonProperty("language")
  public Language getLanguage() {
    return this.language;
  }

  @JsonProperty("vocabulary")
  public Vocabulary getVocabulary() {
    return this.vocabulary;
  }

  @JsonProperty("streakStart")
  public LocalDate getStreakStart() {
    return this.streakStart;
  }

  public Integer getStreak(LocalDate currentDate) {
    return currentDate.compareTo(this.streakStart);
  }

  @JsonProperty("interfaceLanguage")
  public Language getInterfaceLanguage() {
    return this.interfaceLanguage;
  }

  public Learner setName(String newName) {
    this.name = newName;
    return this;
  }

  public Learner setLanguage(Language newLanguage) {
    this.language = newLanguage;
    return this;
  }

  public Learner setInterfaceLanguage(Language newLanguage) {
    this.interfaceLanguage = newLanguage;
    return this;
  }

  public Learner setStreakStart(LocalDate newStreakStart) {
    this.streakStart = newStreakStart;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Learner learner = (Learner) o;
    return name.equals(learner.name) &&
        language == learner.language &&
        vocabulary.equals(learner.vocabulary) &&
        streakStart.equals(learner.streakStart) &&
        interfaceLanguage == learner.interfaceLanguage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, language, vocabulary, streakStart, interfaceLanguage);
  }

}
