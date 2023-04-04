package lingu.services;

import java.util.Map;
import java.util.Optional;

import lingu.lib.JSONFileHandler;
import lingu.model.Language;

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
    this.source = source;
    this.target = target;

    try {
      this.translations = jsonHandler.load(filename, Map.class).get();
    } catch (ClassCastException e) {
      throw new IllegalStateException("The translations file is corrupted and needs to be replaced...");
    }
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
  public Optional<String> translate(String word) {
    if (source != Language.ENGLISH)
      System.err.println("offline translator can only translate from english.");

    if (target != Language.GERMAN && target != Language.SPANISH && target != Language.FRENCH)
      System.err.println("offline translator can only translate to german, english and spanish.");

    Map<String, String> translation = translations.get(word);
    if (translation != null) {
      String translatedWord = translation.get(target.getCode());
      return Optional.ofNullable(translatedWord);
    } else {
      return Optional.empty();
    }
  }

}
