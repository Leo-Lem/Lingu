package lingu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.*;

public class Vocabulary {

  private final List<Vocab> vocabulary = new ArrayList<>();

  @JsonCreator
  public Vocabulary(
      @JsonProperty("vocabulary") Vocab... vocabulary) {
    for (Vocab vocab : vocabulary)
      this.vocabulary.add(vocab);
  }

  @JsonProperty("vocabulary")
  public Vocab[] get() {
    return vocabulary.toArray(new Vocab[0]);
  }

  public Vocab get(Integer index) {
    return vocabulary.get(index);
  }

  public Vocabulary addVocab(Vocab vocab) {
    this.vocabulary.add(vocab);
    return this;
  }

  public Vocabulary deleteVocab(Vocab vocab) {
    this.vocabulary.remove(vocab);
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Vocabulary other = (Vocabulary) obj;
    return vocabulary.equals(other.vocabulary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vocabulary);
  }

}
