package backend.model;

import java.util.*;

public enum Language {

  BASE(""),
  ENGLISH("EN"),
  GERMAN("DE"),
  SPANISH("ES"),
  FRENCH("FR");

  private String code;

  private Language(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

  public static Language defaultLanguage() {
    String systemLanguageCode = Locale.getDefault().getLanguage();
    Map<String, Language> codeToLanguage = new HashMap<>();
    for (Language language : Language.values()) {
      codeToLanguage.put(language.getCode(), language);
    }
    return Optional.ofNullable(codeToLanguage.get(systemLanguageCode.toUpperCase())).orElse(ENGLISH);
  }

}
