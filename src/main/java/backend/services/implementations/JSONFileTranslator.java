package backend.services.implementations;

import java.util.*;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.*;

import backend.model.*;
import backend.services.interfaces.*;

public class JSONFileTranslator implements Translator {
  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  public static class Translations {
    private Map<String, Map<String, String>> translations;

    @JsonCreator
    public Translations(Map<String, Map<String, String>> translations) {
      this.translations = translations;
    }

    @JsonValue
    public Map<String, Map<String, String>> getTranslations() {
      return translations;
    }

    public Optional<String> get(Object key, Language language) {
      return Optional.ofNullable(translations.get(key.toString()))
          .flatMap(translation -> Optional.ofNullable(translation.get(language.getCode())));
    }

    public Set<Entry<String, Map<String, String>>> entrySet() {
      return translations.entrySet();
    }
  }

  private Translations translations;

  public JSONFileTranslator() throws IllegalStateException {
    this(new JSONFilePersistor<>(Translations.class, "src/main/resources/translations.json"));
  }

  public JSONFileTranslator(Persistor<Translations> persistor) throws IllegalStateException {
    try {
      this.translations = persistor.load().get();
    } catch (Exception e) {
      throw new IllegalStateException("The localizations file cannot be loaded: " + e.getMessage());
    }
  }

  @Override
  public Language[] getSupportedLanguages() {
    return new Language[] { Language.BASE, Language.ENGLISH, Language.GERMAN, Language.SPANISH, Language.FRENCH };
  }

  @Override
  public Optional<String> translate(Vocab vocab) {
    return translate(vocab.getWord(), vocab.getSource(), vocab.getTarget());
  }

  @Override
  public Optional<String> translate(String word, Language source, Language target) {
    if (!Arrays.asList(getSupportedLanguages()).contains(source)
        || !Arrays.asList(getSupportedLanguages()).contains(target))
      throw new IllegalArgumentException("Language is not available.");

    if (source.equals(Language.BASE)) {
      return translations.get(word, target);
    } else {
      for (Map.Entry<String, Map<String, String>> entry : translations.entrySet()) {
        Map<String, String> translation = entry.getValue();
        if (translation.containsKey(source.getCode()) && translation.containsKey(target.getCode())) {
          if (translation.get(source.getCode()).equals(word)) {
            return Optional.of(translation.get(target.getCode()));
          }
        }
      }
    }

    return Optional.empty();
  }

}
