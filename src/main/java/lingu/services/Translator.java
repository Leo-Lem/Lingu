package lingu.services;

import java.util.Optional;

import lingu.model.Language;

public interface Translator {

  public Language getSource();

  public Language getTarget();

  public abstract Optional<String> translate(String word);

}
