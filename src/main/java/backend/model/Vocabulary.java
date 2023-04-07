package backend.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.*;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Vocabulary implements Iterable<Vocab> {

  private final List<Vocab> vocabs = new ArrayList<>();

  @JsonCreator
  public Vocabulary(Vocab... vocabulary) {
    for (Vocab vocab : vocabulary)
      this.vocabs.add(vocab);
  }

  @JsonValue
  public Vocab[] get() {
    return vocabs.toArray(new Vocab[] {});
  }

  public Vocab get(Vocab vocab) {
    try {
      return vocabs.get(vocabs.indexOf(vocab));
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  public Vocabulary prefix(Integer count) {
    return new Vocabulary(vocabs.subList(0, count).toArray(new Vocab[] {}));
  }

  public Vocabulary get(Language source, Language target) {
    return new Vocabulary(
        vocabs.stream()
            .filter(vocab -> vocab.getSource().equals(source)
                && vocab.getTarget().equals(target)
                && vocab.getNextUp().isBefore(LocalDateTime.now()))
            .sorted((vocab, other) -> vocab.getNextUp().compareTo(other.getNextUp()))
            .collect(Collectors.toList())
            .toArray(new Vocab[] {}));
  }

  public Vocabulary add(Vocab... vocabs) {
    for (Vocab vocab : vocabs)
      if (!this.vocabs.contains(vocab))
        this.vocabs.add(vocab);
    return this;
  }

  public Vocabulary delete(Vocab... vocabs) {
    for (Vocab vocab : vocabs)
      this.vocabs.remove(vocab);
    return this;
  }

  public Boolean isEmpty() {
    return vocabs.size() == 0;
  }

  public Boolean contains(Vocab vocab) {
    return vocabs.contains(vocab);
  }

  public Integer count() {
    return vocabs.size();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Vocabulary other = (Vocabulary) obj;
    return vocabs.equals(other.vocabs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vocabs);
  }

  @Override
  public Iterator<Vocab> iterator() {
    return new VocabularyIterator();
  }

  private class VocabularyIterator implements Iterator<Vocab> {
    private int currentIndex = 0;

    @Override
    public boolean hasNext() {
      return currentIndex < vocabs.size() && vocabs.get(currentIndex) != null;
    }

    @Override
    public Vocab next() {
      return vocabs.get(currentIndex++);
    }

  }

}
