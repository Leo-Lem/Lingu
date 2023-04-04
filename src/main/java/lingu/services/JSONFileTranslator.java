package lingu.services;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import lingu.model.Language;
import lingu.services.interfaces.Translator;

public class JSONFileTranslator implements Translator {

  private Language source;
  private Language target;

  private Map<String, Map<String, String>> translations;

  public JSONFileTranslator(Language source, Language target) {
    this(source, target, "src/main/resources/words.json", new JSONFileHandler());
  }

  public JSONFileTranslator(Language source, Language target, JSONFileHandler jsonHandler) {
    this(source, target, "src/main/resources/words.json", jsonHandler);
  }

  @SuppressWarnings("unchecked")
  public JSONFileTranslator(Language source, Language target, String filename, JSONFileHandler jsonHandler) {
    if (!Arrays.asList(getAvailableLanguages()).contains(source)
        || !Arrays.asList(getAvailableLanguages()).contains(target))
      throw new IllegalArgumentException("Language is not available.");

    this.source = source;
    this.target = target;

    try {
      this.translations = jsonHandler.load(filename, Map.class).get();
    } catch (ClassCastException e) {
      throw new IllegalStateException("The translations file is corrupted and needs to be replaced...");
    }
  }

  @Override
  public Language[] getAvailableLanguages() {
    return new Language[] { Language.ENGLISH, Language.GERMAN, Language.SPANISH, Language.FRENCH };
  }

  @Override
  public Language getSource() {
    return source;
  }

  @Override
  public Language getTarget() {
    return target;
  }

  @Override
  public JSONFileTranslator setSource(Language language) {
    this.source = language;
    return this;
  }

  @Override
  public JSONFileTranslator setTarget(Language language) {
    this.target = language;
    return this;
  }

  @Override
  public Optional<String> translate(String word) {
    Map<String, String> translation = translations.get(word);
    if (translation != null) {
      String translatedWord = translation.get(target.getCode());
      return Optional.ofNullable(translatedWord);
    } else {
      return Optional.empty();
    }
  }

}
