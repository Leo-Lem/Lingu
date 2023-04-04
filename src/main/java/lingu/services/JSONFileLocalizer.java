package lingu.services;

import java.util.Map;
import java.util.Optional;

import lingu.lib.JSONFileHandler;
import lingu.model.Language;

public class JSONFileLocalizer implements Localizer {

  private Language language;
  private final Map<String, Map<String, String>> localizations;

  public JSONFileLocalizer(Language language) {
    this(language, "src/main/resources/localizations.json", new JSONFileHandler());
  }

  @SuppressWarnings("unchecked")
  public JSONFileLocalizer(Language language, String filename, JSONFileHandler jsonHandler) {
    this.language = language;

    try {
      this.localizations = jsonHandler.load(filename, Map.class).get();
    } catch (ClassCastException e) {
      throw new IllegalStateException(
          "The localizations file for " + getLanguage().getCode() + " is corrupted...");
    }
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
  public Optional<String> localize(String key) {
    return Optional.ofNullable(localizations.get(key))
        .flatMap(localization -> Optional.ofNullable(localization.get(language.getCode())));
  }

}
