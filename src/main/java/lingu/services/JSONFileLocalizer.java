package lingu.services;

import java.util.*;

import lingu.model.Language;
import lingu.services.interfaces.Localizer;

public class JSONFileLocalizer implements Localizer {

  private Language language;
  private final Map<String, Map<String, String>> localizations;

  public JSONFileLocalizer(Language language) {
    this(language, "src/main/resources/localizations.json", new JSONFileHandler());
  }

  @SuppressWarnings("unchecked")
  public JSONFileLocalizer(Language language, String filename, JSONFileHandler jsonHandler) {
    if (!Arrays.asList(getAvailableLanguages()).contains(language))
      throw new IllegalArgumentException("Language is not available: " + language);

    this.language = language;

    try {
      this.localizations = jsonHandler.load(filename, Map.class).get();
    } catch (ClassCastException e) {
      throw new IllegalStateException(
          "The localizations file for " + getLanguage().getCode() + " is corrupted...");
    }
  }

  @Override
  public Language[] getAvailableLanguages() {
    return new Language[] { Language.ENGLISH, Language.GERMAN, Language.SPANISH, Language.FRENCH };
  }

  @Override
  public Language getLanguage() {
    return language;
  }

  @Override
  public Language setLanguage(Language language) {
    return this.language = language;
  }

  @Override
  public String localize(String key) {
    return Optional.ofNullable(localizations.get(key))
        .flatMap(localization -> Optional.ofNullable(localization.get(language.getCode())))
        .orElse(key);
  }

  @Override
  public String localizeWithStringArgument(String key, String argument) {
    return String.format(localize(key + " %s"), localize(argument));
  }
}
