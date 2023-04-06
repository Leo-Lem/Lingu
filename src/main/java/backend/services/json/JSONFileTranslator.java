package backend.services.json;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import backend.model.Language;
import backend.model.Vocab;
import backend.services.interfaces.Persistor;
import backend.services.interfaces.Translator;

public class JSONFileTranslator implements Translator {
  private Map<String, Map<String, String>> translations;

  public JSONFileTranslator() throws IllegalStateException {
    this(new JSONFilePersistor("src/main/resources/translations.json"));
  }

  @SuppressWarnings("unchecked")
  public JSONFileTranslator(Persistor persistor) throws IllegalStateException {
    try {
      this.translations = persistor.load(Map.class).get();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("The translations file cannot be located…");
    } catch (ClassCastException e) {
      throw new IllegalStateException("The translations file is corrupted and needs to be replaced…");
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
      Map<String, String> translation = translations.get(word);

      if (translation != null) {
        String translatedWord = translation.get(target.getCode());
        return Optional.ofNullable(translatedWord);
      }
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
