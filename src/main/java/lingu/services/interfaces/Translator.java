package lingu.services.interfaces;

import java.util.Optional;

import lingu.model.Language;

public interface Translator {

  public Language[] getAvailableLanguages();

  public Language getSource();

  public Language getTarget();

  public Translator setSource(Language language);

  public Translator setTarget(Language language);

  public Optional<String> translate(String word);

}
