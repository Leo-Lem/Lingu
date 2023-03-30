package lingu.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lingu.model.Language;

public class OfflineTranslator implements Translator {

  private Language source;
  private Language target;

  private final String FILENAME = "src/main/resources/words.json";
  private Map<String, Map<String, String>> translations;

  public OfflineTranslator(Language source, Language target) {
    this.source = source;
    this.target = target;
    this.translations = loadTranslations();
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
      String translatedWord = translation.get(target.getCode().toLowerCase());
      return Optional.ofNullable(translatedWord);
    } else {
      return Optional.empty();
    }
  }

  private Map<String, Map<String, String>> loadTranslations() {
    File file = new File(FILENAME);

    try (Reader reader = new FileReader(file)) {
      ObjectMapper mapper = new ObjectMapper();
      TypeReference<Map<String, Map<String, String>>> typeRef = new TypeReference<Map<String, Map<String, String>>>() {
      };
      return mapper.readValue(reader, typeRef);
    } catch (IOException e) {
      System.err.println("Error loading translations: " + e.getMessage());
      return null;
    }
  }

}
